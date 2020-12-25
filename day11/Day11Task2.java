import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day11Task2 {
    public enum Direction {
        LEFT_UP_DIAGONAL,
        UP,
        RIGHT_UP_DIAGONAL,
        LEFT,
        RIGHT,
        LEFT_DOWN_DIAGONAL,
        DOWN,
        RIGHT_DOWN_DIAGONAL
    }

    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            String filePath = new File("").getAbsolutePath() + "/input.txt";
            reader = new BufferedReader(new FileReader(filePath));

            char[][] floorPlan = new char[91][99];

            int row = 0;
            String line = reader.readLine();
            while (line != null) {
                floorPlan[row] = readRow(line);
                line = reader.readLine();
                row++;
            }
            
            boolean updated;
            do {
                printFloorPlan(floorPlan);
                char[][] updatedFloorPlan = update(floorPlan);
                updated = !floorPlansEqual(floorPlan, updatedFloorPlan);
                floorPlan = updatedFloorPlan;
            } while (updated);

            System.out.println(countOccupiedSeats(floorPlan));
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

    private static char[] readRow(String row) {
        char[] rowPlan = new char[99];
        for (int i = 0; i < row.length(); i++) {
            rowPlan[i] = row.charAt(i);
        }

        return rowPlan;
    }

    private static char[][] update(char[][] floorPlan) {
        int height = 91;
        int length = 99;
        char[][] updatedFloorPlan = new char[height][length];

        for (int i = 0; i < height; i++) {
            for(int j = 0; j < length; j++) {
                if (floorPlan[i][j] == '.') {
                    updatedFloorPlan[i][j] = '.';
                    continue;
                }

                int occupiedAdjSeatCount = 0;
                for (Direction direction : Direction.values()) {
                    occupiedAdjSeatCount += getDirectionStatus(floorPlan, direction, i, j);
                }
                
                char updatedSeat = updateSeat(floorPlan[i][j], occupiedAdjSeatCount);
                updatedFloorPlan[i][j] = updatedSeat;
            }
        }

        return updatedFloorPlan;
    }

    private static int getDirectionStatus(char[][] floorPlan, Direction direction, int i, int j) {
        int xOffset = 0;
        int yOffset = 0;

        switch(direction) {
            case LEFT_UP_DIAGONAL:
                yOffset = 1;
                xOffset = -1;
                break;
            case UP:
                yOffset = 1;
                xOffset = 0;
                break;
            case RIGHT_UP_DIAGONAL:
                yOffset = 1;
                xOffset = 1;
                break;
            case LEFT:
                yOffset = 0;
                xOffset = -1;
                break;
            case RIGHT:
                yOffset = 0;
                xOffset = 1;
                break;
            case LEFT_DOWN_DIAGONAL:
                yOffset = -1;
                xOffset = -1;
                break;
            case DOWN:
                yOffset = -1;
                xOffset = 0;
                break;
            case RIGHT_DOWN_DIAGONAL:
                yOffset = -1;
                xOffset = 1;
                break;
            default:
                break;
        }

        int directionStatus = 0;
        for (int y = yOffset, x = xOffset;
            y + i >= 0 && y + i < 91 && x + j >= 0 && x + j < 99; 
            y += yOffset, x += xOffset) {
            if (floorPlan[i + y][j + x] == '#') {
                directionStatus = 1;
                break;
            } else if (floorPlan[i + y][j + x] == 'L') {
                break;
            }

        }

        return directionStatus;
    }

    private static char updateSeat(char seat, int occupiedAdjSeatCount) {
        switch(seat) {
            case '.':
                return '.';
            case '#':
                if (occupiedAdjSeatCount >= 5) {
                    return 'L';
                } else {
                    return '#';
                }
            case 'L':
                if (occupiedAdjSeatCount == 0) {
                    return '#';
                } else {
                    return 'L';
                }
            default:
                return '.';
        }
    }

    private static int countOccupiedSeats(char[][] floorPlan) {
        int height = 91;
        int length = 99;
        int count = 0;

        for (int i = 0; i < height; i++) {
            for(int j = 0; j < length; j++) {
                if (floorPlan[i][j] == '#') {
                    count++;
                }
            }
        }
        
        return count;
    }

    private static void printFloorPlan(char[][] floorPlan) {
        for(int i = 0; i < 91; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < floorPlan[i].length; j++) {
                row.append(floorPlan[i][j]);
            }

            System.out.println(row.toString());
        }
    }
    
    private static boolean floorPlansEqual(char[][] floorPlan1, char[][] floorPlan2) {
        int height = 91;
        int length = 99;
        boolean equal = true;

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (floorPlan1[i][j] != floorPlan2[i][j]) {
                    equal = false;
                    break;
                }
            }

            if (!equal) {
                break;
            }
        }

        return equal;
    }
}