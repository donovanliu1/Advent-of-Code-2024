import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day07 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input.txt");
        
        System.out.println("Part 1: " + doPartOne(fileData));
        System.out.println("Part 2: " + doPartTwo(fileData));
    }

    public static long doPartOne(ArrayList<String> fileData){
        long sum = 0;
        ArrayList<String> testValues = new ArrayList<>();
        ArrayList<String[]> calibrationNums = new ArrayList<>();
        
        for (String data : fileData) {
            String[] split = data.split(":");
            testValues.add(split[0]);
            calibrationNums.add(split[1].substring(1).split(" "));
        }
        for (int i = 0; i < testValues.size(); i++){
            if (checkCalibration(testValues.get(i), calibrationNums.get(i))) sum += Long.parseLong(testValues.get(i));
        }
        return sum;
    }

    public static long doPartTwo(ArrayList<String> fileData){
        long sum = 0;
        ArrayList<String> testValues = new ArrayList<>();
        ArrayList<String[]> calibrationNums = new ArrayList<>();
        
        for (String data : fileData) {
            String[] split = data.split(":");
            testValues.add(split[0]);
            calibrationNums.add(split[1].substring(1).split(" "));
        }
        for (int i = 0; i < testValues.size(); i++){
            if (checkCalibrationPartTwo(testValues.get(i), calibrationNums.get(i))) sum += Long.parseLong(testValues.get(i));
        }
        return sum;
    }

    public static boolean checkCalibration(String testValue, String[] calibrationNums){
        long tVal = Long.parseLong(testValue);
        int[] cNums = new int[calibrationNums.length];
        for (int i = 0; i < calibrationNums.length; i++){
            cNums[i] = Integer.parseInt(calibrationNums[i]);
        }
        
        ArrayList<String[]> binaries = new ArrayList<>();
        String[] binary = new String[]{"+","*"};
        int size = calibrationNums.length - 1;
        for (int i = 0; i < size; i++){
            binaries.add(binary);
        }
        
        ArrayList<ArrayList<String>> combinations = getCombinations(binaries);
        
        for (ArrayList<String> combination : combinations){
            long total = cNums[0];
            for (int i = 0; i < combination.size(); i++){
                if (combination.get(i).equals("+")) total += cNums[i+1];
                else if (combination.get(i).equals("*")) total *= cNums[i+1];
            }
            if (total == tVal) return true;
        }
        return false;
    }

    public static boolean checkCalibrationPartTwo(String testValue, String[] calibrationNums){
        long tVal = Long.parseLong(testValue);
        int[] cNums = new int[calibrationNums.length];
        for (int i = 0; i < calibrationNums.length; i++) {
            cNums[i] = Integer.parseInt(calibrationNums[i]);
        }
        
        ArrayList<String[]> trinaries = new ArrayList<>();
        String[] trinary = new String[]{"+","*","||"};
        int size = calibrationNums.length - 1;
        for (int i = 0; i < size; i++){
            trinaries.add(trinary);
        }
        
        ArrayList<ArrayList<String>> combinations = getCombinations(trinaries);
        
        for (ArrayList<String> combination : combinations){
            long total = cNums[0];
            for (int i = 0; i < combination.size(); i++){
                if (combination.get(i).equals("+")) total += cNums[i+1];
                else if (combination.get(i).equals("*")) total *= cNums[i+1];
                else if (combination.get(i).equals("||")) total = Long.parseLong(total + "" + cNums[i+1]);
            }
            if (total == tVal) return true;
        }
        return false;
    }

    public static ArrayList<ArrayList<String>> getCombinations(ArrayList<String[]> aries){
        ArrayList<ArrayList<String>> combinations = new ArrayList<>();
        getCombinationsHelper(aries, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    public static void getCombinationsHelper(ArrayList<String[]> aries, int index, ArrayList<String> current, ArrayList<ArrayList<String>> result){
        if (index == aries.size()){
            result.add(new ArrayList<>(current));
            return;
        }
        String[] currentInary = aries.get(index);
        for (String inary : currentInary){
            current.add(inary);
            getCombinationsHelper(aries, index+1, current, result);
            current.removeLast();
        }
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
