// 一問一答プログラム

public class QandA {
    public static void main(String args[]) {
        // 問いと解答のcsvファイルを読み込み
        String[][] QASheet = CSVFileReader.readCSVSheet("QandA_Sheet.csv");
        // test print
        /*
        for (int i = 0; i < QASheet.length; i++) {
            for (int j = 0; j < QASheet[i].length; j++) {
                System.out.println(QASheet[i][j]);
            }
        }
        */
        // ウィンドウのサイズ
        int windowSizeWidth = 1500;
        int windowSizeHeight = 1000;

        MainPanel mp = new MainPanel(windowSizeWidth, windowSizeHeight);

        MyFrame mf = new MyFrame("QandA Program", windowSizeWidth, windowSizeHeight);
        // xを押した場合の処理を設定
        mf.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
}