import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2Task2 {
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
        
        int position1 = Integer.parseInt(tokens[0]) - 1;
        int position2 = Integer.parseInt(tokens[1]) - 1;
        char ruleChar = tokens[2].charAt(0);
        String password = tokens[3];
        
        if ((password.length() > position1 && password.charAt(position1) == ruleChar) ^ 
        (password.length() > position2 && password.charAt(position2) == ruleChar)) {
            return true;
        }

        return false;
    }
}