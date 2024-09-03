import java.io.*;
import java.math.BigInteger;

public class LucasNumber{

    public static void main(String[] args){
        LucasNumber app=new LucasNumber();
    }

    public LucasNumber(){
        load("C:\\JavaProj\\Tasks\\inputData\\lucas.txt");
    }

    public void load(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line="";
            while((line=br.readLine())!= null) {
                System.out.println(findNthTerm(Integer.parseInt(line)));
            }
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }

    public BigInteger findNthTerm(int n){
        BigInteger previous = new BigInteger("2");//0th term
        BigInteger current = new BigInteger("1");//1st term
        BigInteger next = new BigInteger(current.toString());
        for(int i = 1; i < n; i++){
            next = previous.add(current);
            previous  = new BigInteger(current.toString());
            current = new BigInteger(next.toString());
        }
        return next;
    }
}