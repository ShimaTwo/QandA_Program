// 正解or不正解を表示するためのパネル

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BasicStroke;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.applet.Applet;

import java.util.regex.Pattern;

public class ResultPanelCorrect extends JPanel {
    // okボタン
    JButton ok = new JButton();
    // 問題番号
    JLabel questionIndex = new JLabel();
    // 問題タイトル
    JLabel questionTitle = new JLabel();
    // 問題文
    JLabel[] questionSentence;
    JLabel[] questionSentenceLabel;
    // 問題文添付画像
    JLabel questionImage = new JLabel();
    // 正解配列
    String[] correctAnswer;
    JLabel answerLabel;

    public ResultPanelCorrect(int width, int height, String[] QALine, int nowIndex, int nextIndex, MyFrame mf) {
        //  アクションリスナー
        ResultPanelActionListener rpa = new ResultPanelActionListener(this, mf, nowIndex, nextIndex);

        // パネル設定
        this.setName("ResultPanel");
        this.setSize(width, height);
        this.setLayout(null);

        // 背景色、透過度を定義
        // this.setBackground(new Color(255, 255, 255));
        // this.setOpaque(true);

        // okボタン
        ok.setText("OK");
        ok.setBounds(50, 400, 650, 60);
        ok.addActionListener(rpa);
        this.add(ok);

        // 問題番号格納
        // test print
        //System.out.println(QALine[0]);
        questionIndex.setText(QALine[0]);
        questionIndex.setBounds(10, 10, 125, 25);
        questionIndex.setFont(new Font("Arias", Font.BOLD, 28));
        this.add(questionIndex);

        // 問題タイトル
        questionTitle.setText(QALine[1]);
        questionTitle.setBounds(10, 40, 300, 25);
        questionTitle.setFont(new Font("Arias", Font.PLAIN, 24));
        // test print
        // questionTitle.setBackground(Color.RED);
        // questionTitle.setOpaque(true);
        this.add(questionTitle);

        // 問題文添付画像
        if (QALine[3].equals("0")) {
            // pass
        } else {
            ImageIcon questionIcon = new ImageIcon(getClass().getResource("image_directory/"+ QALine[3]));
            // test print
            System.out.println("/image_directory/"+ QALine[3]);
            questionImage.setIcon(questionIcon);
            questionImage.setBounds(375, 40, 400, 300);
            this.add(questionImage);
        }

        // 問題文配置設定
        String[] sentenceArray = splitToArray(QALine[2]);
        questionSentence = new JLabel[sentenceArray.length];
        questionSentenceLabel = new JLabel[sentenceArray.length];
        for (int i = 0; i < sentenceArray.length; i++) {
            questionSentence[i] = new JLabel(sentenceArray[i]);
            questionSentenceLabel[i] = new JLabel("問"+String.valueOf(i+1));
            questionSentence[i].setBounds(30, 100+50*i, 375, 75);
            questionSentenceLabel[i].setBounds(15, 100+50*i, 200, 25);
            this.add(questionSentence[i]);
            this.add(questionSentenceLabel[i]);
        }

        // 解答表示
        correctAnswer = splitToArray(QALine[4]);
        String allAnswer = "";
        for (int i = 0; i < correctAnswer.length; i++) {
            allAnswer = allAnswer + "解答"+String.valueOf(i+1)+":"+correctAnswer[i]+"      ";
        }
        answerLabel = new JLabel(allAnswer);
        answerLabel.setBounds(60, 350, 635, 40);
        answerLabel.setBackground(Color.WHITE);
        answerLabel.setOpaque(true);
        this.add(answerLabel);
    }

    // .(ドット)区切りで文字列配列を返す
    public String[] splitToArray(String answerLine) {
        String[] retArray;
        // test print
        // System.out.println(answerLine.substring(0,1));
        if (answerLine.substring(0,1).equals("{") && answerLine.substring(answerLine.length()-1,answerLine.length()).equals("}")) {
            String line = answerLine.substring(1, answerLine.length()-1);
            // test print
            // System.out.println(line);
            String[] splitLine = line.split(Pattern.quote("."));
            retArray = splitLine;
        } else if (answerLine.substring(0,1).equals("[") && answerLine.substring(answerLine.length()-1,answerLine.length()).equals("]")) {
            String line = answerLine.substring(1, answerLine.length()-1);
            // test print
            // System.out.println(line);
            String[] splitLine = line.split(Pattern.quote("."));
            retArray = splitLine;
        } else {
            retArray = new String[1];
            retArray[0] = answerLine;
        }
        return retArray;
    }
}

class ResultPanelActionListener implements ActionListener {
    // 結果パネル
    ResultPanelCorrect rpc;
    // フレーム
    MyFrame mf;
    // 現在のインデックス
    int now;
    // 次のインデックス
    int next;

    ResultPanelActionListener (ResultPanelCorrect resultPanelCorrect, MyFrame myFrame, int nowIndex, int nextIndex) {
        rpc = resultPanelCorrect;
        mf = myFrame;
        now = nowIndex;
        next = nextIndex;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mf.setResultPanelInvisible(now);
        mf.setSubPanelInvisible(now);
        mf.setSubPanelVisible(next);
    }
}