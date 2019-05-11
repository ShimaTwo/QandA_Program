// メインパネル デフォルト画面

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class MainPanel extends JPanel {
    // ラジオボタン
    JRadioButton cource1 = new JRadioButton();
    JRadioButton cource2 = new JRadioButton();
    JRadioButton cource3 = new JRadioButton();

    // スタート用ボタン
    JButton start = new JButton();

    // 出題形式
    JLabel form = new JLabel();

    // 題名
    JLabel title = new JLabel();

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
        cource1.setBounds(150, 150, 100, 25);
        cource2.setBounds(150, 190, 150, 25);
        cource3.setBounds(150, 230, 150, 25);

        // ラジオボタンをパネルに追加
        this.add(cource1);
        this.add(cource2);
        this.add(cource3);

        // 出題形式
        form.setText("出題形式");
        form.setBounds(130, 100, 150, 30);
        form.setFont(new Font("Arias", Font.PLAIN, 24));
        this.add(form);

        // 題名
        title.setText("理解度テスト向け一問一答プログラム");
        title.setBounds(120, 30, 600, 30);
        title.setFont(new Font("Arias", Font.PLAIN, 28));
        this.add(title);

        // スタート用ボタン名前設定
        start.setText("スタート");

        // スタート用ボタンの配置座標指定
        start.setBounds(50,270, 650, 120);

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