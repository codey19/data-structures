import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RemoveWords {
    public static void main(String[] args){RemoveWords app=new RemoveWords();}

    public RemoveWords(){load("src/words.csv");}//add extra words to this file

    public void load(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] words = br.readLine().split(",");
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter the writing here:");
            String writing = reader.nextLine();
            System.out.println(removeWords(writing, words));
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }

    public String removeWords(String writing, String[] words){
        String[] rArray = writing.split(" ");
        for(int i = 0; i < rArray.length; i++)
            for(String e: words)
                if(rArray[i].equals(e) || (rArray[i].contains(e) && rArray[i].indexOf("\'") != -1))
                    rArray[i] = generateGap(rArray[i].length());
        String rString = "";
        for(String e: rArray)
            rString += " " + e + " ";
        return rString.trim();
    }

    public String generateGap(int length){
        String r = "";
        for(int i = 0; i < length; i++)
            r += "_";
        return r;
    }
}