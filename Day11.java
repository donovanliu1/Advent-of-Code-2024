import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Day11{
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("src/SMALLinput.txt");
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
    
    public static long doPartTwo(ArrayList<String> fileData){
        long total = 0;
        String[] tempStones = fileData.get(0).split(" ");
        HashMap<Long, Long> stones = new HashMap<>();
        HashMap<Long, Long> stones2 = new HashMap<>();
        for (String stone : tempStones){
            stones.put(Long.parseLong(stone), 1L);
//            stones.put(Long.parseLong(stone), 1L);
        }
        int blinks = 25;
        System.out.println(stones);
        for (int blink = 0; blink < blinks; blink++){
            for (long stone : stones.keySet()){
                if (stone == 0){
                    stones2.put(1L, stones.get(stone));
                    stones.put(0L, 0L);
                }
                else if (String.valueOf(stone).length() % 2 == 0){
                    String stoneString = String.valueOf(stone);
                    long stoneLeft = Long.parseLong(stoneString.substring(0, stoneString.length()/2));
                    long stoneRight = Long.parseLong(stoneString.substring(stoneString.length()/2));
                    stones2.put(stoneLeft, stones.get(stone));
                    stones2.put(stoneRight, stones.get(stone));
                    stones.put(stone, 0L);
                }
                else{
                    stones2.put(stone * 2024, stones.get(stone));
                    stones.put(stone, 0L);
                }
            }
            for (long stone : stones2.keySet()){
                stones.put(stone, stones2.get(stone));
            }
            System.out.println(stones);
            stones2.clear();
        }
        for (long stone : stones.keySet()){
            total += stones.get(stone);
        }
        return total;
//        String[] tempStones = fileData.get(0).split(" ");
//        ArrayList<String> stones = new ArrayList<>();
//        for (String stone : tempStones) stones.add(stone);
//        int blinks = 75;
//        for (int blink = 0; blink < blinks; blink++){
//            for (int stone = 0; stone < stones.size(); stone++){
//                String currentStone = stones.get(stone);
//                if (Long.parseLong(currentStone) == 0) stones.set(stone, String.valueOf(1));
//                else if (currentStone.length() % 2 == 0){
//                    long stoneRight = Long.parseLong(currentStone.substring(currentStone.length()/2));
//                    stones.set(stone, String.valueOf(stoneRight));
//                    stones.add(stone, currentStone.substring(0, currentStone.length()/2));
//                    stone++;
//                }
//                else stones.set(stone, String.valueOf(Long.parseLong(currentStone) * 2024));
//            }
//        }
//        return stones.size();
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
