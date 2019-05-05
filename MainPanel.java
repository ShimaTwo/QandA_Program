// メインパネル デフォルト画面

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class MainPanel extends JPanel {
    // ラジオボタン
    JRadioButton cource1 = new JRadioButton();
    JRadioButton cource2 = new JRadioButton();
    JRadioButton cource3 = new JRadioButton();
    public MainPanel (int width, int height) {
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

        // ボタンの配置座標指定
        cource1.setBounds(100, 100, 100, 25);
        cource2.setBounds(100, 125, 150, 25);
        cource3.setBounds(100, 150, 150, 25);

        // ラジオボタンをパネルに追加
        this.add(cource1);
        this.add(cource2);
        this.add(cource3);
    }
}