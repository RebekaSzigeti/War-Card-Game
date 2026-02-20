import javax.swing.*;
import java.awt.*;

//kirajzolja a kartyat es a szoveget kozepre
class CardPanel extends JPanel {

    private String playerText = "";
    private String computerText = "";

    public void setCards(String playerCard, String computerCard) {
        playerText = playerCard;
        computerText = computerCard;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // kártya
        g.setColor(Color.ORANGE);
        g.fillRect(40, 20, 180, 220);

        g.setColor(Color.BLACK);
        g.drawRect(40, 20, 180, 220);

        // szöveg – 3 sor
        g.drawString("Player card:", 60, 60);
        g.drawString(playerText, 60, 80);

        g.drawString("VS", 120, 130);

        g.drawString("Computer card:", 60, 160);
        g.drawString(computerText, 60, 180);
    }
}

