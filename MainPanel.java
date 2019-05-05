// メインパネル デフォルト画面

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    // ラジオボタン
    JRadioButton cource1 = new JRadioButton();
    JRadioButton cource2 = new JRadioButton();
    JRadioButton cource3 = new JRadioButton();

    // スタート用ボタン
    JButton start = new JButton();

    public MainPanel (int width, int height, MyFrame mf) {
        // メインパネルのアクションリスナー
        MainPanelActionListener mal = new MainPanelActionListener(this, mf);

        // レイアウトマネージャを無効にする
        this.setLayout(null);

        // Nameと大きさ設定
        this.setName("MainPanel");
        this.setSize(width, height);

        // ラジオボタン名前設定
        cource1.setText("全問順番");
        cource2.setText("全問ランダム");
        cource3.setText("10問ランダム");

        // cource1をアクティブに
        cource1.setSelected(true);

        // ラジオボタンをグループ化
        ButtonGroup group = new ButtonGroup();
        group.add(cource1);
        group.add(cource2);
        group.add(cource3);

        // ラジオボタンの配置座標指定
        cource1.setBounds(100, 100, 100, 25);
        cource2.setBounds(100, 125, 150, 25);
        cource3.setBounds(100, 150, 150, 25);

        // ラジオボタンをパネルに追加
        this.add(cource1);
        this.add(cource2);
        this.add(cource3);

        // スタート用ボタン名前設定
        start.setText("スタート");

        // スタート用ボタンの配置座標指定
        start.setBounds(100, 300, 100, 50);

        // スタートボタンにアクションリスナーを追加
        start.addActionListener(mal);

        // スタートボタンをパネルに追加
        this.add(start);
    }
}

// メインパネルアクションリスナー
class MainPanelActionListener implements ActionListener {
    // フレーム
    MyFrame mf;
    // メインパネル
    MainPanel mp;

    MainPanelActionListener (MainPanel mainPanel, MyFrame myFrame) {
        mp = mainPanel;
        mf = myFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // test print
        // System.out.println("pushed...");
        // ラジオボタンのチェックによる条件分岐
        int[] questionIndex = null;
        if (mp.cource1.isSelected() && !mp.cource2.isSelected() && !mp.cource3.isSelected()) {
            questionIndex = QandA.setQuestionIndexOrder();
        } else if (!mp.cource1.isSelected() && mp.cource2.isSelected() && !mp.cource3.isSelected()) {
            questionIndex = QandA.setQuestionIndexRandom();
        } else if (!mp.cource1.isSelected() && !mp.cource2.isSelected() && mp.cource3.isSelected()) {
            questionIndex = QandA.setQuestionIndexsSampleTen();
        } else {
            // ラジオボタンは3つのうち1つだけがアクティブなはずなのでここは実行されない
            System.out.println("ラジオボタンエラー");
            System.exit(0);
        }
        mf.beginQuestion(questionIndex);
    }
}