import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day2Part1 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/input.txt");
        int count = 0;
        for (String s : fileData){
            String[] splitSample = s.split(" ");
            int[] diffs = findDiffs(splitSample);
            if (checkLimits(diffs) && checkConsistency(diffs)) count++;

        }
    }

    public static boolean checkConsistency(int[] diffs){
        boolean increasing = diffs[0] > 0;
        for (int diff : diffs){
            if (diff == 0) return false;
            if (increasing && diff < 0) return false;
            if (!increasing && diff > 0) return false;
        }
        return true;
    }

    public static boolean checkLimits(int[] diffs){
        for (int diff : diffs){
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) return false;
        }
        return true;
    }

    public static int[] findDiffs(String[] s){
        int[] diffs = new int[s.length - 1];
        for (int i = 0; i < s.length - 1; i++){
            int num1 = Integer.parseInt(s[i]);
            int num2 = Integer.parseInt(s[i + 1]);
            diffs[i] = num1 - num2;
        }
        return diffs;
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
