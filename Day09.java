import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day09{
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("src/input.txt");

        System.out.println("Part 1: " + doPartOne(fileData));
        System.out.println("Part 2: " + doPartTwo(fileData));
    }

    public static long doPartOne(ArrayList<String> fileData){
        String data = fileData.getFirst();
        ArrayList<String> blocks = getBlocks(data);
        moveBlocks(blocks);
        return calculateCheckSum(blocks);
    }

    public static long doPartTwo(ArrayList<String> fileData){
        String data = fileData.getFirst();
        ArrayList<String> blocks = getBlocks(data);
        moveBlocksPartTwo(blocks);
        System.out.println(blocks);
        return calculateCheckSum(blocks);
    }

    public static ArrayList<String> getBlocks(String data){
        ArrayList<String> blocks = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < data.length(); i++){
            if (i % 2 == 0){
                for (int j = 0; j < Integer.parseInt(data.substring(i,i+1)); j++){
                    blocks.add(String.valueOf(id));
                }
                id++;
            }
            else for (int j = 0; j < Integer.parseInt(data.substring(i,i+1)); j++) blocks.add(".");
        }
        return blocks;
    }

    public static void moveBlocks(ArrayList<String> blocks){
        for (int i = blocks.size() - 1; i >= 0; i--){
            String current = blocks.get(i);
            if (!current.equals(".")){
                for (int j = 0; j < i; j++){
                    if (blocks.get(j).equals(".")){
                        blocks.set(j, current);
                        blocks.remove(i);
                        break;
                    }
                }
            }
        }
    }
    public static void moveBlocksPartTwo(ArrayList<String> blocks){
        String current = blocks.getLast();
        int blockCount = 0;
        int spaceCount = 0;
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = blocks.size() - 1; i >= 0; i--){
            if (current.equals(blocks.get(i))){
                indexes.add(i);
                blockCount++;
            }
            else{
                for (int j = 0; j <= i; j++){
                    if (blocks.get(j).equals(".")) spaceCount++;
                    else spaceCount = 0;
                    if (spaceCount == blockCount){
                        for (int k = j - spaceCount + 1; k <= j; k++){
                            blocks.set(k, current);
                        }
                        for (int index : indexes){
                            blocks.set(index, ".");
                        }
                        spaceCount = 0;
                        break;
                    }
                }
                current = blocks.get(i);
                blockCount = 1;
                indexes.clear();
                indexes.add(i);
            }
        }
    }
    public static long calculateCheckSum(ArrayList<String> blocks){
        long checkSum = 0;
        for (int i = 0; i < blocks.size(); i++){
            if (!blocks.get(i).equals(".")){
                int id = Integer.parseInt(blocks.get(i));
                checkSum += (id * i);
            }
        }
        return checkSum;
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
