import evaluator.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidHandSizeException {
        Card card = new Card(CardColour.CLUBS, CardValue.ACE);
        Hand hand1 = null;

        //tech debt
        hand1 = new Hand(
            List.of(
                new Card(CardColour.CLUBS, CardValue.ACE),
                new Card(CardColour.HEARTS, CardValue.ACE),
                new Card(CardColour.SPADES, CardValue.ACE),
                new Card(CardColour.DIAMONDS, CardValue.ACE),
                new Card(CardColour.DIAMONDS, CardValue.KING))
        );
        System.out.println(hand1);

        Hand hand2 = new Hand(
            List.of(
                new Card(CardColour.CLUBS, CardValue.KING),
                new Card(CardColour.HEARTS, CardValue.KING),
                new Card(CardColour.SPADES, CardValue.KING),
                new Card(CardColour.DIAMONDS, CardValue.ACE),
                new Card(CardColour.DIAMONDS, CardValue.KING))
        );
        System.out.println(HandEvaluator.evaluate(hand1, hand2));
    }
}