package evaluator;

import java.util.ArrayList;
import java.util.List;

public class CardTable {
    private static List<Card> flop = new ArrayList<>();
    private static Card turn = new Card();
    private static Card river = new Card();


    public static void setFlop(List<Card> flop) {
        CardTable.flop = flop;
    }

    public static void setTurn(Card turn) {
        CardTable.turn = turn;
    }

    public static void setRiver(Card river) {
        CardTable.river = river;
    }

    public static List<Card> getFlop() {
        return flop;
    }

    public static Card getTurn() {
        return turn;
    }

    public static Card getRiver() {
        return river;
    }
}
