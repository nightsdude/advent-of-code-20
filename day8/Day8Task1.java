import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class Day8Task1 {
    public static void main(String[] args) {
        BufferedReader reader = null;
		try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
			reader = new BufferedReader(new FileReader(
                    filePath));
            
            List<String> instructions = new ArrayList<String>();
            String line = reader.readLine();
			while (line != null) {
                instructions.add(line);
                line = reader.readLine();
            }
            
            System.out.println(runInstructionsAndReturnAccValueBeforeRepeatingAnyInstruction(instructions));            
		} catch (IOException e) {
			e.printStackTrace();
        } finally {
            try {reader.close();} catch(IOException e) {e.printStackTrace();}
        }
    }

    private static int runInstructionsAndReturnAccValueBeforeRepeatingAnyInstruction(List<String> instructions) {
        Set<Integer> executedInstructionIndices = new HashSet<Integer>();
        
        int accumulator = 0;
        int instructionIndex = 0;
        while(!executedInstructionIndices.contains(instructionIndex)) {
            String[] instructionTokens = instructions.get(instructionIndex).split(" ");
            String operation = instructionTokens[0];
            int value = Integer.parseInt(instructionTokens[1]);

            executedInstructionIndices.add(instructionIndex);

            if (operation.equals("acc")) {
                accumulator += value;
                instructionIndex++;
            } else if (operation.equals("jmp")) {
                instructionIndex += value;
            } else if (operation.equals("nop")) {
                instructionIndex++;
            }
        }

        return accumulator;
    }
}