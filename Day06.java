import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;


public class Day06 {
    public static void main(String[] args) {
        String fileName = "input.txt";
        ArrayList<String> fileData = getFileData(fileName);
        ArrayList<String[]> map = new ArrayList<>();
        for (String data : fileData){
            map.add(data.split(""));
        }
        System.out.println("Part 1: " + doPartOne(map));
        System.out.println("Part 2: " + doPartTwo(map));
    }

    public static int doPartOne(ArrayList<String[]> map){
        int count = 0;
        int[] position = findPosition(map);
        int direction = 0;
        ArrayList<String[]> copyMap = new ArrayList<>();
        for (String[] s : map){
            String[] copy = new String[s.length];
            for (int i = 0; i < copy.length; i++){
                copy[i] = s[i];
            }
            copyMap.add(copy);
        }

        while (!checkLeave(copyMap, position)){
            String currentPlace = copyMap.get(position[1])[position[0]];
            if (!currentPlace.equals("X")){
                copyMap.get(position[1])[position[0]] = "X";
                count++;
            }
            if (checkObstacle(copyMap, position, direction)){
                direction += 90;
                direction = direction % 360;
            }
            else{
                if (direction == 0) position[1]--;
                else if (direction == 90) position[0]++;
                else if (direction == 180) position[1]++;
                else if (direction == 270) position[0]--;
            }
        }
        return count;
    }

    public static int doPartTwo(ArrayList<String[]> map){
        int count = 0;
        ArrayList<String[]> markedMap = markMap(map);

        for (int r = 0; r < map.size(); r++){
            for (int c = 0; c < map.get(0).length; c++){
                if (markedMap.get(c)[r].equals("X")){
                    markedMap.get(c)[r] = "#";
                    if (checkLoop(markedMap)) count++;
                    markedMap.get(c)[r] = "X";
                }
            }
        }
        return count;
    }

    public static boolean checkLoop(ArrayList<String[]> map){
        int[] position = findPosition(map);
        int direction = 0;
        ArrayList<int[]> oldObstacles = new ArrayList<>();
        while (!checkLeave(map, position)){
            int x = position[0];
            int y = position[1];
            if (checkObstacle(map, position, direction)){
                int[] obstacle;
                if (direction == 0) obstacle = new int[]{x, y-1, direction};
                else if (direction == 90) obstacle = new int[]{x+1, y, direction};
                else if (direction == 180) obstacle = new int[]{x, y+1, direction};
                else obstacle = new int[]{x-1, y, direction};
                for (int[] obst : oldObstacles){
                    if (obstacle[0] == obst[0] && obstacle[1] == obst[1] && obstacle[2] == obst[2]) return true;
                }
                oldObstacles.add(obstacle);
                direction += 90;
                direction %= 360;
            }
            else{
                if (direction == 0) position[1]--;
                else if (direction == 90) position[0]++;
                else if (direction == 180) position[1]++;
                else if (direction == 270) position[0]--;
            }
        }
        return false;
    }

    public static ArrayList<String[]> markMap(ArrayList<String[]> map){
        int[] position = findPosition(map);
        int direction = 0;

        while (!checkLeave(map, position)){
            String currentPlace = map.get(position[1])[position[0]];
            if (!currentPlace.equals("X") && !currentPlace.equals("^")) map.get(position[1])[position[0]] = "X";
            if (checkObstacle(map, position, direction)){
                direction += 90;
                direction %= 360;
            }
            else{
                if (direction == 0) position[1]--;
                else if (direction == 90) position[0]++;
                else if (direction == 180) position[1]++;
                else if (direction == 270) position[0]--;
            }
        }
        return map;
    }
    
    public static int[] findPosition(ArrayList<String[]> map){
        for (int r = 0; r < map.size(); r++){
            for (int c = 0; c < map.get(0).length; c++){
                if (map.get(r)[c].equals("^")){
//                    System.out.println("[" + c + "," + r + "] position");
                    return new int[]{c, r};
                }
            }
        }
        return new int[]{0, 0};
    }

    public static boolean checkObstacle(ArrayList<String[]> map, int[] position, int direction){
        int x = position[0];
        int y = position[1];
        if (direction == 0 && !checkLeave(map, new int[]{x, y-1}) && map.get(y-1)[x].equals("#")) return true;
        else if (direction == 90 && !checkLeave(map, new int[]{x+1, y}) && map.get(y)[x+1].equals("#")) return true;
        else if (direction == 180 && !checkLeave(map, new int[]{x, y+1}) && map.get(y+1)[x].equals("#")) return true;
        else if (direction == 270 && !checkLeave(map, new int[]{x-1, y}) && map.get(y)[x-1].equals("#")) return true;
        return false;
    }

    public static boolean checkLeave(ArrayList<String[]> map, int[] position){
        int x = position[0];
        int y = position[1];
        int mapLength = map.size();
        int mapWidth = map.get(0).length;
        return x < 0 || x >= mapWidth || y < 0 || y >= mapLength;
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
