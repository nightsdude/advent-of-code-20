import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 {
    public static boolean binarySearch(List<Integer> list, int searchedNumber, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return false;
        }

        int midIndex = (endIndex - startIndex) / 2 + startIndex;
        
        int midElement = list.get(midIndex);
        if (midElement == searchedNumber) {
            return true;
        } else if (midElement > searchedNumber) {
            return binarySearch(list, searchedNumber, startIndex, midIndex - 1);
        } else {
            return binarySearch(list, searchedNumber, midIndex + 1, endIndex);
        }
    }

    public static void main(String[] args) {
        BufferedReader reader;
        List<Integer> list = new ArrayList<Integer>();
		try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
			reader = new BufferedReader(new FileReader(
					filePath));
			String line = reader.readLine();
			while (line != null) {
                list.add(Integer.parseInt(line));
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        Collections.sort(list);

        int desiredSum = 2020;
        int number1 = 0;
        int number2 = 0;
        int number3 = 0;

        for (int i = 0; i < list.size(); i++) {
            number1 = list.get(i);
            int desiredSumToNum1 = desiredSum - number1;
            for (int j = 0; j < list.size(); j++) {
                number2 = list.get(j);
                number3 = desiredSumToNum1 - number2;
                if (number3 < 1) {
                    continue;
                }

                if (binarySearch(list, number3, j + 1, list.size() - 1)) {
                    break;
                }
            }
            if (number3 > 0 && (number1 + number2 + number3) == desiredSum) {
                break;
            }
        }

        System.out.println(number1);
        System.out.println(number2);
        System.out.println(number3);
        System.out.println(number1 * number2 * number3);
    }
}