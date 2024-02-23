import evaluator.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static DeckOfCards deck = new DeckOfCards();
    static List<Hand> winnerHand = new ArrayList<>();

    public static void main(String[] args) throws InvalidHandSizeException {

        Player player1 = new Player("Player_A");
        Player player2 = new Player("Player_B");

        startOfDealing(player1);
        startOfDealing(player2);

        flopOfDealing(player1);
        flopOfDealing(player2);

        turnOfDealing(player1);
        turnOfDealing(player2);

        riverOfDealing(player1);
        riverOfDealing(player2);

        System.out.println(player1);
        System.out.println(player2);

        /*7 lapból 21 különböző hand állítható össze:
         */
        Map<Player, Hand> round = new HashMap<>();

        Hand player1Hand = getBestHand(player1.getPlayCardList());
        System.out.println("Player 1 hand: "+player1Hand);
        round.put(player1, player1Hand);
        Hand player2Hand = getBestHand(player2.getPlayCardList());
        System.out.println("Player 2 hand: "+player2Hand);
        round.put(player2, player2Hand);

        winnerHand(round);
    }

    private static void winnerHand(Map<Player, Hand> round) {
        Hand bestHand = null;
        Player winner = null;
        for(Player key: round.keySet()){
            if (bestHand == null || (round.get(key).compareTo(bestHand) > 0)) {
                bestHand = round.get(key);
                winner = key;
            }
        }
        System.out.printf("The winner: %s, best hand: %s", winner, bestHand);
    }

    private static Hand getBestHand(List<Card> playCardList) throws InvalidHandSizeException {
        List<Card> tempCardList = new ArrayList<>();
        Hand bestHand = null;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    if (k != i && k != j) {
                        tempCardList.add(playCardList.get(k));
                    }
                }
                System.out.println(tempCardList.size());
                Hand tempHand = new Hand(tempCardList);
                tempCardList.clear();
                if (bestHand == null || (tempHand.compareTo(bestHand) > 0)) {
                    bestHand = tempHand;
                }

            }
        }
        return bestHand;
    }

    private static void startOfDealing(Player player) {
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            cardList.add(deck.getFirstCardOfDeck());
        }
        player.setStarting(cardList);
    }

    private static void flopOfDealing(Player player) {
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cardList.add(deck.getFirstCardOfDeck());
        }
        player.setFlop(cardList);
    }

    private static void turnOfDealing(Player player) {
        Card card = deck.getFirstCardOfDeck();
        player.setTurn(card);
    }

    private static void riverOfDealing(Player player) {
        Card card = deck.getFirstCardOfDeck();
        player.setRiver(card);
    }

}