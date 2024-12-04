import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input.txt");

        int rows = fileData.size();
        int columns = fileData.get(0).length();
        String[][] grid = new String[rows][columns];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c] = fileData.get(r).substring(c, c+1);
            }
        }

        System.out.println("Part 1: " + doPartOne(grid));
    }

    public static int doPartOne(String[][] grid){
        int count = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (r < grid.length - 3 && checkDown(grid, r, c)) count++;
                if (c < grid[0].length - 3 && checkRight(grid, r, c)) count++;
                if (r < grid.length - 3 && c > 2 && checkLeftDiag(grid, r, c)) count++;
                if (r < grid.length - 3 && c < grid[0].length - 3 && checkRightDiag(grid, r, c)) count++;
            }
        }
        return count;
    }

    public static boolean checkDown(String[][] grid, int r, int c){
        String phrase = grid[r][c] + grid[r+1][c] + grid[r+2][c] + grid[r+3][c];
        if (phrase.equals("XMAS")){
            System.out.println("down: " + phrase);
            return true;
        }
        if (phrase.equals("SAMX")){
            System.out.println("down: " + phrase);
            return true;
        }
        return false;
    }

    public static boolean checkRight(String[][] grid, int r, int c){
        String phrase = grid[r][c] + grid[r][c+1] + grid[r][c+2] + grid[r][c+3];
        if (phrase.equals("XMAS")){
            System.out.println("righ: " + phrase);
            return true;
        }
        if (phrase.equals("SAMX")){
            System.out.println("righ: " + phrase);
            return true;
        }
        return false;
    }

    public static boolean checkRightDiag(String[][] grid, int r, int c){
        String phrase = grid[r][c] + grid[r+1][c+1] + grid[r+2][c+2] + grid[r+3][c+3];
        if (phrase.equals("XMAS")){
            System.out.println("ridg: " + phrase);
            return true;
        }
        if (phrase.equals("SAMX")){
            System.out.println("ridg: " + phrase);
            return true;
        }
        return false;
    }

    public static boolean checkLeftDiag(String[][] grid, int r, int c){
        String phrase = grid[r][c] + grid[r+1][c-1] + grid[r+2][c-2] + grid[r+3][c-3];
        if (phrase.equals("XMAS")){
            System.out.println("lfdg: " + phrase);
            return true;
        }
        if (phrase.equals("SAMX")){
            System.out.println("lfdg: " + phrase);
            return true;
        }
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
