import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1
{
    public static void main(String[] args){
        ArrayList<String> fileData = getFileData("trasnfer-main/src/input.txt");
        ArrayList<Integer> leftNums = new ArrayList<>();
        ArrayList<Integer> rightNums = new ArrayList<>();

        for (String data : fileData){
            leftNums.add(Integer.parseInt(data.substring(0,5)));
            rightNums.add(Integer.parseInt(data.substring(8)));
        }

        System.out.println("Part 1: " + doPartOne(leftNums, rightNums));
        System.out.println("Part 2: " + doPartTwo(leftNums, rightNums));

    }

    public static int doPartOne(ArrayList<Integer> left, ArrayList<Integer> right){
        int length = left.size();
        int total = 0;
        ArrayList<Integer> copyLeft = new ArrayList<>();
        ArrayList<Integer> copyRight = new ArrayList<>();
        for (Integer num : left){
            copyLeft.add(num);
        }
        for (Integer num : right){
            copyRight.add(num);
        }
        while (copyLeft.size() > 0){
            int leftSmallest = copyLeft.get(0);
            int rightSmallest = copyRight.get(0);
            for (int i = 0; i < copyLeft.size(); i++){
                if (copyLeft.get(i) < leftSmallest) leftSmallest = copyLeft.get(i);
            }
            for (int i = 0; i < copyRight.size(); i++){
                if (copyRight.get(i) < rightSmallest) rightSmallest = copyRight.get(i);
            }
            copyLeft.remove((Integer) leftSmallest);
            copyRight.remove((Integer) rightSmallest);
            total += Math.abs(leftSmallest - rightSmallest);
        }
        return total;
    }

    public static int doPartTwo(ArrayList<Integer> left, ArrayList<Integer> right){
        int total = 0;
        ArrayList<Integer> copyLeft = new ArrayList<>();
        ArrayList<Integer> copyRight = new ArrayList<>();
        for (Integer num : left){
            copyLeft.add(num);
        }
        for (Integer num : right){
            copyRight.add(num);
        }
        for (int i = 0; i < copyLeft.size(); i++){
            int count = 0;
            for (int j = 0; j < copyRight.size(); j++){
                if (copyLeft.get(i).equals(copyRight.get(j))) count++;
            }
            total += copyLeft.get(i) * count;
        }
        return total;
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
