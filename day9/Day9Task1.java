import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class Day9Task1 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
            reader = new BufferedReader(new FileReader(filePath));

            Set<Integer> set = new HashSet<Integer>();
            LinkedList<Integer> linkedList = new LinkedList<Integer>();

            String line = reader.readLine();
            
            for (int i = 0; i < 25; i++) {
                int number = Integer.parseInt(line);
                linkedList.addLast(number);
                set.add(number);

                line = reader.readLine();
            }

            while (line != null) {
                int currentNumber = Integer.parseInt(line);

                if (!isSumOfPrevious25(currentNumber, set, linkedList)) {
                    System.out.println(currentNumber);
                    return;
                }

                set.remove(linkedList.removeFirst());

                set.add(currentNumber);
                linkedList.addLast(currentNumber);

                line = reader.readLine();
            }

            System.out.println();
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

    public static boolean isSumOfPrevious25(int number, Set<Integer> set, LinkedList<Integer> linkedList) {
        for (int currentNumber : linkedList) {
            if (Math.abs(number - currentNumber) != currentNumber && set.contains(Math.abs(currentNumber - number))) {
                return true;
            }
        }

        return false;
    }
}