import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

public class BowlingData {
    private static TreeMap<Integer, PriorityQueue<Bowler>> data;
    public static void main(String[] args) {
        data = new TreeMap<>();
        load("C:\\JavaProj\\MapProblems\\src\\BowlingData");

        Set<Map.Entry<Integer, PriorityQueue<Bowler>>> set = data.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Bowler>> entry : set){
            System.out.print(entry.getKey() + " = ");
            PriorityQueue<Bowler> cur = entry.getValue();
            while(!cur.isEmpty()){
                System.out.print(cur.poll());
                if(!cur.isEmpty())
                    System.out.print(",");
            }
            System.out.println();
        }
    }

    public static void load(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line="";
            while((line=br.readLine())!= null) {
                String[] split = line.split(" ");
                Bowler bowler = new Bowler(split[0], split[1], Integer.valueOf(split[2]));
                if(data.containsKey(Integer.valueOf(split[2])))
                    data.get(Integer.valueOf(split[2])).add(bowler);
                else{
                    PriorityQueue<Bowler> addBowler = new PriorityQueue<>();
                    addBowler.add(bowler);
                    data.put(Integer.valueOf(split[2]), addBowler);
                }
            }
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }
}