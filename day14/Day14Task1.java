import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14Task1 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
            reader = new BufferedReader(new FileReader(filePath));

            String mask = "";
            Map<Long, Long> memory = new HashMap<Long, Long>();

            String line = reader.readLine();
            while (line != null) {
                mask = executeLine(line, mask, memory);
                line = reader.readLine();
            }

            long sum = 0;
            for (Long value : memory.values()) {
                sum += value;
            }

            System.out.println(sum);
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

    private static String executeLine(String line, String mask, Map<Long, Long> memory) {
        String[] tokens = line.split(" ");

        if (tokens[0].equals("mask")) {
            return tokens[2];
        } else {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(line);

            m.find();
            long address = Long.parseLong(m.group(0));

            m.find();
            long value = Long.parseLong(m.group(0));

            long actualValue = applyMask(mask, value);
            memory.put(address, actualValue);
        }

        return mask;
    }

    private static long applyMask(String mask, long number) {
        for (int bitIndex = 0; bitIndex < mask.length(); bitIndex++) {
            char maskBit = mask.charAt(mask.length() - 1 - bitIndex);
            if (maskBit == '1') {
                number = number | ((long)1 << bitIndex); // set a bit to 1
            } else if (maskBit == '0') {
                number &= ~((long)1 << bitIndex); // set a bit to 0
            }
        }
        return number;
    }
}