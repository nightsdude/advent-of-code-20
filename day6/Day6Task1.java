import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;


public class Day6Task1 {
    public static void main(String[] args) {
        BufferedReader reader = null;
		try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
			reader = new BufferedReader(new FileReader(
                    filePath));
            
            int yesCount = 0;
                    
            String line = reader.readLine();
			while (line != null) {
                StringBuilder groupAnswers = new StringBuilder();
                while (line != null && !line.isEmpty()) {
                    groupAnswers.append(line);
                    line = reader.readLine();
                }
                yesCount += getGroupYesCount(groupAnswers.toString());
    
                line = reader.readLine();
            }
            
            System.out.println(yesCount);

		} catch (IOException e) {
			e.printStackTrace();
        } finally {
            try {reader.close();} catch(IOException e) {e.printStackTrace();}
        }
    }

    public static int getGroupYesCount(String line) {
        Set<Character> questionsAnsweredYes = new HashSet<Character>();
        for (int i = 0; i < line.length(); i++) {
            questionsAnsweredYes.add(line.charAt(i));
        }

        return questionsAnsweredYes.size();
    }
}