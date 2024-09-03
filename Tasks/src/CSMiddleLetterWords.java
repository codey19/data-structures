import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CSMiddleLetterWords {
    HashMap<Character, Integer> middleLetters = new HashMap<>();

    public static void main(String[] args){
        CSMiddleLetterWords app=new CSMiddleLetterWords();
    }

    public CSMiddleLetterWords(){
        load("C:\\JavaProj\\Tasks\\inputData\\MiddleLetterWords.txt");
    }

    public void load(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line="";
            while((line=br.readLine())!= null) {
                Character c = line.charAt(line.length()/2);
                if(middleLetters.containsKey(c))
                    middleLetters.replace(c, middleLetters.get(c)+1);
                else
                    middleLetters.put(c, 1);
            }
            int numSame = 0;
            for(Integer value: middleLetters.values())
                if(value > 1)
                    numSame += value;
            System.out.println("Ans--> " + numSame);
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }
}