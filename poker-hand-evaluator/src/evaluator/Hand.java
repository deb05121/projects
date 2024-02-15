package evaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static evaluator.HandValue.PAIR;

public class Hand implements Comparable<Hand> {
    private List<Card> cards;
    private HandValue handValue;


    //jó lenne sorban tárolnunk őket (közvetlenül egymás után jönnek-e)

    //jó lenne megnéznünk gyorsan, hogy minden szín azonos-e

    //jó lenne valami struktúrában a párokat (pár, két pár, drill, full house, poker) hatékonyan keresni
    //tudom, hogy hányas értékből mennyi van

    private Map<CardValue, Integer> cardFrequencies = new HashMap<>();
    private boolean isFlush;

    public List<Card> getCards() {
        return cards;
    }

    public HandValue getHandValue() {
        return handValue;
    }

    public Map<CardValue, Integer> getCardFrequencies() {
        return cardFrequencies;
    }

    public Hand(List<Card> cards) throws InvalidHandSizeException {
        if (cards.size() != 5) {
            //TODO: check invalid hands (for example multiple cards with identical value or colour)
            throw new InvalidHandSizeException("Hand size must be 5");
        }
        this.cards = new ArrayList<>(cards);
        //a cardcomparator-nak átadom a frekvenciákat
        setCardFrequencyMap();
        this.cards.sort(new CardComparator(this));
        setIsFlush();
        //sort
        //do things with colours?
        evaluateHand();
    }

    private void setIsFlush() {
        //5 db ugyanolyan színű lap van-e
        isFlush = isFlush();
    }

    private void setCardFrequencyMap() {
        for (Card card : cards) {
            //ha a map tartalmazza az adott kártyaértéket, megnövelem a hozzá
            //tartozó value-t 1-gyel
            //map key-ei egy set-et alkotnak
            CardValue cardValue = card.getCardValue();
            if (cardFrequencies.containsKey(cardValue)) {
                cardFrequencies.put(cardValue, cardFrequencies.get(cardValue) + 1);
            } else {
                cardFrequencies.put(cardValue, 1);
            }
            //egyébként odateszem 1-es értékkel
        }
    }

    private void evaluateHand() {
        if (isRoyalFlush()) { //flush
            handValue = HandValue.ROYAL_FLUSH;
        } else if (isStraightFlush()) { //flush
            handValue = HandValue.STRAIGHT_FLUSH;
        } else if (isPoker()) {
            handValue = HandValue.POKER;
        } else if (isFullHouse()) {
            handValue = HandValue.FULL_HOUSE;
        } else if (isFlush()) { //flush
            handValue = HandValue.FLUSH;
        } else if (isStraight()) {
            handValue = HandValue.STRAIGHT;
        } else if (isThreeOfAKind()) {
            handValue = HandValue.THREE_OF_A_KIND;
        } else if (isTwoPair()) {
            handValue = HandValue.TWO_PAIR;
        } else if (isPair()) {
            handValue = PAIR;
        } else {
            handValue = HandValue.HIGH_CARD;
        }
    }

    private boolean isPair() {
        return cardFrequencies.containsValue(2);
    }

    private boolean isTwoPair() {
        int counter = 0;
        for (int frequency : cardFrequencies.values()) {
            if (frequency == 2) {
                counter++;
            }
        }
        return counter == 2;
    }

    private boolean isThreeOfAKind() {
        return cardFrequencies.containsValue(3);
    }

    private boolean isStraight() {
        //szomszédosak --> ordinal különbség -1 -e

        //handle A 2 3 4 5 edge case
        if (isAceToFiveStraight()) {
            return true;
        }

        //separation of concerns
        for (int i = 0; i < cards.size() - 1; i++) { //!!!!!
            int firstCardOrdinal = cards.get(i).getCardValue().ordinal();
            int secondCardOrdinal = cards.get(i + 1).getCardValue().ordinal();
            if (firstCardOrdinal - secondCardOrdinal != -1) {
                return false;
            }
        }
        return true;
    }

    private boolean isAceToFiveStraight() {
        return
            cards.get(0).getCardValue() == CardValue.ACE &&
                cards.get(1).getCardValue() == CardValue.FIVE &&
                cards.get(2).getCardValue() == CardValue.FOUR &&
                cards.get(3).getCardValue() == CardValue.THREE &&
                cards.get(4).getCardValue() == CardValue.TWO;
    }

    private boolean isFlush() {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getCardColour() != cards.get(i + 1).getCardColour()) {
                return false;
            }
        }
        return true;
    }

    private boolean isFullHouse() {
        return isThreeOfAKind() && isPair();
    }

    private boolean isPoker() {
        //A - 1
        //K - 0
        //2 - 4
        return cardFrequencies.containsValue(4);
        //TWO - ACE
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    private boolean isRoyalFlush() {
        //olyan straight flush, aminek az utolsó lapja 10 (csökkenő sorba rendezve)
        return isStraightFlush() && cards.get(cards.size() - 1).getCardValue() == CardValue.TEN;
    }

    @Override
    public String toString() {
        return "Hand{" +
            "cards=" + cards +
            ", handValue=" + handValue +
            ", cardFrequencies=" + cardFrequencies + '}';
    }

    @Override
    public int compareTo(Hand other) {
        //if the handvalue ordinals are different, return with the hand with the higher ordinal
        if (this.handValue.ordinal() != other.handValue.ordinal()) {
            System.out.printf("hand 1: %s hand 2: %s%n", this.handValue, other.handValue);
            System.out.printf("compareto result: %s%n", this.handValue.compareTo(other.handValue));
            return this.handValue.compareTo(other.handValue);
        }

        //straight: only the first one
        //straight flush: only the first one

        //FIXME: decide which hand with similar hand value is better
        //pair, two pair (!), 3, full house, poker --> fogom a legnagyobb frequency-jű
        //struktúrát, azokat komparálom, aztán haladok a kisebbek felé

        for (int i = 0; i < this.cards.size(); i++) {
            var thisCardValue = this.cards.get(i).getCardValue();
            var otherCardValue = other.cards.get(i).getCardValue();
            int comparisonResult = thisCardValue.compareTo(otherCardValue);
            if (comparisonResult > 0) {
                return -1;
            }
            else if(comparisonResult < 0){
                return 1;
            }
        }

        return 0;
    }
}
