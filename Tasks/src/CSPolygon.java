import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSPolygon {

    public static void main(String[] args){
        CSPolygon app=new CSPolygon();
    }

    public CSPolygon(){
        load("C:\\JavaProj\\Tasks\\inputData\\polygons.txt");
    }

    public void load(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String polygonValues = "";
            while((polygonValues = br.readLine())!= null)
                System.out.println(polygonValues + (isPolygon(polygonValues)? " is possibly a polygon": " is NOT a polygon"));
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }

    public boolean isPolygon(String polygonValues){
        String[] values = polygonValues.split("\\|");//saving it rather than calling split() twice
        String[] sideLengths = values[0].split(",");
        String[] angleValues = values[1].split(",");
        if(sideLengths.length < 3 || angleValues.length < 3)//Rule #1
            return false;
        if(sideLengths.length != angleValues.length)//Rule #2
            return false;
        int angleSum = 0;
        for(String e: angleValues)
            angleSum += Integer.parseInt(e);
        if(180 * (sideLengths.length - 2) != angleSum)//Rule #3
            return false;
        if(breaksRule4(sideLengths))//Rule #4
            return false;
        return true;
    }

    public boolean breaksRule4(String[] array){
        int largestSide = 0;
        int sum = 0;
        for(String e: array) {
            int num = Integer.parseInt(e);
            if (largestSide < num)
                largestSide = num;
            sum += num;
        }
        return largestSide > sum - largestSide;
    }
}