package evaluator;

public class Card implements Comparable<Card> {
    private CardColour cardColour;
    private CardValue cardValue;

    public Card() {

    }

    public CardColour getCardColour() {
        return cardColour;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public Card(CardColour cardColour, CardValue cardValue) {
        this.cardColour = cardColour;
        this.cardValue = cardValue;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardColour=" + cardColour +
                ", cardValue=" + cardValue +
                '}';
    }

    @Override
    public int compareTo(Card other) {
        if (this.cardValue.ordinal() != other.cardValue.ordinal()) {
            return this.cardValue.compareTo(other.cardValue);
        }
        return 0;
    }
}
