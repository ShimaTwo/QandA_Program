import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MyFrame extends JFrame implements WindowConstants {
    // メインパネル
    MainPanel mp;
    // サブパネル群
    SubPanel[] sp;
    // 問題シート
    String[][] Sheet;
    // 出題順インデックス
    int[] nextIndex;
    // 問題数カウンター
    int questionCounter = 0;
    // 幅
    int Width;
    // 高さ
    int Height;

    public MyFrame(String title, int width, int height, String[][] sheet) {
        // 幅、高さ
        Width = width;
        Height = height;

        // 問題シート
        Sheet = sheet;

        // メインパネル 起動時の画面
        mp = new MainPanel(width, height, this);
        this.add(mp);
        mp.setVisible(true);

        // フレーム設定
        this.setTitle(title);
        this.setBounds(300, 100, width, height);
    }

    public void beginQuestion(int[] index) {
        // test print
        // System.out.println("begin");
        // メインパネルを不可視化
        mp.setVisible(false);

        // indexに従って順番にサブパネルを可視化
        // サブパネル 問題画面
        sp = new SubPanel[index.length];
        for (int i = 0; i < index.length; i++) {
            if (i == index.length-1) {
                sp[i] = new SubPanel(Width, Height, Sheet[index[i]], -1, this);
            } else {
                sp[i] = new SubPanel(Width, Height, Sheet[index[i]], i+1, this);
            }
            this.add(sp[i]);
            sp[i].setVisible(false);
        }

        sp[index[0]].setVisible(true);
    }

    public void setSubPanelVisible(int index) {

    }
}