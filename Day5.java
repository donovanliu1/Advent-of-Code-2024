import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {
        ArrayList<String> rulesArray = getFileData("input.txt");
        ArrayList<String> updatesArray = getFileData("input2.txt");

        ArrayList<String[]> rules = new ArrayList<>();
        ArrayList<String[]> updates = new ArrayList<>();

        for (String rule : rulesArray){
            rules.add(rule.split("\\|"));
        }

        for (String update : updatesArray){
            updates.add(update.split(","));
        }

        System.out.println("Part 1: " + doPartOne(rules, updates));
        System.out.println("Part 2: " + doPartTwo(rules, updates));
    }

    public static int doPartOne(ArrayList<String[]> rulesArray, ArrayList<String[]> updateArray){
        int total = 0;
        for (int i = 0; i < updateArray.size(); i++){
            int count = 0;
            int countMax = factorial(updateArray.get(i).length - 1);
            for (int j = 0; j < updateArray.get(i).length - 1; j++){
                int leftNum = Integer.parseInt(updateArray.get(i)[j]);
                for (int k = j + 1; k < updateArray.get(i).length; k++){
                    int rightNum = Integer.parseInt(updateArray.get(i)[k]);
                    for (int l = 0; l < rulesArray.size(); l++){
                        int leftRule = Integer.parseInt(rulesArray.get(l)[0]);
                        int rightRule = Integer.parseInt(rulesArray.get(l)[1]);
                        if (leftRule == leftNum && rightRule == rightNum) count++;
                    }
                }
                if (count == countMax) total += Integer.parseInt(updateArray.get(i)[updateArray.get(i).length / 2]);
            }
        }
        return total;
    }

    public static int doPartTwo(ArrayList<String[]> rulesArray, ArrayList<String[]> updateArray){
        int total = 0;
        ArrayList<String[]> goatArray = findInvalids(rulesArray, updateArray);
        for (int i = 0; i < goatArray.size(); i++){
            int[] tempArray = new int[goatArray.get(i).length];
            for (int j = 0; j < goatArray.get(i).length; j++){
                int count = 0;
                int leftNum = Integer.parseInt(goatArray.get(i)[j]);
                for (int k = 0; k < goatArray.get(i).length; k++){
                    int rightNum = Integer.parseInt(goatArray.get(i)[k]);
                    for (int l = 0; l < rulesArray.size(); l++){
                        int leftRule = Integer.parseInt(rulesArray.get(l)[0]);
                        int rightRule = Integer.parseInt(rulesArray.get(l)[1]);
                        if (leftRule == leftNum && rightRule == rightNum) count++;
                    }
                }
                tempArray[tempArray.length - count - 1] = leftNum;
            }
            total += tempArray[tempArray.length / 2];
        }
        return total;
    }

    public static ArrayList<String[]> findInvalids(ArrayList<String[]> rulesArray, ArrayList<String[]> updateArray){
        ArrayList<String[]> newArray = new ArrayList<>();
        for (int i = 0; i < updateArray.size(); i++){
            int count = 0;
            int countMax = factorial(updateArray.get(i).length - 1);
            for (int j = 0; j < updateArray.get(i).length - 1; j++){
                int leftNum = Integer.parseInt(updateArray.get(i)[j]);
                for (int k = j + 1; k < updateArray.get(i).length; k++){
                    int rightNum = Integer.parseInt(updateArray.get(i)[k]);
                    for (int l = 0; l < rulesArray.size(); l++){
                        int leftRule = Integer.parseInt(rulesArray.get(l)[0]);
                        int rightRule = Integer.parseInt(rulesArray.get(l)[1]);
                        if (leftRule == leftNum && rightRule == rightNum) count++;
                    }
                }

            }
            if (count != countMax) newArray.add(updateArray.get(i));
        }
        return newArray;
    }

    public static int factorial(int num){
        int result = 0;
        while (num > 0){
            result += num;
            num--;
        }
        return result;
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
