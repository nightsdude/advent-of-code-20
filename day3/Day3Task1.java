import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Day3Task1 {
    public static void main(String[] args) {
        BufferedReader reader;
		try {
            int mapSize = 323;
            int rowSize = 0;

            Boolean[][] map = new Boolean[mapSize][];

            String filePath = new File("").getAbsolutePath() + "/input.txt";
			reader = new BufferedReader(new FileReader(
					filePath));
            String line = reader.readLine();
            int index = 0;
			while (line != null) {
                Boolean[] row = parseMapRow(line);
                rowSize = row.length;
                map[index] = row;
                line = reader.readLine();
                index++;
			}
            reader.close();

            int treeCount = 0;

            for (int i = 0; i < mapSize; i++) {
                if(map[i][(i * 3)% rowSize] == true) {
                    treeCount++;
                }
            }
            
            System.out.println(treeCount);

		} catch (IOException e) {
			e.printStackTrace();
        }
    }

    public static Boolean[] parseMapRow(String line) {
        List<Boolean> list = new ArrayList<Boolean>();
        for(int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '#') {
                list.add(true);
            } else if (line.charAt(i) == '.') {
                list.add(false);
            } else {
                break;
            }
        }
        
        return list.toArray(new Boolean[list.size()]);
    }
}