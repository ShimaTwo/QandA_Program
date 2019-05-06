import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MyFrame extends JFrame implements WindowConstants {
    // メインパネル
    MainPanel mp;
    // 丸を描く
    CirclePanel cp;
    // xを描く
    PekePanel pp;
    // サブパネル群
    SubPanel[] sp;
    // 正解リザルトパネル群
    ResultPanelCorrect[] rpc;
    // 不正解リザルトパネル群
    // ResultPanelIncorrect[] rpi;
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

        cp = new CirclePanel(Width, Height);
        this.add(cp);
        cp.setVisible(false);

        pp = new PekePanel(Width, Height);
        this.add(pp);
        pp.setVisible(false);

        // 結果パネル
        rpc = new ResultPanelCorrect[index.length];
        for (int i = 0; i < index.length; i++) {
            if (i == index.length-1) {
                rpc[i] = new ResultPanelCorrect(Width, Height, Sheet[index[i]], i, -1, this);
            } else {
                rpc[i] = new ResultPanelCorrect(Width, Height, Sheet[index[i]], i, i+1, this);
            }
            this.add(rpc[i]);
            rpc[i].setVisible(false);
        }

        // indexに従って順番にサブパネルを可視化
        // サブパネル 問題画面
        sp = new SubPanel[index.length];
        for (int i = 0; i < index.length; i++) {
            if (i == index.length-1) {
                sp[i] = new SubPanel(Width, Height, Sheet[index[i]],i , -1, this);
            } else {
                sp[i] = new SubPanel(Width, Height, Sheet[index[i]],i , i+1, this);
            }
            this.add(sp[i]);
            sp[i].setVisible(false);
        }

        sp[index[0]].setVisible(true);
    }

    public void setResultPanelVisible(Boolean collect, int index) {
        if (collect == true) {
            // test print
            // System.out.println("visible...");
            rpc[index].setVisible(true);
            cp.setVisible(true);
            sp[index].setVisible(false);
        } else {
            rpc[index].setVisible(true);
            pp.setVisible(true);
            sp[index].setVisible(false);
        }
    }

    public void setResultPanelInvisible(int index) {
        cp.setVisible(false);
        pp.setVisible(false);
    }

    public void setSubPanelVisible(int index) {
        if (index == -1) {
            System.exit(0);
        } else {
            sp[index].setVisible(true);
        }
    }

    public void setSubPanelInvisible(int index) {
        rpc[index].setVisible(false);
    }
}