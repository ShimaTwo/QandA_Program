// 一問一答プログラム

import java.util.Random;

public class QandA {
    static String[][] QASheet;
    public static void main(String args[]) {
        // 問いと解答のcsvファイルを読み込み
        QASheet = CSVFileReader.readCSVSheet("QandA_Sheet.csv");
        // test print
        /*
        for (int i = 0; i < QASheet.length; i++) {
            for (int j = 0; j < QASheet[i].length; j++) {
                System.out.println(QASheet[i][j]);
            }
        }
        */
        // ウィンドウのサイズ
        int windowSizeWidth = 750;
        int windowSizeHeight = 500;

        MyFrame mf = new MyFrame("QandA Program", windowSizeWidth, windowSizeHeight, QASheet);
        // xを押した場合の処理を設定
        mf.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }

    // 出題順の配列を、QAシートの順番通りで返す
    public static int[] setQuestionIndexOrder() {
        int retArray[] = new int[QASheet.length];
        for (int i = 0; i < QASheet.length; i++) {
            retArray[i] = i;
        }
        return retArray;
    }

    // 出題順の配列を、ランダムな順番で返す
    public static int[] setQuestionIndexRandom() {
        int retArray[] = new int[QASheet.length];
        for (int i = 0; i < QASheet.length; i++) {
            retArray[i] = i;
        }
        
        // 配列並べ替え
        for (int i = 0; i < retArray.length; i++) {
            int random = (int) (Math.random() * retArray.length);
            int tmp = retArray[i];
            retArray[i] = retArray[random];
            retArray[random] = tmp;
        }

        return retArray;
    }

    // 出題順の配列をランダムに10問選んで返す
    public static int[] setQuestionIndexsSampleTen() {
        if (QASheet.length < 10) {
            System.out.println("QASheet.csvの問が10問以下です");
            System.exit(0);
        }

        int retArray[] = new int[10];
        int randomArray[] = new int[QASheet.length];
        randomArray = setQuestionIndexRandom();
        for (int i = 0; i < retArray.length; i++) {
            retArray[i] = randomArray[i];
        }

        return retArray;
    }
}