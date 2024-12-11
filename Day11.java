import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11{
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");
        System.out.println("Part 1: " + doPartOne(fileData));
        System.out.println("Part 2: " + doPartTwo(fileData));
    }

    public static int doPartOne(ArrayList<String> fileData){
        String[] tempStones = fileData.get(0).split(" ");
        ArrayList<String> stones = new ArrayList<>();
        for (String stone : tempStones) stones.add(stone);
        int blinks = 25;
        for (int blink = 0; blink < blinks; blink++){
            for (int stone = 0; stone < stones.size(); stone++){
                String currentStone = stones.get(stone);
                if (Long.parseLong(currentStone) == 0) stones.set(stone, String.valueOf(1));
                else if (currentStone.length() % 2 == 0){
                    long stoneRight = Long.parseLong(currentStone.substring(currentStone.length()/2));
                    stones.set(stone, String.valueOf(stoneRight));
                    stones.add(stone, currentStone.substring(0, currentStone.length()/2));
                    stone++;
                }
                else stones.set(stone, String.valueOf(Long.parseLong(currentStone) * 2024));
            }
        }
        return stones.size();
    }
    
    public static int doPartTwo(ArrayList<String> fileData){
        String[] tempStones = fileData.get(0).split(" ");
        ArrayList<String> stones = new ArrayList<>();
        for (String stone : tempStones) stones.add(stone);
        int blinks = 75;
        for (int blink = 0; blink < blinks; blink++){
            for (int stone = 0; stone < stones.size(); stone++){
                String currentStone = stones.get(stone);
                if (Long.parseLong(currentStone) == 0) stones.set(stone, String.valueOf(1));
                else if (currentStone.length() % 2 == 0){
                    long stoneRight = Long.parseLong(currentStone.substring(currentStone.length()/2));
                    stones.set(stone, String.valueOf(stoneRight));
                    stones.add(stone, currentStone.substring(0, currentStone.length()/2));
                    stone++;
                }
                else stones.set(stone, String.valueOf(Long.parseLong(currentStone) * 2024));
            }
        }
        return stones.size();
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}
