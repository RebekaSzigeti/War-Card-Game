import java.util.LinkedList;
import java.util.Queue;

public class Player {
    private Queue<Card> hand = new LinkedList<>();

    public void addCard(Card card) {
        hand.add(card);
    }

    public void addCards(Queue<Card> cards) {
        hand.addAll(cards);
    }

    // lap kijatszasa
    public Card playCard() {
        // poll -> elveszi az elso elemet a sorbol, visszaadja azt, eltavolitja a sorbol
        return hand.poll();
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public int cardCount() {
        return hand.size();
    }
}
