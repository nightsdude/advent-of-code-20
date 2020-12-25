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


public class Day4Task1 {
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
            String fieldKey = field.split(":")[0];
            if (fieldKeysToBeChecked.contains(fieldKey)) {
                fieldKeysToBeChecked.remove(fieldKey);
            }
        }

        if (fieldKeysToBeChecked.size() == 0) {
            return true;
        }

        return false;
    }
}