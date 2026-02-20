import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

//jatek foablaka, kezeli: play round, auto play, lefuttat egy kort es a WAR-t
public class WarGameFrame extends JFrame {

    private Player player = new Player();
    private Player computer = new Player();

    private CardPanel panel = new CardPanel();
    private JLabel status = new JLabel("Press Play");
    private JLabel count = new JLabel();

    private JButton play;
    private Timer timer;

    private int roundCount = 0;
    private static final int MAX_ROUNDS = 50;
    private boolean gameFinished = false;


    // felepiti es elinditja a WAR jatek ablakat, beallitja az ablak tulajdonsagait,
    // letrehozza a gombokat, osszekoti az esemenykezelokkel, es elrendezi az elemeket
    public WarGameFrame() {
        setTitle("WAR");
        setSize(300, 350);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // letrehozza a paklit, megkeveri, kiosztja a jatekosoknak
        setupGame();

        play = new JButton("Play Round");
        play.addActionListener(e -> playRound());

        // idozito, ami 1,5 masodpercenkent automatikusan lejatszik egy kort
        timer = new Timer(1500, e -> playRound());

        // elinditja a Timert, a jatek magatol megy tovabb
        JButton auto = new JButton("Auto Play");
        auto.addActionListener(e -> timer.start());

        JPanel bottom = new JPanel();
        bottom.add(play);
        bottom.add(auto);

        add(panel, BorderLayout.CENTER);
        add(status, BorderLayout.NORTH);
        // ezek ugyanazt a helyet jelentik, az egyik felulirja a masikat
        add(count, BorderLayout.SOUTH);
        add(bottom, BorderLayout.PAGE_END);

        // kiirja a jatekos lapjait es a gep lapjait, kezdo allapot frissitese
        updateCount();

        SoundManager.playBackground("background.wav");

    }

    //letrehoz egy kevert paklit, majd felvaltva szetosztja az osszes lapot a jatekos es a gep kozott
    private void setupGame() {
        Deck deck = new Deck();   //ekkor meg rendezett sorrenden van
        deck.shuffle();

        IntStream.range(0, deck.getCards().size())
                .forEach(i -> {
                    if (i % 2 == 0)
                        player.addCard(deck.getCards().get(i));
                    else
                        computer.addCard(deck.getCards().get(i));
                });

    }

    //leallitja a jatekot dontetlennel, ez a vegtelen jatek elkerulese eseten tortenik
    // ha tul sok kort volt lejatszva akkor dontetlen lesz
    private void endDraw() {
        gameFinished = true;
        timer.stop();
        // letiltja a play round gombot
        play.setEnabled(false);

        //elmenti az eredmenyt
        StatisticsManager.saveDraw();
        status.setText("🤝 Draw! Too many rounds.");
    }


    private void playRound() {

        // ha a jatek veget ert megakadalyozza hogy tovabb fusson (timer + gomb ellen)
        if (gameFinished) return;

        // vegtelen jatek ellen kell a szamlalo, kell a dontetlenhez
        roundCount++;

        //tul sok kor eseten leall dontetlennel
        if (roundCount >= MAX_ROUNDS) {
            endDraw();
            return;
        }

        // az egyik jatekos kifogy lapokbol -> jatek vege
        if (!player.hasCards() || !computer.hasCards()) {
            endGame();
            return;
        }

        // hangot jatszik le minden kornel
        SoundManager.play("click.wav");

        // ide kerulnek a korben kijatszott lapok
        Queue<Card> pile = new LinkedList<>();

        //mindket fel kijatszik egy lapot
        Card p = player.playCard();
        Card c = computer.playCard();

        pile.add(p);
        pile.add(c);

        // kiirja milyen lapok csaptak ossze
        panel.setCards(p.toString(), c.toString());


        //eredmeny eldontese
        if (p.getRank() > c.getRank()) {
            player.addCards(pile);
            status.setText("You win");
        } else if (p.getRank() < c.getRank()) {
            computer.addCards(pile);
            status.setText("Computer wins");
        } else {
            status.setText("WAR!");
        }

        //frissiti a kepernyon, hogy hany lapja van a jatekosnak es a szamitogepnek
        updateCount();
    }

    private void updateCount() {
        count.setText("You: " + player.cardCount() +
                " | Computer: " + computer.cardCount());
    }

    //leallitja a jatekot, eltarolja az eredmenyt, kiirja a vegso uzenetet
    private void endGame() {
        gameFinished = true;
        timer.stop();
        play.setEnabled(false);

        boolean playerWon = player.hasCards();
        StatisticsManager.saveGameResult(playerWon);

        status.setText(
                playerWon ? "🏆 You win the war!" : "💀 You lost the war."
        );
    }


}
