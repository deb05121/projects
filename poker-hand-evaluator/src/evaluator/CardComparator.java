package evaluator;

import java.util.Comparator;
import java.util.Map;

public class CardComparator implements Comparator<Card> {

    private final Map<CardValue, Integer> frequencies;

    public CardComparator(Hand hand) {
        this.frequencies = hand.getCardFrequencies();
    }

    @Override
    public int compare(Card card1, Card card2) {

        int firstCardFrequency = frequencies.get(card1.getCardValue());
        int secondCardFrequency = frequencies.get(card2.getCardValue());
        if (firstCardFrequency > secondCardFrequency) {
            return -1;
        } else if (secondCardFrequency > firstCardFrequency) {
            return 1;
        }
        return card1.getCardValue().compareTo(card2.getCardValue());
    }
}
