import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13Task2 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
            reader = new BufferedReader(new FileReader(filePath));

            int earliestTime = Integer.parseInt(reader.readLine()); // ignored
            String[] busLinesInput = reader.readLine().split(",");

            Map<Integer, Integer> busLinesByMinuteOffsets = IntStream.range(0, busLinesInput.length)
                .boxed()
                .filter(index -> !busLinesInput[index].equals("x"))
                .collect(Collectors.toMap(index -> index, index -> Integer.parseInt(busLinesInput[index])));

            System.out.println(findTheContestTime(busLinesByMinuteOffsets));
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

    private static long findTheContestTime(Map<Integer, Integer> busLines) {
        long time = 100_000_000_000_265L; // 100000000000004L

        int greatestBusLineKey = findKeyOfGreatestValue(busLines);

        boolean allSatisifiedConidition = false;
        while(!allSatisifiedConidition) {
            allSatisifiedConidition = true;
            for (Integer offset : busLines.keySet()) {
                if ((time - greatestBusLineKey + offset) % busLines.get(offset) != 0) {
                    allSatisifiedConidition = false;
                }
            }
            System.out.print(time + "\r");

            time += busLines.get(greatestBusLineKey);
        }

        System.out.println();
        return time;
    }

    private static int findKeyOfGreatestValue(Map<Integer, Integer> map) {
        int keyOfGreatestValue = 0;
        int greatestValue = Integer.MIN_VALUE;

        for (Integer n : map.keySet()) {
            if (map.get(n) > greatestValue) {
                greatestValue = map.get(n);
                keyOfGreatestValue = n;
            }
        }

        return keyOfGreatestValue;
    }
}