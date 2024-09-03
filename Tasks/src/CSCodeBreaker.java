import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSCodeBreaker {
    private int numBlack;// number of same color-same position
    private int numWhite;// number of same color-different position

    public static void main(String[] args){
        CSCodeBreaker app=new CSCodeBreaker();
    }

    public CSCodeBreaker(){
        load("C:\\JavaProj\\Tasks\\inputData\\mastermind.txt");
    }

    public void load(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while(true) {
                String solution = br.readLine();
                String guess = br.readLine();
                if(solution == null || guess == null)
                    break;
                System.out.println("Code: " + solution + "\nGuess: " + guess);
                findPegs(solution, guess);
                System.out.println("Color Correct - Correctly Placed: " + numBlack);
                System.out.println("Color Correct - Incorrectly Placed: " + numWhite + "\n");
            }
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }
    public void findPegs(String solution, String guess){
       numBlack = 0;
       numWhite = 0;
       for(int i = 0; i < guess.length(); i++){
           for(int j = 0; j < solution.length(); j++){
               if(guess.charAt(i) == solution.charAt(j)) {
                   solution = solution.substring(0, j) + "X" + solution.substring(j+1);//X marks the counted spots to avoid repeats
                   if(i == j) {
                       numBlack++;
                       break;
                   }
                   numWhite++;
                   break;
               }
           }
       }
    }
}