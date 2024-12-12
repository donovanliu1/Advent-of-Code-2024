import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
//        for (String data :fileData) {
//            String[] split = data.split(":");
//            testValues.add(split[0]);
//            calibrationNums.add(split[1].substring(1).split(" "));
//        }
        checkCalibration("190", new String[]{"19", "10", "123", "123"});
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
        ArrayList<String[]> binaries = new ArrayList<>();
        String[] binary = new String[]{"+","*"};
        int size = calibrationNums.length - 1;
        for (int i = 0; i < size; i++){
            binaries.add(binary);
        }
        ArrayList<ArrayList<String>> combinations = getCombinations(binaries);
        System.out.println(combinations);
        return false;
    }

    public static ArrayList<ArrayList<String>> getCombinations(ArrayList<String[]> binaries){
        ArrayList<ArrayList<String>> combinations = new ArrayList<>();
        getCombinationsHelper(binaries, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    public static void getCombinationsHelper(ArrayList<String[]> binaries, int index, ArrayList<String> current, ArrayList<ArrayList<String>> result){
        if (index == binaries.size()){
            result.add(new ArrayList<>(current));
            return;
        }
        String[] currentBinary = binaries.get(index);
//        ArrayList<String> currentBinary = binaries.get(index);
        for (String binary : currentBinary){
            current.add(binary);
            getCombinationsHelper(binaries, index+1, current, result);
            current.removeLast();
        }
    }

//    public static boolean checkNums(int testValue, int[] calilbrationNums, int position, int currentNum, ArrayList<Integer> calibrationResults){
//        if (position < 0) return testValue == currentNum;
//
//        return false;
//    }
//
////    public static String[] getCombinations (int[] calibrationNums){
////        int combinationSize = calibrationNums.length - 1;
////        int arraySize = (int) Math.pow(2, combinationSize);
////        String[] combinations = new String[combinationSize];
////        for (int i = 0; i < combinations.length; i++){
////            String combination = "";
////            for (int j = 0; j < )
////        }
////    }
//
//    public static String getCombinations(ArrayList<String> combinations, int[] calibrationNums, int position, boolean binary){
//        if (position < calibrationNums.length){
//            if (binary) return "+" + getCombination(combinations, calibrationNums, position + 1, )
//        }
//    }

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
