import java.util.*;
public class TestProblems {
    public static void main(String[] args){
        char[][] map = new char[5][6];
        char[] line1 = {'0','0','0','0','N','0'};
        char[] line2 = {'0','0','0','0','0','0'};
        char[] line3 = {'X','0','0','0','0','0'};
        char[] line4 = {'0','0','0','0','0','0'};
        char[] line5 = {'0','0','0','0','0','0'};
        map[0] = line1;
        map[1] = line2;
        map[2] = line3;
        map[3] = line4;
        map[4] = line5;
        System.out.println(order("is2 Thi1s T4est 3a"));
    }
    public static String order(String words) {
        if(words.equals(""))
            return words;
        String[] array = new String[8];
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for(String e: words.split(" ")){
            int index = 0;
            for(char letter: e.toCharArray()){
                if(alpha.indexOf(letter + "") != -1)
                    index = Character.getNumericValue(letter);
            }
            array[index] = e;
        }
        String newString = "";
        for(String e: array)
            newString += e + " ";
        return newString.trim();
    }
}
