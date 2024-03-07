package evaluator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CardComparatorTest {

    @Test
    void shouldGetCorrectOrder() throws InvalidHandSizeException {
        Hand hand1 = provideFullHouse();
        final var comparator1 = new CardComparator(hand1);
        Hand hand2 = providePoker();
        final var comparator2 = new CardComparator(hand2);
        Hand hand3 = provideTwoPairs();
        final var comparator3 = new CardComparator(hand3);
        Hand hand4 = provideHighCard();
        final var comparator4 = new CardComparator(hand4);

        hand1.getCards().sort(comparator1);
        Assertions.assertEquals(CardValue.KING, hand1.getCards().get(0).getCardValue());
        Assertions.assertEquals(CardValue.KING, hand1.getCards().get(1).getCardValue());
        Assertions.assertEquals(CardValue.KING, hand1.getCards().get(2).getCardValue());
        Assertions.assertEquals(CardValue.ACE, hand1.getCards().get(3).getCardValue());
        Assertions.assertEquals(CardValue.ACE, hand1.getCards().get(4).getCardValue());

        hand2.getCards().sort(comparator2);
        Assertions.assertEquals(CardValue.TWO, hand2.getCards().get(0).getCardValue());
        Assertions.assertEquals(CardValue.TWO, hand2.getCards().get(1).getCardValue());
        Assertions.assertEquals(CardValue.TWO, hand2.getCards().get(2).getCardValue());
        Assertions.assertEquals(CardValue.TWO, hand2.getCards().get(3).getCardValue());
        Assertions.assertEquals(CardValue.KING, hand2.getCards().get(4).getCardValue());

        hand3.getCards().sort(comparator3);
        Assertions.assertEquals(CardValue.KING, hand3.getCards().get(0).getCardValue());
        Assertions.assertEquals(CardValue.KING, hand3.getCards().get(1).getCardValue());
        Assertions.assertEquals(CardValue.TWO, hand3.getCards().get(2).getCardValue());
        Assertions.assertEquals(CardValue.TWO, hand3.getCards().get(3).getCardValue());
        Assertions.assertEquals(CardValue.ACE, hand3.getCards().get(4).getCardValue());

        hand4.getCards().sort(comparator4);
        Assertions.assertEquals(CardValue.ACE, hand4.getCards().get(0).getCardValue());
        Assertions.assertEquals(CardValue.EIGHT, hand4.getCards().get(1).getCardValue());
        Assertions.assertEquals(CardValue.SIX, hand4.getCards().get(2).getCardValue());
        Assertions.assertEquals(CardValue.FOUR, hand4.getCards().get(3).getCardValue());
        Assertions.assertEquals(CardValue.TWO, hand4.getCards().get(4).getCardValue());

        System.out.println(hand1);
        System.out.println(hand2);
        System.out.println(hand3);
        System.out.println(hand4);

    }

    private Hand provideHighCard() throws InvalidHandSizeException {
        return new Hand(List.of(
                new Card(CardColour.CLUBS, CardValue.TWO),
                new Card(CardColour.SPADES, CardValue.FOUR),
                new Card(CardColour.HEARTS, CardValue.SIX),
                new Card(CardColour.CLUBS, CardValue.EIGHT),
                new Card(CardColour.DIAMONDS, CardValue.ACE))
        );

    }

    //TODO: testutils --> provide-olom ezeket a hand-eket
    private Hand providePoker() throws InvalidHandSizeException {
        return new Hand(List.of(
                new Card(CardColour.CLUBS, CardValue.TWO),
                new Card(CardColour.SPADES, CardValue.TWO),
                new Card(CardColour.HEARTS, CardValue.TWO),
                new Card(CardColour.CLUBS, CardValue.KING),
                new Card(CardColour.DIAMONDS, CardValue.TWO))
        );
    }

    private Hand provideTwoPairs() throws InvalidHandSizeException {
        return new Hand(List.of(
                new Card(CardColour.CLUBS, CardValue.TWO),
                new Card(CardColour.SPADES, CardValue.TWO),
                new Card(CardColour.HEARTS, CardValue.KING),
                new Card(CardColour.CLUBS, CardValue.ACE),
                new Card(CardColour.DIAMONDS, CardValue.KING))
        );
    }

    private Hand provideFullHouse() throws InvalidHandSizeException {
        return new Hand(List.of(
                new Card(CardColour.CLUBS, CardValue.ACE),
                new Card(CardColour.SPADES, CardValue.ACE),
                new Card(CardColour.HEARTS, CardValue.KING),
                new Card(CardColour.CLUBS, CardValue.KING),
                new Card(CardColour.DIAMONDS, CardValue.KING))
        );
    }
}
