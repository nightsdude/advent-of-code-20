import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Day9Task2 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
            reader = new BufferedReader(new FileReader(filePath));

            List<Long> numbers = new ArrayList<Long>();

            String line = reader.readLine();

            while (line != null) {
                numbers.add(Long.parseLong(line));
                line = reader.readLine();
            }

            Pair<Integer, Integer> range = findTheRangeThatSumsTo(numbers, 25918798);
            
            long smallest = findSmallest(numbers.subList(range.getFirst(), range.getSecond() + 1));
            long largest = findGreatest(numbers.subList(range.getFirst(), range.getSecond() + 1));

            System.out.println(smallest + largest);
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

    public static Pair<Integer, Integer> findTheRangeThatSumsTo(List<Long> numbers, int desiredSum) {
        for (int i = 0; i < numbers.size(); i++) {
            int currentSum = 0;

            for (int j = i; j < numbers.size(); j++) {
                currentSum += numbers.get(j);

                if (currentSum == desiredSum) {
                    return new Pair<Integer, Integer>(i, j);
                } else if (currentSum > desiredSum) {
                    break;
                }
            }
        }
        
        return new Pair<Integer, Integer>(0, 0);
    }

    public static long findSmallest(List<Long> numbers) {
        long smallest = Long.MAX_VALUE;

        for (long number : numbers) {
            if (number < smallest) {
                smallest = number;
            }
        }
        
        return smallest;
    }

    public static long findGreatest(List<Long> numbers) {
        long greatest = Long.MIN_VALUE;

        for (long number : numbers) {
            if (number > greatest) {
                greatest = number;
            }
        }

        return greatest;
    }
}
