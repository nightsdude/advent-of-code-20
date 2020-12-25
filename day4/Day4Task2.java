import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day4Task2 {
    public static void main(String[] args) {
        BufferedReader reader = null;
		try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
			reader = new BufferedReader(new FileReader(
                    filePath));

            int validCount = 0;
                    
            String line = reader.readLine();
			while (line != null) {
                if(passportDataIsValid(readPassportData(reader, line))) {
                    validCount++;
                }
                line = reader.readLine();
            }
            
            System.out.println(validCount);

		} catch (IOException e) {
			e.printStackTrace();
        } finally {
            try {reader.close();} catch(IOException e) {e.printStackTrace();}
        }
    }

    public static String[] readPassportData(BufferedReader reader, String line) {
        StringBuilder rawData = new StringBuilder();

        try {
            while (line != null && !line.isEmpty()) {
                rawData.append(line).append(" ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawData.toString().split("\\s+");
    }

    public static boolean passportDataIsValid(String[] data) {
        Set<String> fieldKeysToBeChecked = Stream.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid").collect(Collectors.toSet());

        for (String field : data) {
            String[] tokens = field.split(":");
            String fieldKey = tokens[0];
            String fieldValue = tokens[1];

            if (fieldKeysToBeChecked.contains(fieldKey)) {
                fieldKeysToBeChecked.remove(fieldKey);
            }

            if (fieldKey.equals("byr") && !fieldValue.matches("19[2-9][0-9]|200[0-2]")) {
                return false;
            }
            if (fieldKey.equals("iyr") && !fieldValue.matches("20(1[0-9]|20)")) {
                return false;
            }
            if (fieldKey.equals("eyr") && !fieldValue.matches("20(2[0-9]|30)")) {
                return false;
            }
            if (fieldKey.equals("hgt") && !fieldValue.matches("1([5-8][0-9]|9[0-3])cm|(59|6[0-9]|7[0-6])in")) {
                return false;
            }
            if (fieldKey.equals("hcl") && !fieldValue.matches("#([0-9a-f]{6})")) {
                return false;
            }
            if (fieldKey.equals("ecl") && !fieldValue.matches("amb|blu|brn|gry|grn|hzl|oth")) {
                return false;
            }
            if (fieldKey.equals("pid") && !fieldValue.matches("[0-9]{9}")) {
                return false;
            }
        }

        if (fieldKeysToBeChecked.size() == 0) {
            return true;
        }

        return false;
    }
}