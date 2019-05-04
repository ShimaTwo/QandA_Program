
// csvファイル読み込み

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVFileReader {
    public static String[][] readCSVSheet(String csvFilePath) {
        ArrayList<String[]> retArrayList = new ArrayList<String[]>();
        try {
            Scanner sc = new Scanner(new File(csvFilePath));
            String[] line;

            // ヘッダ読み飛ばし
            sc.nextLine();

            // csvファイル読み込み
            while (sc.hasNextLine()) {
                line = sc.nextLine().split(",");
                retArrayList.add(line);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // csvファイルが空の時の処理
        if (retArrayList.size() == 0) {
            System.out.println("no data");
            System.exit(0);
        }

        // リストを配列にして返す
        return retArrayList.toArray(new String[retArrayList.size()][retArrayList.get(0).length]);
    }
}