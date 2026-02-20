import java.util.*;
import java.util.stream.IntStream;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        // vegigmegy az osszes szinen, minden szinhez letrehozza a 2-14 erteku lapokat
        // minden kombinaciobol csinal egy Card objektumot, hozzaadja a cards listahoz
        Arrays.stream(suits).forEach(suit ->
                IntStream.rangeClosed(2, 14)
                        .forEach(rank -> cards.add(new Card(suit, rank)))
        );
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}
