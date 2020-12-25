import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Day10Task1 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
            reader = new BufferedReader(new FileReader(filePath));

            List<Integer> list = new ArrayList<Integer>();

            String line = reader.readLine();
            while (line != null) {
                list.add(Integer.parseInt(line));
                line = reader.readLine();
            }

            Collections.sort(list);

            for (int number : list) {
                System.out.println(number);
            }

            System.out.println();
            System.out.println(getSingleJoltDifferencesMultipliedBy3JoltDifferences(list));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getSingleJoltDifferencesMultipliedBy3JoltDifferences(List<Integer> sortedJoltRatings) {
        int singleJoltDiffCount = 0;
        int threeJoltDiffCount = 0;

        for (int i = 0; i < sortedJoltRatings.size() - 1; i++) {
            int diff = sortedJoltRatings.get(i + 1) - sortedJoltRatings.get(i);
            if (diff == 1) {
                singleJoltDiffCount++;
            } else if (diff == 3) {
                threeJoltDiffCount++;
            }
        }

        return singleJoltDiffCount * (threeJoltDiffCount + 1);
    }
}