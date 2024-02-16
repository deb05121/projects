import evaluator.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static DeckOfCards deck = new DeckOfCards();
    public static void main(String[] args) throws InvalidHandSizeException {

        System.out.println(deck);

        Player player1 = new Player();
        Player player2 = new Player();

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

    }

    private static void startOfDealing(Player player) {
        List<Card> cardList = new ArrayList<>();
        for (int i=0; i<2; i++){
            cardList.add(deck.getFirstCardOfDeck());
        }
        player.setStarting(cardList);
    }

    private static void flopOfDealing(Player player) {
        List<Card> cardList = new ArrayList<>();
        for (int i=0; i<3; i++){
            cardList.add(deck.getFirstCardOfDeck());
        }
        player.setStarting(cardList);
    }

    private static void turnOfDealing(Player player) {
        Card card = deck.getFirstCardOfDeck();
        player.setTurn(card);
    }

    private static void riverOfDealing(Player player) {
        Card card = deck.getFirstCardOfDeck();
        player.setTurn(card);
    }

}