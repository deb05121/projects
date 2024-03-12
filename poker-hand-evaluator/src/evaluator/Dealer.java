package evaluator;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private Dealer() {
    }

    public static void startOfDealing(Player player, DeckOfCards deck) {
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            cardList.add(deck.getFirstCardOfDeck());
        }
        player.setStarting(cardList);
    }

    public static void flopOfDealing(DeckOfCards deck) {
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cardList.add(deck.getFirstCardOfDeck());
        }
        CardsOnTheTable.addFlopToDesk(cardList);
    }

    public static void turnOfDealing(DeckOfCards deck) {
        Card card = deck.getFirstCardOfDeck();
        CardsOnTheTable.addCardToDesk(card);
    }

    public static void riverOfDealing(DeckOfCards deck) {
        Card card = deck.getFirstCardOfDeck();
        CardsOnTheTable.addCardToDesk(card);
    }
}
