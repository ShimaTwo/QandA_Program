// 問題画面用のサブパネル

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class SubPanel extends JPanel {
    // 改行コード指定
    String br = System.getProperty("line.separator");

    // 問題番号
    JLabel questionIndex = new JLabel();
    // 解答ボタン
    JButton answer = new JButton();
    // 問題タイトル
    JLabel questionTitle = new JLabel();
    // 問題文
    JLabel[] questionSentence;
    JLabel[] questionSentenceLabel;
    // 問題文添付画像
    JLabel questionImage = new JLabel();
    // 解答用テキストボックス
    JTextField[] answerTextField;
    JLabel[] answerTextLabel;
    // 正解配列
    String[] correctAnswer;
    // 順不同orNot(順不同ならfalse)
    Boolean perfectOrder = true;

    public SubPanel (int width, int height, String[] QALine, int nowIndex, int nextIndex, MyFrame mf) {
        // サブパネル用アクションリスナー
        SubPanelActionListener sal = new SubPanelActionListener(mf, this, nowIndex, nextIndex);

        // パネル設定
        this.setName("SubPanel");
        this.setSize(width, height);
        this.setLayout(null);

        // 問題番号格納
        // test print
        //System.out.println(QALine[0]);
        questionIndex.setText(QALine[0]);
        questionIndex.setBounds(10, 10, 125, 25);
        questionIndex.setFont(new Font("Arias", Font.BOLD, 28));
        this.add(questionIndex);

        // 解答ボタン設定
        answer.setText("回答");
        answer.setBounds(582, 425, 150, 50);
        answer.addActionListener(sal);
        this.add(answer);

        // 問題タイトル
        questionTitle.setText(QALine[1]);
        questionTitle.setBounds(10, 40, 300, 25);
        questionTitle.setFont(new Font("Arias", Font.PLAIN, 24));
        // test print
        // questionTitle.setBackground(Color.RED);
        // questionTitle.setOpaque(true);
        this.add(questionTitle);

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

        // 順不同判定
        perfectOrder = checkParenthesis(QALine[4]);

        // 解答用テキストボックス
        String[] answerArray = splitToArray(QALine[2]);
        // test print
        // System.out.println(QALine[3]);
        answerTextField = new JTextField[answerArray.length];
        answerTextLabel = new JLabel[answerArray.length];
        for (int i = 0; i < answerTextField.length; i++) {
            answerTextField[i] = new JTextField();
            answerTextLabel[i] = new JLabel("回答"+String.valueOf(i+1));
            answerTextField[i].setBounds(30+140*i, 400, 120, 25);
            answerTextLabel[i].setBounds(30+140*i, 375, 120, 25);
            this.add(answerTextField[i]);
            this.add(answerTextLabel[i]);
        }

        // 正解の配列
        correctAnswer = splitToArray(QALine[4]);
    }

    // 解答が[]区切りか()区切りかを判定
    public Boolean checkParenthesis(String answerLine) {
        Boolean retBool = true;
        if (answerLine.substring(0,1).equals("{") && answerLine.substring(answerLine.length()-1,answerLine.length()).equals("}")) {
            retBool = false;
        }
        return retBool;
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
        // test print
        for (int i = 0; i < retArray.length; i++) {
            // System.out.println(retArray[i]);
        }
        return retArray;
    }
}

// サブパネル用アクションリスナー
class SubPanelActionListener implements ActionListener {
    // フレーム
    MyFrame mf;
    // サブパネル
    SubPanel sp;
    // 今のインデックス
    int now;
    // 次のインデックス
    int next;

    SubPanelActionListener (MyFrame myFrame, SubPanel subPanel, int nowIndex, int nextIndex) {
        mf = myFrame;
        sp = subPanel;
        now = nowIndex;
        next = nextIndex;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // test print
        // System.out.println("pushed...");
        String finalAnswer[] = new String[sp.answerTextField.length];
        for (int i = 0; i < sp.answerTextField.length; i++) {
            finalAnswer[i] = sp.answerTextField[i].getText();
        }

        // 順不同が不可の場合
        Boolean correctOrNot = true;
        // test print
        // System.out.println(sp.perfectOrder);
        if (sp.perfectOrder == true) {
            for (int i = 0; i < finalAnswer.length; i++) {
                if (!finalAnswer[i].equals(sp.correctAnswer[i])) {
                    // testprint
                    // System.out.println(finalAnswer[i]);
                    // System.out.println(sp.correctAnswer[i]);
                    correctOrNot = false;
                }
            }
        } else {
            correctOrNot = false;
            Boolean checkBool[] = new Boolean[finalAnswer.length];
            ArrayList<Integer> deadIndex = new ArrayList<Integer>();
            for (int i = 0; i < checkBool.length; i++) {
                checkBool[i] = false;
            }
            for (int i = 0; i < finalAnswer.length; i++) {
                for (int j = 0; j < sp.correctAnswer.length; j++) {
                    if (finalAnswer[i].equals(sp.correctAnswer[j]) && deadChecker(j, deadIndex) == true) {
                        checkBool[i] = true;
                        deadIndex.add(j);
                    }
                }
            }
            Boolean allCheck = true;
            for (int i = 0; i < checkBool.length; i++) {
                if (checkBool[i]==false){
                    allCheck = false;
                }
            }
            correctOrNot = allCheck;
        }

        if (correctOrNot == true) {
            // test print
            // System.out.println("正解");
            mf.setResultPanelVisible(true, now);
        } else {
            // test print
            // System.out.println("不正解");
            mf.setResultPanelVisible(false, now);
        }
    }

    // 重複ありならfalse
    public Boolean deadChecker(int target, ArrayList<Integer> list) {
        Boolean retBool = true;
        for (int i = 0; i < list.size(); i++) {
            if (target == list.get(i)) {
                retBool = false;
            }
        }
        return retBool;
    }
}