import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Day5Task2 {
    public static void main(String[] args) {
        BufferedReader reader = null;
		try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
			reader = new BufferedReader(new FileReader(
                    filePath));

            boolean[] occupiedSeats = new boolean[823];
                    
            String line = reader.readLine();
			while (line != null) {
                occupiedSeats[getPassId(line)] = true;
                line = reader.readLine();
            }
            
            for (int i = 0; i < occupiedSeats.length; i++) {
                if (!occupiedSeats[i]) {
                    System.out.println(i);
                }
            }

		} catch (IOException e) {
			e.printStackTrace();
        } finally {
            try {reader.close();} catch(IOException e) {e.printStackTrace();}
        }
    }

    public static int getPassId(String line) {
        StringBuilder rowInBinary = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            char currentChar = line.charAt(i);
            if (currentChar == 'B') {
                rowInBinary.append('1');
            } else if (currentChar == 'F') {
                rowInBinary.append('0');
            }
        }
        
        StringBuilder columnInBinary = new StringBuilder();
        for (int i = 7; i < 10; i++) {
            char currentChar = line.charAt(i);
            if (currentChar == 'R') {
                columnInBinary.append('1');
            } else if (currentChar == 'L') {
                columnInBinary.append('0');
            }
        }

        return Integer.parseInt(rowInBinary.toString(), 2) * 8 + 
            Integer.parseInt(columnInBinary.toString(), 2);
    }
}