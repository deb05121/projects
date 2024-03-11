package evaluator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class HandTest {
    @ParameterizedTest
    @MethodSource(value = "provideFlushHands")
    void shouldBeFlush(Hand hand) {
        //test-driven development
        Assertions.assertEquals(HandValue.FLUSH, hand.getHandValue());
    }

    static Stream<Hand> provideFlushHands() throws InvalidHandSizeException {
        return Stream.of(
                new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.ACE),
                        new Card(CardColour.CLUBS, CardValue.EIGHT),
                        new Card(CardColour.CLUBS, CardValue.NINE),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.TWO))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.QUEEN),
                        new Card(CardColour.CLUBS, CardValue.EIGHT),
                        new Card(CardColour.CLUBS, CardValue.NINE),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.THREE))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.JACK),
                        new Card(CardColour.CLUBS, CardValue.QUEEN),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.ACE),
                        new Card(CardColour.CLUBS, CardValue.NINE)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "providePokerHands")
    void shouldBePoker(Hand hand) {
        Assertions.assertEquals(HandValue.POKER, hand.getHandValue());
    }

    static Stream<Hand> providePokerHands() throws InvalidHandSizeException {
        return Stream.of(
                new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.ACE),
                        new Card(CardColour.SPADES, CardValue.ACE),
                        new Card(CardColour.HEARTS, CardValue.ACE),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.ACE))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.EIGHT),
                        new Card(CardColour.SPADES, CardValue.QUEEN),
                        new Card(CardColour.HEARTS, CardValue.QUEEN),
                        new Card(CardColour.CLUBS, CardValue.QUEEN))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.JACK),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.KING)
                ))
        );
        //pénz
        //vertical scaling: CPU / memory increase
        //autoscaling
        //horizontal scaling: more instances of the application run
        //AWS + GCP (Google Cloud Provider) + Azure
        //AWS Architect Certificate
        //aws lambda function
    }

    @ParameterizedTest
    @MethodSource(value = "provideThreeOfAKindHands")
    void shouldBeThreeOfAKind(Hand hand) {
        Assertions.assertEquals(HandValue.THREE_OF_A_KIND, hand.getHandValue());
    }

    static Stream<Hand> provideThreeOfAKindHands() throws InvalidHandSizeException {
        return Stream.of(
                new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.ACE),
                        new Card(CardColour.SPADES, CardValue.ACE),
                        new Card(CardColour.HEARTS, CardValue.ACE),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.SEVEN))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.EIGHT),
                        new Card(CardColour.SPADES, CardValue.QUEEN),
                        new Card(CardColour.HEARTS, CardValue.NINE),
                        new Card(CardColour.CLUBS, CardValue.QUEEN))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.JACK),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.TEN)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provideFullHouseHands")
    void shouldBeFullHouse(Hand hand) {
        Assertions.assertEquals(HandValue.FULL_HOUSE, hand.getHandValue());
    }

    static Stream<Hand> provideFullHouseHands() throws InvalidHandSizeException {
        return Stream.of(
                new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.ACE),
                        new Card(CardColour.SPADES, CardValue.ACE),
                        new Card(CardColour.HEARTS, CardValue.ACE),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.KING))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.NINE),
                        new Card(CardColour.SPADES, CardValue.QUEEN),
                        new Card(CardColour.HEARTS, CardValue.NINE),
                        new Card(CardColour.CLUBS, CardValue.QUEEN))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.JACK),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.JACK)
                ))
        );
    }

    //tests should always be independent
    @ParameterizedTest
    @MethodSource(value = "providePairHands")
    void shouldBePair(Hand hand) {
        Assertions.assertEquals(HandValue.PAIR, hand.getHandValue());
    }

    static Stream<Hand> providePairHands() throws InvalidHandSizeException {
        return Stream.of(
                new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.ACE),
                        new Card(CardColour.SPADES, CardValue.ACE),
                        new Card(CardColour.HEARTS, CardValue.TEN),
                        new Card(CardColour.CLUBS, CardValue.THREE),
                        new Card(CardColour.DIAMONDS, CardValue.TWO))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.ACE),
                        new Card(CardColour.SPADES, CardValue.QUEEN),
                        new Card(CardColour.HEARTS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.TWO))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.JACK),
                        new Card(CardColour.CLUBS, CardValue.TWO),
                        new Card(CardColour.CLUBS, CardValue.THREE),
                        new Card(CardColour.CLUBS, CardValue.FOUR),
                        new Card(CardColour.DIAMONDS, CardValue.JACK)
                )));
    }

    @ParameterizedTest
    @MethodSource(value = "provideTwoPairHands")
    void shouldBeTwoPair(Hand hand) {
        Assertions.assertEquals(HandValue.TWO_PAIR, hand.getHandValue());
    }

    static Stream<Hand> provideTwoPairHands() throws InvalidHandSizeException {
        return Stream.of(
                new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.ACE),
                        new Card(CardColour.SPADES, CardValue.ACE),
                        new Card(CardColour.HEARTS, CardValue.SIX),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.KING))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.NINE),
                        new Card(CardColour.SPADES, CardValue.QUEEN),
                        new Card(CardColour.HEARTS, CardValue.NINE),
                        new Card(CardColour.CLUBS, CardValue.TEN))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.JACK),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.SEVEN),
                        new Card(CardColour.DIAMONDS, CardValue.SEVEN)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provideStraightHands")
    void shouldBeStraight(Hand hand) {
        Assertions.assertEquals(HandValue.STRAIGHT, hand.getHandValue());
    }

    static Stream<Hand> provideStraightHands() throws InvalidHandSizeException {
        return Stream.of(
                new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.SIX),
                        new Card(CardColour.SPADES, CardValue.SEVEN),
                        new Card(CardColour.HEARTS, CardValue.FIVE),
                        new Card(CardColour.CLUBS, CardValue.FOUR),
                        new Card(CardColour.DIAMONDS, CardValue.THREE))
                ), new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.TEN),
                        new Card(CardColour.DIAMONDS, CardValue.JACK),
                        new Card(CardColour.SPADES, CardValue.ACE),
                        new Card(CardColour.HEARTS, CardValue.KING),
                        new Card(CardColour.CLUBS, CardValue.QUEEN)))
                , new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.FIVE),
                        new Card(CardColour.DIAMONDS, CardValue.ACE),
                        new Card(CardColour.SPADES, CardValue.TWO),
                        new Card(CardColour.HEARTS, CardValue.THREE),
                        new Card(CardColour.CLUBS, CardValue.FOUR)))
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provideStraightFlushHands")
    void shouldBeStraightFlush(Hand hand) {
        Assertions.assertEquals(HandValue.STRAIGHT_FLUSH, hand.getHandValue());
    }

    static Stream<Hand> provideStraightFlushHands() throws InvalidHandSizeException {
        return Stream.of(
                new Hand(List.of(
                        new Card(CardColour.CLUBS, CardValue.SIX),
                        new Card(CardColour.CLUBS, CardValue.SEVEN),
                        new Card(CardColour.CLUBS, CardValue.FIVE),
                        new Card(CardColour.CLUBS, CardValue.FOUR),
                        new Card(CardColour.CLUBS, CardValue.THREE))
                ), new Hand(List.of(
                        new Card(CardColour.DIAMONDS, CardValue.TEN),
                        new Card(CardColour.DIAMONDS, CardValue.JACK),
                        new Card(CardColour.DIAMONDS, CardValue.NINE),
                        new Card(CardColour.DIAMONDS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.QUEEN)))
                , new Hand(List.of(
                        new Card(CardColour.SPADES, CardValue.FIVE),
                        new Card(CardColour.SPADES, CardValue.ACE),
                        new Card(CardColour.SPADES, CardValue.TWO),
                        new Card(CardColour.SPADES, CardValue.THREE),
                        new Card(CardColour.SPADES, CardValue.FOUR)))
        );
    }

    @Test
    void shouldBeRoyalFlush() throws InvalidHandSizeException {
        Hand hand = new Hand(List.of(
                new Card(CardColour.DIAMONDS, CardValue.TEN),
                new Card(CardColour.DIAMONDS, CardValue.QUEEN),
                new Card(CardColour.DIAMONDS, CardValue.KING),
                new Card(CardColour.DIAMONDS, CardValue.JACK),
                new Card(CardColour.DIAMONDS, CardValue.ACE)));
        Assertions.assertEquals(HandValue.ROYAL_FLUSH, hand.getHandValue());
    }

    @ParameterizedTest
    @MethodSource(value = "provideHandsWithDifferentHandValues")
    void shouldDifferentiateBetweenDifferentHandValues(List<Hand> hands) {
        var hand1 = hands.get(0);
        var hand2 = hands.get(1);

        Assertions.assertTrue(hand1.compareTo(hand2) > 0);
    }

    static Stream<List<Hand>> provideHandsWithDifferentHandValues() throws InvalidHandSizeException {
        //functional interfaces
        return Stream.of(
                List.of(
                        //royal
                        new Hand(List.of(
                                new Card(CardColour.DIAMONDS, CardValue.KING),
                                new Card(CardColour.DIAMONDS, CardValue.QUEEN),
                                new Card(CardColour.DIAMONDS, CardValue.JACK),
                                new Card(CardColour.DIAMONDS, CardValue.TEN),
                                new Card(CardColour.DIAMONDS, CardValue.ACE)
                        )),
                        new Hand(List.of(
                                new Card(CardColour.DIAMONDS, CardValue.KING),
                                new Card(CardColour.DIAMONDS, CardValue.QUEEN),
                                new Card(CardColour.DIAMONDS, CardValue.JACK),
                                new Card(CardColour.DIAMONDS, CardValue.TEN),
                                new Card(CardColour.DIAMONDS, CardValue.NINE)
                        ))
                )

            /*TODO: provide more tests
              @CSVFileSource
              @CSVSource
             */
        );
    }

    @Test
    @DisplayName("straight -> first > second")
    void shouldBeAbleToEvaluateTwoStraightHandsWhenFirstIsGreaterThanSecond() throws InvalidHandSizeException {
        Hand firstHand = new Hand(
                List.of(
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.JACK),
                        new Card(CardColour.DIAMONDS, CardValue.TEN),
                        new Card(CardColour.DIAMONDS, CardValue.ACE)
                )
        );

        System.out.println(firstHand);

        Hand secondHand = new Hand(
                List.of(
                        new Card(CardColour.CLUBS, CardValue.EIGHT),
                        new Card(CardColour.DIAMONDS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.JACK),
                        new Card(CardColour.DIAMONDS, CardValue.TEN),
                        new Card(CardColour.DIAMONDS, CardValue.NINE)
                )
        );

        System.out.println(secondHand);

        //Assertj --> assertThat --> assertThat(firstHand.compareTo(secondHand)).isGreaterThan(0)
        Assertions.assertEquals(2, firstHand.compareTo(secondHand)); //FIXME
    }

    @Test
    @DisplayName("straight -> second > first")
    void shouldBeAbleToEvaluateTwoStraightHandsWhenSecondIsGreaterThanFirst() throws InvalidHandSizeException {
        Hand firstHand = new Hand(
                List.of(
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.JACK),
                        new Card(CardColour.DIAMONDS, CardValue.TEN),
                        new Card(CardColour.DIAMONDS, CardValue.ACE)
                )
        );

        Hand secondHand = new Hand(
                List.of(
                        new Card(CardColour.CLUBS, CardValue.EIGHT),
                        new Card(CardColour.DIAMONDS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.JACK),
                        new Card(CardColour.DIAMONDS, CardValue.TEN),
                        new Card(CardColour.DIAMONDS, CardValue.NINE)
                )
        );

        //Assertj --> assertThat --> assertThat(firstHand.compareTo(secondHand)).isGreaterThan(0)
        Assertions.assertEquals(2, firstHand.compareTo(secondHand)); //FIXME
    }

    @Test
    @DisplayName("straight -> second == first")
    void shouldBeAbleToEvaluateTwoStraightHandsWhenSecondIsEqualToFirst() throws InvalidHandSizeException {
        Hand firstHand = new Hand(
                List.of(
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.QUEEN),
                        new Card(CardColour.DIAMONDS, CardValue.JACK),
                        new Card(CardColour.HEARTS, CardValue.TEN),
                        new Card(CardColour.DIAMONDS, CardValue.ACE)
                )
        );

        Hand secondHand = new Hand(
                List.of(
                        new Card(CardColour.DIAMONDS, CardValue.JACK),
                        new Card(CardColour.CLUBS, CardValue.KING),
                        new Card(CardColour.DIAMONDS, CardValue.QUEEN),
                        new Card(CardColour.CLUBS, CardValue.TEN),
                        new Card(CardColour.DIAMONDS, CardValue.ACE)
                )
        );

        Assertions.assertEquals(0, firstHand.compareTo(secondHand));
    }

}

