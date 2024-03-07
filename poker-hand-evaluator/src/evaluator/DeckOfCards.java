package evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DeckOfCards {
    private final List<Card> playCards;

    public DeckOfCards() {
        List<Card> deck = new ArrayList<>();
        Map<Integer, Card> deckOfPlay = new TreeMap<>();
        this.playCards = new ArrayList<>();
        //original deck
        for (CardColour cardColour : CardColour.values()) {
            for (CardValue cardValue : CardValue.values()) {
                Card card = new Card(cardColour, cardValue);
                deck.add(card);
            }
        }

        List<Integer> index = new ArrayList<>();
        while (index.size() < 52) {
            int i = (int) (Math.random() * 1000) + 1;
            if (!index.contains(i)) {
                index.add(i);
            }
        }
        for (int i = 0; i < 52; i++) {
            if (!deckOfPlay.containsValue(deck.get(i))) {
                deckOfPlay.put(index.get(i), deck.get(i));
            }
        }

        for (Integer key : deckOfPlay.keySet()) {
            playCards.add(deckOfPlay.get(key));
        }
        //System.out.println(playCards.size());
    }

    public Card getFirstCardOfDeck() {
        Card card = playCards.get(0);
        this.deleteFirstCardOfDeck();
        return card;
    }

    private void deleteFirstCardOfDeck() {
        playCards.remove(0);
        //System.out.println(playCards.size());
    }

    @Override
    public String toString() {
        return "\nplayCards{" +
                "deck=" + playCards +
                '}';
    }

}
