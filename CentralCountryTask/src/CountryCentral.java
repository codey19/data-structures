import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CountryCentral {
    private static Graph graph;
    private static HashMap<String, Integer> countryPoints;
    public CountryCentral() {
        load();
    }

    public void load() {
        graph = new Graph();
        countryPoints = new HashMap<>();
        try {
            File file = new File("GEODATASOURCE-COUNTRY-BORDERS.CSV");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] s = line.split(",");
                countryPoints.put(s[1], 0);
                if(s.length < 4)
                    continue;
                graph.add(s[1], s[3]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){
        CountryCentral countryCentral = new CountryCentral();
        graph.findCentral(countryPoints);
        TreeMap<Integer, ArrayList<String>> treeMap = new TreeMap<>();
        System.out.println("C-factor\tCountry");
        for (Map.Entry<String,Integer> entry : countryPoints.entrySet()) {
            if(treeMap.containsKey(entry.getValue()))
                treeMap.get(entry.getValue()).add(entry.getKey());
            else
                treeMap.put(entry.getValue(), new ArrayList<>(Arrays.asList(entry.getKey())));
        }
        int i = 0;
        for (Map.Entry<Integer,ArrayList<String>> entry : treeMap.entrySet()) {
            if(i >= 15)
                break;
            ArrayList<String> key = entry.getValue();
            if(key.size() > 1){
                for(String e: key){
                    System.out.println(entry.getKey() * -1 + "\t" + e);
                    i++;
                    if(i >= 15)
                        break;
                }
            }else{
                System.out.println(entry.getKey() * -1 + "\t" + key.get(0));
                i++;
            }
        }
        //System.out.println(graph);
    }
}
