
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CirclePanel extends JPanel {
    public CirclePanel(int width, int height) {
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

        g2.setColor(new Color(220, 0, 0, 100));
        g2.drawOval(220, 20, 300, 300);
    }
}