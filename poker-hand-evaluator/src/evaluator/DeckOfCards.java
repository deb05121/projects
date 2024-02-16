package evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DeckOfCards {
    private List<Card> deck;
    private Map<Integer, Card> deckOfPlay;
    private List<Card> playCards;
    private Integer index;


    public DeckOfCards() {
        this.deck = new ArrayList<>();
        this.deckOfPlay = new TreeMap<>();
        this.playCards = new ArrayList<>();
        for (CardColour cardColour : CardColour.values()) {
            for (CardValue cardValue : CardValue.values()) {
                Card card = new Card(cardColour, cardValue);
                deck.add(card);
            }
        }
        int preSize = 0;
        for (Card card : deck) {
            do {
                preSize = deckOfPlay.size();
                index = (int) (Math.random() * 100) + 1;
                deckOfPlay.put(index, card);

            } while (deckOfPlay.size() == preSize);
        }
        for (Integer key : deckOfPlay.keySet()) {
            playCards.add(deckOfPlay.get(key));
        }
        System.out.println(playCards.size());
    }

    public Card getFirstCardOfDeck() {
        Card card = playCards.get(0);
        this.deleteFirstCardOfDeck();
        return card;
    }
    private void deleteFirstCardOfDeck(){
        playCards.remove(0);
        System.out.println(playCards.size());
    }

    @Override
    public String toString() {
        return "playCards{" +
                "deck=" + playCards +
                '}';
    }

}
