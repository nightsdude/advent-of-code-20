import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Day6Task2 {
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
                int groupMemberCount = 0;
                while (line != null && !line.isEmpty()) {
                    groupAnswers.append(line);
                    groupMemberCount++;
                    line = reader.readLine();
                }
                yesCount += getGroupYesCount(groupAnswers.toString(), groupMemberCount);
    
                line = reader.readLine();
            }
            
            System.out.println(yesCount);

		} catch (IOException e) {
			e.printStackTrace();
        } finally {
            try {reader.close();} catch(IOException e) {e.printStackTrace();}
        }
    }

    public static int getGroupYesCount(String line, int groupMemberCount) {
        Map<Character, Integer> questionsAnsweredYes = new HashMap<Character, Integer>();
        for (int i = 0; i < line.length(); i++) {
            Character question = line.charAt(i);
            if (!questionsAnsweredYes.containsKey(question)) {
                questionsAnsweredYes.put(question, 1);
            }
            else {
                questionsAnsweredYes.put(question, questionsAnsweredYes.get(question) + 1);
            }
        }
        int yesCount = 0;

        for (Map.Entry<Character, Integer> entry : questionsAnsweredYes.entrySet()) {
            if (entry.getValue() == groupMemberCount) {
                yesCount++;
            }
        }

        return yesCount;
    }
}