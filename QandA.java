// 一問一答プログラム

public class QandA {
    public static void main(String args[]) {
        // 問いと解答のcsvファイルを読み込み
        String[][] QASheet = CSVFileReader.readCSVSheet("QandA_Sheet.csv");
        // test print
        for (int i = 0; i < QASheet.length; i++) {
            for (int j = 0; j < QASheet[i].length; j++) {
                System.out.println(QASheet[i][j]);
            }
        }
    }
}