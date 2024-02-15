package evaluator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CardComparator implements Comparator<Card> {

    private Map<CardValue, Integer> frequencies = new HashMap<>();

    //FIXME: pass Hand to get rid of potential violation
    public CardComparator(Hand hand){
        this.frequencies = hand.getCardFrequencies();
    }

    @Override
    public int compare(Card card1, Card card2) {
        //enum ordinalja alapján komparálja
        //döntsd el a value alapján
        //frekvencia?!
        //veszem a legnagyobb frekvenciájút, és előreteszem
        int firstCardFrequency = frequencies.get(card1.getCardValue());
        int secondCardFrequency = frequencies.get(card2.getCardValue());
        if(firstCardFrequency > secondCardFrequency){
            return -1;
        } else if(secondCardFrequency > firstCardFrequency){
            return 1;
        }
        return card1.getCardValue().compareTo(card2.getCardValue());
    }
}
