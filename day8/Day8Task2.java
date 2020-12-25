import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Day8Task2 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
            reader = new BufferedReader(new FileReader(filePath));

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
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int runInstructionsAndReturnAccValueBeforeRepeatingAnyInstruction(List<String> instructions) {
        List<Integer> instructionsToReplace = IntStream.range(0, instructions.size())
                .filter(i -> instructions.get(i).startsWith("jmp")).boxed().collect(Collectors.toList());

        while (instructionsToReplace.size() > 0) {
            int indexToReplace = instructionsToReplace.get(instructionsToReplace.size() - 1);
            instructionsToReplace.remove(instructionsToReplace.size() - 1);
            instructions.set(indexToReplace, "nop" + instructions.get(indexToReplace).substring(3));
            Map<Integer, Integer> executedInstructions = new HashMap<Integer, Integer>();

            int accumulator = 0;
            int instructionIndex = 0;
            while (instructionIndex < instructions.size()
                    || executedInstructions.values().stream().anyMatch(value -> value > 100)) {
                String[] instructionTokens = instructions.get(instructionIndex).split(" ");
                String operation = instructionTokens[0];
                int value = Integer.parseInt(instructionTokens[1]);

                if (executedInstructions.containsKey(instructionIndex)) {
                    executedInstructions.put(instructionIndex, executedInstructions.get(instructionIndex) + 1);
                } else {
                    executedInstructions.put(instructionIndex, 1);
                }

                if (operation.equals("acc")) {
                    accumulator += value;
                    instructionIndex++;
                } else if (operation.equals("jmp")) {
                    instructionIndex += value;
                } else if (operation.equals("nop")) {
                    instructionIndex++;
                }
            }

            instructions.set(indexToReplace, "jmp" + instructions.get(indexToReplace).substring(3));

            if (instructionIndex >= instructions.size()) {
                return accumulator;
            }

        }
        System.out.println("Not successful");
        return 0;

    }
}