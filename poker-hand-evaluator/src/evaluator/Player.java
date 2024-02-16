package evaluator;

import java.util.ArrayList;
import java.util.List;

public class Player {


    private List<Card> playCardList;

    public Player() {

        this.playCardList = new ArrayList<>();
    }

    public void setStarting(List<Card> starting) {

        setPlayCardList(starting);
    }

    public void setFlop(List<Card> flop) {
        setPlayCardList(flop);
    }

    public void setTurn(Card turn) {
        List<Card> oneCard = List.of(turn);
        setPlayCardList(oneCard);
    }

    public void setRiver(Card river) {
        List<Card> oneCard = List.of(river);
        setPlayCardList(oneCard);
    }
   private void setPlayCardList(List<Card> cards){
       this.playCardList.addAll(cards);
   }

    @Override
    public String toString() {
        return "Player{" +
                "playCardList=" + playCardList +
                '}';
    }
}
