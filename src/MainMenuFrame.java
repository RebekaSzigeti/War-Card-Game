import javax.swing.*;
import java.awt.*;

//fomenu, navigacio -> start game, statistics, exit
public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("WAR - Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton start = new JButton("Start Game");
        JButton stats = new JButton("Statistics");
        JButton exit = new JButton("Exit");

        start.addActionListener(e -> {
            new WarGameFrame().setVisible(true);
            //csak az adott ablakot zarja be, a program tovabb fut
            dispose();
        });

        //megnyit egy informacios ablakot, amelyben megjeleniti a korabban elmentett statisztikat
        stats.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this, //szulo ablak
                        StatisticsManager.loadStatistics(), //megjelenitett szoveg
                        "Statistics", //ablak cime
                        JOptionPane.INFORMATION_MESSAGE //ikon tipusa
                )
        );

        exit.addActionListener(e -> System.exit(0));

        add(start);
        add(stats);
        add(exit);
    }
}
