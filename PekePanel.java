import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PekePanel extends JPanel {
    public PekePanel(int width, int height) {
        repaint();

        // パネル設定
        this.setName("ResultPanel");
        this.setSize(width, height);
        this.setLayout(null);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        BasicStroke bs = new BasicStroke(20);
        g2.setStroke(bs);

        g2.setColor(new Color(0, 0, 220, 100));
        g2.drawLine(240, 70, 500, 330);
        g2.drawLine(500, 70, 384, 186);
        g2.drawLine(356, 214, 240, 330);
        // test
        g2.setColor(new Color(220, 0, 0, 100));
        // g2.drawLine(520, 50, 220, 350);
        // g2.drawOval(220, 50, 300, 300);
    }
}