import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2Task1 {
    public static void main(String[] args) {
        BufferedReader reader;
		try {
            int validCount = 0;
            String filePath = new File("").getAbsolutePath() + "/input.txt";
			reader = new BufferedReader(new FileReader(
					filePath));
			String line = reader.readLine();
			while (line != null) {
                if (isPasswordValid(line)) {
                    validCount++;
                }
				line = reader.readLine();
			}
            reader.close();
            
            System.out.println(validCount);
		} catch (IOException e) {
			e.printStackTrace();
        }
    }

    public static boolean isPasswordValid(String line) {
        String[] tokens = line.split("[- ]");
        
        int lowerLimit = Integer.parseInt(tokens[0]);
        int upperLimit = Integer.parseInt(tokens[1]);
        char ruleChar = tokens[2].charAt(0);
        String password = tokens[3];
        
        int ruleCount = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == ruleChar) {
                ruleCount++;
            }
        }

        if (ruleCount >= lowerLimit && ruleCount <= upperLimit) {
            return true;
        }

        return false;
    }
}