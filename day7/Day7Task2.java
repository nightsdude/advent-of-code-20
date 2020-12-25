import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;


public class Day7Task2 {
    public static void main(String[] args) {
        BufferedReader reader = null;
		try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
			reader = new BufferedReader(new FileReader(
                    filePath));
            
            Map<String, Set<Pair<String, Integer>>> bagToContainers = new HashMap<String, Set<Pair<String, Integer>>>();
                    
            String line = reader.readLine();
			while (line != null) {
                addBagRule(bagToContainers, line);
                line = reader.readLine();
            }
            
            System.out.println(getContainedBagsCount(bagToContainers));

		} catch (IOException e) {
			e.printStackTrace();
        } finally {
            try {reader.close();} catch(IOException e) {e.printStackTrace();}
        }
    }

    public static void addBagRule(Map<String, Set<Pair<String, Integer>>> bagToContained, String line) {
        String[] bags = line.split("bags contain|bag(s|),|bag(s|)\\.");
        
        if (bags[1].trim().equals("no other")) {
            bagToContained.put(bags[0].trim(), null);
            return;
        }

        Set<Pair<String, Integer>> bagsContained = new HashSet<Pair<String, Integer>>();
        for (int i = 1; i < bags.length; i++) {
            String[] tokens = bags[i].trim().split(" ");
            Integer quantity = Integer.parseInt(tokens[0]);
            tokens[0] = "";
            String bag = String.join(" ", tokens).trim();

            Pair<String, Integer> bagContainedWithCount = new Pair<String, Integer>(bag, quantity);
            bagsContained.add(bagContainedWithCount);
        }

        bagToContained.put(bags[0].trim(), bagsContained);
    }

    public static int getContainedBagsCount(Map<String, Set<Pair<String, Integer>>> bagToContained) {
        Set<Pair<String, Integer>> containedBags = bagToContained.get("shiny gold");

        return getContainedBagsCount(bagToContained, containedBags);
    }

    public static int getContainedBagsCount(Map<String, Set<Pair<String, Integer>>> bagToContained, Set<Pair<String, Integer>> containedBags) {
        if (containedBags == null) {
            return 1;
        }
        
        int containedBagsCount = 0; 
        for (Pair<String, Integer> containedBag : containedBags) {
            containedBagsCount += containedBag.getSecond() + containedBag.getSecond() * getContainedBagsCount(bagToContained, bagToContained.get(containedBag.getFirst()));
        }

        return containedBagsCount;
    }
}