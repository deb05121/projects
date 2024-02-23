package evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DeckOfCards {
    private List<Card> deck;
    private Map<Integer, Card> deckOfPlay;
    private List<Card> playCards;
    private Integer index;


    public DeckOfCards() {
        this.deck = new ArrayList<>();
        this.deckOfPlay = new TreeMap<>();
        this.playCards = new ArrayList<>();
        //original deck
        for (CardColour cardColour : CardColour.values()) {
            for (CardValue cardValue : CardValue.values()) {
                Card card = new Card(cardColour, cardValue);
                deck.add(card);
            }
        }

        List<Integer> index = new ArrayList<>();
        while (index.size() < 52) {
            int i = (int) (Math.random() * 1000) + 1;
            if (!index.contains(i)) {
                index.add(i);
            }
        }
        for (int i = 0; i < 52; i++) {
            if (!deckOfPlay.containsValue(deck.get(i))) {
                deckOfPlay.put(index.get(i), deck.get(i));
            }
        }

        for (Integer key : deckOfPlay.keySet()) {
            playCards.add(deckOfPlay.get(key));
        }
        System.out.println(playCards.size());
        int spades = 0;
        int diamonds = 0;
        int clubs = 0;
        int hearts = 0;
        for (Card card : playCards) {
            switch (card.getCardColour()) {
                case CLUBS -> clubs++;
                case HEARTS -> hearts++;
                case SPADES -> spades++;
                case DIAMONDS -> diamonds++;
            }
        }
        System.out.printf("%d, %d, %d, %d \n", clubs, hearts, spades, diamonds);
    }

    public Card getFirstCardOfDeck() {
        Card card = playCards.get(0);
        this.deleteFirstCardOfDeck();
        return card;
    }

    private void deleteFirstCardOfDeck() {
        playCards.remove(0);
        //System.out.println(playCards.size());
    }

    @Override
    public String toString() {
        return "playCards{" +
                "deck=" + playCards +
                '}';
    }

}
