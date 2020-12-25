import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;


public class Day7Task1 {
    public static void main(String[] args) {
        BufferedReader reader = null;
		try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
			reader = new BufferedReader(new FileReader(
                    filePath));
            
            Map<String, Set<String>> bagToContainers = new HashMap<String, Set<String>>();
                    
            String line = reader.readLine();
			while (line != null) {
                addBagRule(bagToContainers, line);
                line = reader.readLine();
            }
            
            System.out.println(getCompatibleBagsCount(bagToContainers));

		} catch (IOException e) {
			e.printStackTrace();
        } finally {
            try {reader.close();} catch(IOException e) {e.printStackTrace();}
        }
    }

    public static void addBagRule(Map<String, Set<String>> bagToContainers, String line) {
        String[] bags = line.split("bags contain \\d|bag(s|), \\d|bag(s|)\\.");

        for (int i = 1; i < bags.length; i++) {
            String bag = bags[i].trim();
            if (bagToContainers.containsKey(bag)) {
                Set<String> containerSet = bagToContainers.get(bag);
                containerSet.add(bags[0].trim());
            } else {
                Set<String> containerSet = new HashSet<String>();
                containerSet.add(bags[0].trim());
                bagToContainers.put(bag, containerSet);
            }
        }
    }

    public static int getCompatibleBagsCount(Map<String, Set<String>> bagToContainers) {
        Set<String> updatedContainers = new HashSet<String>();
        Set<String> containers = bagToContainers.get("shiny gold");

        for (String container : containers) {
            updatedContainers.add(container);
            if (bagToContainers.containsKey(container)) {
                Set<String> containersOfContainer = bagToContainers.get(container);
                for (String containerOfContainer : containersOfContainer) {
                    updatedContainers.add(containerOfContainer);
                }
            }
        }

        if (containers.equals(updatedContainers)) {
            return containers.size();
        } else {
            bagToContainers.put("shiny gold", updatedContainers);
            return getCompatibleBagsCount(bagToContainers);
        }
    }
}