import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day15Task1 {
    public static void main(String[] args) {
        Map<Integer, Integer> numberToRound = Stream.of(new Object[][] { 
            { 5, 1 }, 
            { 1, 2 }, 
            { 9, 3 },
            { 18, 4 },
            { 13, 5 },
            { 8, 6 },
            { 0, 7 }
        }).collect(Collectors.toMap(numberAndRound -> (Integer) numberAndRound[0], numberAndRound -> (Integer) numberAndRound[1]));

        int round = 7;
        int spokenNumber = 0;

        while (round != 2020) {
            spokenNumber = nextRound(++round, spokenNumber, numberToRound);
        }

        System.out.println(spokenNumber);
    }

    private static int nextRound(int round, int previouslySpoken, Map<Integer, Integer> numberToRound) {
        if (numberToRound.containsKey(previouslySpoken)) {
            int spokenNumber = round - 1 - numberToRound.get(previouslySpoken);
            numberToRound.put(previouslySpoken, round - 1);
            return spokenNumber;
        }

        numberToRound.put(previouslySpoken, round - 1);
        return 0;
    }
}