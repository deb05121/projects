import evaluator.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Main {


    private static final String GENERATE_ROUND_ID = "INSERT INTO poker.round (winner_hand) VALUES (?)";
    private static final String UPDATE_ROUND_ID = "UPDATE poker.round SET winner_hand = ? WHERE id = ?";
    private static final String SELECT_ROUND_ID = "SELECT id FROM poker.round ORDER BY id DESC LIMIT 1";
    private static final String UPDATE_PLAYER = "INSERT INTO poker.players (name, round_id, card1_colour, card1_value, card2_colour, card2_value, card3_colour, card3_value, card4_colour, card4_value, card5_colour, card5_value, best_hand) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    static DeckOfCards deck;
    static Hand winnerHand = null;
    static boolean gameOver = false;
    static List<Player> playerList = new ArrayList<>();

    public static void main(String[] args) throws InvalidHandSizeException, SQLException, IOException {
        // database connection
        Properties properties = new Properties();
        properties.load(Main.class.getResourceAsStream("application.properties"));
        String user = properties.getProperty("db_user");
        String password = properties.getProperty("db_pass");
        String url = properties.getProperty("db_url");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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
                    Dealer.startOfDealing(player, deck);
                }

                Dealer.flopOfDealing(deck);
                Dealer.turnOfDealing(deck);
                Dealer.riverOfDealing(deck);

                for (Player player : playerList) {
                    player.setPlayerCardList(CardsOnTheTable.getDesk());
                    System.out.printf("\nPlayer %s seven card: %s\n", player.getName(), player.getPlayerCardList());
                }
                //DML
                int roundId = setDatabaseRoundId(connection);

                Map<Player, Hand> roundOfHands = new HashMap<>();
                for (Player player : playerList) {
                    Hand playerHand = getBestHand(player.getPlayerCardList());
                    System.out.printf("\nPlayer %s best hand: %s\n", player.getName(), playerHand);
                    roundOfHands.put(player, playerHand);
                    System.out.printf("handvalue: %s\n", roundOfHands.get(player).getHandValue());
                    //DML
                    insertToPlayersTable(connection, player, playerHand, roundId);
                }

                winnerHand = getWinnerHand(roundOfHands);

                //DML
                updateDatabaseRoundTable(connection, roundId);

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
                for (Player player : playerList) {
                    player.delPlayerCardList();
                }
                CardsOnTheTable.clearDesk();
            }
            scanner.close();
        }
        System.out.println("Game over!");
    }

    private static void updateDatabaseRoundTable(Connection connection, int roundId) throws SQLException {
        String handValue = winnerHand.getHandValue().toString();
        PreparedStatement pds = connection.prepareStatement(UPDATE_ROUND_ID);
        pds.setString(1, handValue);
        pds.setInt(2, roundId);
        System.out.println(pds);
        pds.executeUpdate();
    }

    private static int setDatabaseRoundId(Connection connection) throws SQLException {
        PreparedStatement pds = connection.prepareStatement(GENERATE_ROUND_ID);
        pds.setString(1, null);
        System.out.println(pds);
        pds.executeUpdate();
        int roundId = 0;
        PreparedStatement pds1 = connection.prepareStatement(SELECT_ROUND_ID);
        ResultSet rs1 = pds1.executeQuery();
        while (rs1.next()) {
            roundId = rs1.getInt(1);
        }
        rs1.close();
        return roundId;
    }

    private static void insertToPlayersTable(Connection connection, Player player, Hand playerHand, int roundId) throws SQLException {

        PreparedStatement pds = connection.prepareStatement(UPDATE_PLAYER);
        pds.setString(1, player.getName());
        pds.setInt(2, roundId);
        String cardColour = playerHand.getCards().get(0).getCardColour().toString();
        pds.setString(3, cardColour);
        String cardValue = playerHand.getCards().get(0).getCardValue().toString();
        pds.setString(4, cardValue);
        cardColour = playerHand.getCards().get(1).getCardColour().toString();
        pds.setString(5, cardColour);
        cardValue = playerHand.getCards().get(1).getCardValue().toString();
        pds.setString(6, cardValue);
        cardColour = playerHand.getCards().get(2).getCardColour().toString();
        pds.setString(7, cardColour);
        cardValue = playerHand.getCards().get(2).getCardValue().toString();
        pds.setString(8, cardValue);
        cardColour = playerHand.getCards().get(3).getCardColour().toString();
        pds.setString(9, cardColour);
        cardValue = playerHand.getCards().get(3).getCardValue().toString();
        pds.setString(10, cardValue);
        cardColour = playerHand.getCards().get(4).getCardColour().toString();
        pds.setString(11, cardColour);
        cardValue = playerHand.getCards().get(4).getCardValue().toString();
        pds.setString(12, cardValue);
        String handValue = playerHand.getHandValue().toString();
        pds.setString(13, handValue);
        pds.executeUpdate();

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
}