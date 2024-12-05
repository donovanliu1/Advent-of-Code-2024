import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {
        ArrayList<String> rulesArray = getFileData("src/input.txt");
        ArrayList<String> updatesArray = getFileData("src/input2.txt");

        ArrayList<String[]> rules = new ArrayList<>();
        ArrayList<String[]> updates = new ArrayList<>();

        for (String rule : rulesArray){
            rules.add(rule.split("\\|"));
        }

        for (String update : updatesArray){
            updates.add(update.split(","));
        }

        System.out.println("Part 1: " + doPartOne(rules, updates));
    }

    public static int doPartOne(ArrayList<String[]> rulesArray, ArrayList<String[]> updateArray){
        int total = 0;
        for (int i = 0; i < updateArray.size(); i++){
            int count = 0;
            int countMax = factorial(updateArray.get(i).length - 1);
            System.out.println(countMax);
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
