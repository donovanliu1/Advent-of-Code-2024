import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;


public class Day3Part2 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input.txt");
        ArrayList<String> allMatches = new ArrayList<String>();
        String regex = "mul\\([0-9]*,[0-9]*\\)|do\\(\\)|don't\\(\\)";
        int total = 0;
        boolean enabled = true;
        for (String data : fileData){
            String searchString = data;
            Matcher m = Pattern.compile(regex).matcher(searchString);
            while (m.find()) {
                allMatches.add(m.group());
            }
        }
        for (String match : allMatches){
            if (match.equals("do()")) enabled = true;
            if (match.equals("don't()")) enabled = false;
            if (enabled && !match.equals("do()")) total += mul(match);
        }
        System.out.println(total);
    }

    private static int mul(String s){
        System.out.println(s);
        String nums = s.substring(4, s.length()-1);
        String[] strings = nums.split(",");
        return Integer.parseInt(strings[0]) * Integer.parseInt(strings[1]);
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
