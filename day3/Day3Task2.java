import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Day3Task2 {
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

            int treeCount1 = getTreeCountForSlope(map, 1, 1);
            int treeCount2 = getTreeCountForSlope(map, 3, 1);
            int treeCount3 = getTreeCountForSlope(map, 5, 1);
            int treeCount4 = getTreeCountForSlope(map, 7, 1);
            int treeCount5 = getTreeCountForSlope(map, 1, 2);
            
            System.out.println(treeCount1 * treeCount2 * treeCount3 * treeCount4 * treeCount5);

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

    public static int getTreeCountForSlope(Boolean[][] map, int horizontalStep, int verticalStep) {
        int treeCount = 0;

        for (int i = 0; (i * verticalStep) < map.length; i++){
            if(map[i * verticalStep][(i * horizontalStep)% map[0].length]) {
                treeCount++;
            }
        }

        return treeCount;
    }
}