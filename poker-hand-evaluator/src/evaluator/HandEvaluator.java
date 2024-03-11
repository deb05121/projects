package evaluator;

public class HandEvaluator {

    public static final String THE_FIRST_HAND_HAS_WON = "The first hand has won";
    public static final String THE_SECOND_HAND_HAS_WON = "The second hand has won";
    public static final String THE_TWO_HANDS_ARE_EQUAL = "The two hands are equal";

    private HandEvaluator() {
    }

    public static String evaluate(Hand firstHand, Hand secondHand) {
        int comparisonResult = firstHand.compareTo(secondHand);

        return comparisonResult > 0
                ? THE_FIRST_HAND_HAS_WON
                : comparisonResult < 0
                ? THE_SECOND_HAND_HAS_WON
                : THE_TWO_HANDS_ARE_EQUAL;
    }
}
