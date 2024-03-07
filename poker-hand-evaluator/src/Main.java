import evaluator.*;

import java.util.*;

public class Main {
    static DeckOfCards deck = new DeckOfCards();
    static List<Hand> winnerHand = new ArrayList<>();

    public static void main(String[] args) throws InvalidHandSizeException {

        List<Player> playerList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players will play the Texas poker:");
        int counterOfPlayer = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < counterOfPlayer; i++) {
            String playerName = scanner.nextLine();
            Player player = new Player(playerName);
            playerList.add(player);
        }

        for (Player player : playerList) {
            startOfDealing(player);
        }

        flopOfDealing();
        turnOfDealing();
        riverOfDealing();

        for (Player player : playerList) {
            player.setFlop(CardTable.getFlop());
            player.setTurn(CardTable.getTurn());
            player.setRiver(CardTable.getRiver());
            System.out.printf("\nPlayer %s seven card: %s", player.getName(), player.getPlayerCardList());
        }

        Map<Player, Hand> round = new HashMap<>();
        for (Player player : playerList) {
            Hand playerHand = getBestHand(player.getPlayerCardList());
            System.out.printf("\nPlayer %s best hand: %s", player.getName(), playerHand);
            round.put(player, playerHand);
        }

        winnerHand(round);
    }

    private static void winnerHand(Map<Player, Hand> round) {
        Hand bestHand = null;
        Player winner = null;
        for (Player key : round.keySet()) {
            if (bestHand == null || (round.get(key).compareTo(bestHand) > 0)) {
                bestHand = round.get(key);
                winner = key;
            }
        }
        assert winner != null;
        System.out.printf("\nThe winner: %s, \nbest hand: %s", winner.getName(), bestHand);
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

    private static void flopOfDealing() {
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cardList.add(deck.getFirstCardOfDeck());
        }
        CardTable.setFlop(cardList);
    }

    private static void turnOfDealing() {
        Card card = deck.getFirstCardOfDeck();
        CardTable.setTurn(card);
    }

    private static void riverOfDealing() {
        Card card = deck.getFirstCardOfDeck();
        CardTable.setRiver(card);
    }

}