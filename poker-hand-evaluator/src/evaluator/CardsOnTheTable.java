package evaluator;

import java.util.ArrayList;
import java.util.List;

public class CardsOnTheTable {
    private static List<Card> desk = new ArrayList<>();

    public static void addFlopToDesk(List<Card> flop) {
        CardsOnTheTable.desk.addAll(flop);
    }

    public static void addCardToDesk(Card card){
        CardsOnTheTable.desk.add(card);
    }

    public static List<Card> getDesk() {
        return desk;
    }

    public static void clearDesk(){
        desk.clear();
    }
}
