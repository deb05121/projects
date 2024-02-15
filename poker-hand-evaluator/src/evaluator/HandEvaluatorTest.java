package evaluator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HandEvaluatorTest {

    @Test
    void shouldEvaluateHandsWithDifferentHandValue() throws InvalidHandSizeException {
        Hand firstHand = new Hand(List.of(
            new Card(CardColour.CLUBS, CardValue.TWO),
            new Card(CardColour.SPADES, CardValue.FOUR),
            new Card(CardColour.HEARTS, CardValue.SIX),
            new Card(CardColour.CLUBS, CardValue.EIGHT),
            new Card(CardColour.DIAMONDS, CardValue.ACE))
        );

        Hand secondHand = new Hand(List.of(
            new Card(CardColour.CLUBS, CardValue.TWO),
            new Card(CardColour.SPADES, CardValue.TWO),
            new Card(CardColour.HEARTS, CardValue.KING),
            new Card(CardColour.CLUBS, CardValue.ACE),
            new Card(CardColour.DIAMONDS, CardValue.KING))
        );

        Assertions.assertEquals(HandEvaluator.THE_SECOND_HAND_HAS_WON, HandEvaluator.evaluate(firstHand, secondHand));
        Assertions.assertEquals(HandEvaluator.THE_FIRST_HAND_HAS_WON, HandEvaluator.evaluate(secondHand, firstHand));
    }

    @Test
    void shouldEvaluateTwoTwoPairs() throws InvalidHandSizeException {
        Hand firstHand = new Hand(List.of(
            new Card(CardColour.CLUBS, CardValue.ACE),
            new Card(CardColour.SPADES, CardValue.ACE),
            new Card(CardColour.HEARTS, CardValue.SIX),
            new Card(CardColour.CLUBS, CardValue.EIGHT),
            new Card(CardColour.DIAMONDS, CardValue.EIGHT))
        );

        Hand secondHand = new Hand(List.of(
            new Card(CardColour.CLUBS, CardValue.TWO),
            new Card(CardColour.SPADES, CardValue.TWO),
            new Card(CardColour.HEARTS, CardValue.ACE),
            new Card(CardColour.CLUBS, CardValue.ACE),
            new Card(CardColour.DIAMONDS, CardValue.KING))
        );

        //TODO: provide more test values

        Assertions.assertEquals(HandEvaluator.THE_FIRST_HAND_HAS_WON, HandEvaluator.evaluate(firstHand, secondHand));
        Assertions.assertEquals(HandEvaluator.THE_SECOND_HAND_HAS_WON, HandEvaluator.evaluate(secondHand, firstHand));
    }

    @Test
    void shouldEvaluateTwoHighCards() throws InvalidHandSizeException {
        Hand firstHand = new Hand(List.of(
            new Card(CardColour.CLUBS, CardValue.ACE),
            new Card(CardColour.SPADES, CardValue.KING),
            new Card(CardColour.HEARTS, CardValue.QUEEN),
            new Card(CardColour.CLUBS, CardValue.JACK),
            new Card(CardColour.DIAMONDS, CardValue.THREE))
        );

        Hand secondHand = new Hand(List.of(
            new Card(CardColour.CLUBS, CardValue.TWO),
            new Card(CardColour.SPADES, CardValue.ACE),
            new Card(CardColour.HEARTS, CardValue.KING),
            new Card(CardColour.CLUBS, CardValue.JACK),
            new Card(CardColour.DIAMONDS, CardValue.QUEEN))
        );
        Assertions.assertEquals(HandEvaluator.THE_FIRST_HAND_HAS_WON, HandEvaluator.evaluate(firstHand, secondHand));
        Assertions.assertEquals(HandEvaluator.THE_SECOND_HAND_HAS_WON, HandEvaluator.evaluate(secondHand, firstHand));
    }
}
