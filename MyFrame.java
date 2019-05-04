import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MyFrame extends JFrame implements WindowConstants {
    public MyFrame(String title, int width, int height) {
        this.setTitle(title);
        this.setBounds(300, 100, width, height);
    }
}