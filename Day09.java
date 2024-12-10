import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day09
{
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("input.txt");

        System.out.println("Part 1: " + doPartOne(fileData));
        System.out.println("Part 2: " + doPartTwo(fileData));
    }

    public static int doPartOne(ArrayList<String> fileData){
        int count = 0;
        return count;
    }
    
    public static int doPartTwo(ArrayList<String> fileData){
        int count = 0;
        return count;
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
