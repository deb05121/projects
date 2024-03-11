import evaluator.*;

import java.util.*;

public class Main {
    static DeckOfCards deck;
    static Hand winnerHand = null;
    static boolean gameOver = false;

    static List<Player> playerList = new ArrayList<>();

    public static void main(String[] args) throws InvalidHandSizeException {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            deck = new DeckOfCards();
            System.out.println("How many new players will play the Texas poker:");
            int counterOfPlayer = scanner.nextInt();
            int allPlayers = playerList.size() + counterOfPlayer;
            if ((allPlayers < 2) || allPlayers > 10) {
                System.out.println("Players number have to be 2 at least and 10 at most.");
                gameOver = true;
                break;
            }
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
                player.setPlayerCardList(CardsOnTheTable.getDesk());

                System.out.printf("\nPlayer %s seven card: %s\n", player.getName(), player.getPlayerCardList());
            }

            Map<Player, Hand> round = new HashMap<>();
            for (Player player : playerList) {
                Hand playerHand = getBestHand(player.getPlayerCardList());
                System.out.printf("\nPlayer %s best hand: %s\n", player.getName(), playerHand);
                round.put(player, playerHand);
            }

            winnerHand = getWinnerHand(round);

            System.out.println("\nPlayers: \n" + playerList);
            System.out.println("How many person will exit the card game?");
            int exit = scanner.nextInt();
            if (exit == playerList.size()) {
                gameOver = true;
                break;
            }
            scanner.nextLine();
            for (int i = 0; i < exit; i++) {
                System.out.println("Who exits the card game?");
                String name = scanner.nextLine();
                playerList.removeIf(player -> player.getName().equals(name));
            }
            for(Player player: playerList){
                player.delPlayerCardList();
            }
        }
        System.out.println("Game over!");
        scanner.close();
    }

    private static Hand getWinnerHand(Map<Player, Hand> round) {
        Hand bestHandOfWinner = null;
        Player winner = null;
        for (Player key : round.keySet()) {
            if (bestHandOfWinner == null || (round.get(key).compareTo(bestHandOfWinner) > 0)) {
                bestHandOfWinner = round.get(key);
                winner = key;
            }
        }
        assert winner != null;
        System.out.printf("\nThe winner: %s, \nbest hand: %s", winner.getName(), bestHandOfWinner);
        return bestHandOfWinner;
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
        CardsOnTheTable.addFlopToDesk(cardList);
    }

    private static void turnOfDealing() {
        Card card = deck.getFirstCardOfDeck();
        CardsOnTheTable.addCardToDesk(card);
    }

    private static void riverOfDealing() {
        Card card = deck.getFirstCardOfDeck();
        CardsOnTheTable.addCardToDesk(card);
    }

}