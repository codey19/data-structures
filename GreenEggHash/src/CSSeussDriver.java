import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class CSSeussDriver {//UNIQUE WORD COUNT: 50
    public static void main(String[] args){CSSeussDriver app = new CSSeussDriver();}

    public CSSeussDriver(){load("C:\\JavaProj\\GreenEggHash\\src\\GreenEggsAndHam.txt");}

    public void load(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            HashKeeper<String> hashKeeper = new HashKeeper<>(5);
            String line="";
            while((line=br.readLine())!= null) {
                for(String e: line.split(" ")){
                    hashKeeper.add(e.trim());
                }
                //hashKeeper.printBuckets();
            }
            hashKeeper.remove("");
            System.out.println(hashKeeper.size());
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }
}