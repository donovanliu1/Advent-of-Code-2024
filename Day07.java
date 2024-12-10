import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;


public class Day07 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("input.txt");

        System.out.println("Part 1: " + doPartOne(fileData));
    }

    public static int doPartOne(ArrayList<String> fileData){
        int sum = 0;

        ArrayList<String> testValues = new ArrayList<>();
        ArrayList<String[]> calibrationNums = new ArrayList<>();
        for (String data :fileData) {
            String[] split = data.split(":");
            testValues.add(split[0]);
            calibrationNums.add(split[1].substring(1).split(" "));
        }

        return sum;
    }

    // combinations of operations
    // for loop range: list size * 2


    public static boolean checkCalibration(String testValue, String[] calibrationNums){
        int tVal = Integer.parseInt(testValue);
        int[] cNums = new int[calibrationNums.length];
        for (int i = 0; i < calibrationNums.length; i++){
            cNums[i] = Integer.parseInt(calibrationNums[i]);
        }



        return false;
    }

    public static boolean checkNums(int testValue, int[] calilbrationNums, int position, int currentNum, ArrayList<Integer> calibrationResults){
        if (position < 0) return testValue == currentNum;

        return false;
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
