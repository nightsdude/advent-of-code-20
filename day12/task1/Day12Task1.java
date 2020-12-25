import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day12Task1 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
            reader = new BufferedReader(new FileReader(filePath));

            Position position = new Position();
            String line = reader.readLine();
            while (line != null) {
                executeLine(position, line);
                line = reader.readLine();
            }

            System.out.println(position.getManhattanDistance());
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

    public static void executeLine(Position position, String line) {
        Position.Command command = Position.Command.valueOf(Character.toString(line.charAt(0)));
        int value = Integer.parseInt(line.substring(1));

        position.executeCommand(command, value);
    }
}