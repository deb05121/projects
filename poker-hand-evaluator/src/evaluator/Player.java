package evaluator;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> playerCardList;


    public Player(String name) {
        this.name = name;
        this.playerCardList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getPlayerCardList() {
        return playerCardList;
    }

    public void setStarting(List<Card> starting) {
        setPlayerCardList(starting);
    }

    public void setFlop(List<Card> flop) {
        setPlayerCardList(flop);
    }

    public void setTurn(Card turn) {
        List<Card> oneCard = List.of(turn);
        setPlayerCardList(oneCard);
    }

    public void setRiver(Card river) {
        List<Card> oneCard = List.of(river);
        setPlayerCardList(oneCard);
    }

    private void setPlayerCardList(List<Card> cards) {
        this.playerCardList.addAll(cards);
    }

    @Override
    public String toString() {
        return this.name + "{" +
                "playCardList=" + playerCardList +
                '}';
    }
}
