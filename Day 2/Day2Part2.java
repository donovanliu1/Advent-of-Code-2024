import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day2Part2 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("trasnfer-main/src/input.txt");
        int count = 0;
        for (String s : fileData){
            String[] splitSample = s.split(" ");
            ArrayList<Integer> nums = convertToInt(splitSample);
            int removeIndex = -1;
            while (removeIndex != nums.size()){
                ArrayList<Integer> nums2 = new ArrayList<>();
                for (int i = 0; i < nums.size(); i++){
                    if (i != removeIndex) nums2.add(nums.get(i));
                }
                if (checkSafe(nums2)){
                    count++;
                    break;
                }
                removeIndex++;
            }
        }
        System.out.println();
        System.out.println(count);
    }

    public static ArrayList<Integer> convertToInt(String[] nums){
        ArrayList<Integer> nums2 = new ArrayList<>();
        for (String s : nums){
            nums2.add(Integer.parseInt(s));
        }
        return nums2;
    }

    public static boolean checkSafe(ArrayList<Integer> nums){
        ArrayList<Integer> diffs = findDiffs(nums);
        if (!checkConsistency(diffs)) return false;
        return checkDiffs(diffs);
    }

    public static boolean checkPositive(ArrayList<Integer> diffs){
        for (int diff : diffs){
            if (diff <= 0) return false;
        }
        return true;
    }

    public static boolean checkNegative(ArrayList<Integer> diffs){
        for (int diff : diffs){
            if (diff >= 0) return false;
        }
        return true;
    }

    public static boolean checkConsistency(ArrayList<Integer> diffs){
        return checkNegative(diffs) || checkPositive(diffs);
    }

    public static boolean checkDiffs(ArrayList<Integer> diffs){
        for (int diff : diffs){
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) return false;
        }
        return true;
    }

    public static ArrayList<Integer> findDiffs(ArrayList<Integer> nums){
        ArrayList<Integer> diffs = new ArrayList<>();
        for (int i = 0; i < nums.size() - 1; i++){
            int num1 = nums.get(i);
            int num2 = nums.get(i + 1);
            int diff = num1 - num2;
            diffs.add(diff);
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
