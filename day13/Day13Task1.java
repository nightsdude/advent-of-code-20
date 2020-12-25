import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Set;

public class Day13Task1 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
            reader = new BufferedReader(new FileReader(filePath));

            int earliestTime = Integer.parseInt(reader.readLine()); // ignored
            String[] busLinesInput = reader.readLine().split(",");

            Set<Integer> busLines = Arrays.stream(busLinesInput)
                .filter(busLine -> !busLine.equals("x"))
                .map(busLine -> Integer.parseInt(busLine))
                .collect(Collectors.toSet());

            int earliestBusLine = findTheEarliestBusLine(earliestTime, busLines);
            int waitingTime = earliestBusLine - (earliestTime % earliestBusLine);
            System.out.println(waitingTime * earliestBusLine);
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

    private static int findTheEarliestBusLine(int earliestTime, Set<Integer> busLines) {
        int time = earliestTime;
        while(true) {
            for (Integer busLine : busLines) {
                if ((time % busLine) == 0) {
                    return busLine;
                }
            }

            time++;
        }
    }
}