import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day14Task2 {
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

            char[] maskedAddress = applyMask(mask, address);

            Set<Long> addresses = new HashSet<Long>();
            getAllAddresses(maskedAddress, addresses);

            for (Long maskedAddressCombination : addresses) {
                memory.put(maskedAddressCombination, value);
            }
            
        }

        return mask;
    }

    private static char[] applyMask(String mask, long number) {
        char[] binaryChars = String.format("%36s", Long.toBinaryString(number)).replace(' ', '0').toCharArray();

        for (int bitIndex = 0; bitIndex < mask.length(); bitIndex++) {
            char maskBit = mask.charAt(bitIndex);
            if (maskBit == '1') {
                binaryChars[bitIndex] = '1';
            } else if (maskBit == 'X') {
                binaryChars[bitIndex] = 'X'; 
            }
        }
        
        return binaryChars;
    }

    private static void getAllAddresses(char[] maskedAddress, Set<Long> addresses) {
        int index;

        for(index = 0; index < maskedAddress.length; index++) {
            if (index == maskedAddress.length - 1 && maskedAddress[index] != 'X') {
                addresses.add(Long.parseLong(new String(maskedAddress), 2));
                return;
            }

            if (maskedAddress[index] == 'X') {
                break;
            }
        }

        maskedAddress[index] = '0';
        getAllAddresses(Arrays.copyOf(maskedAddress, 36), addresses);

        maskedAddress[index] = '1';
        getAllAddresses(Arrays.copyOf(maskedAddress, 36), addresses);
    }
}