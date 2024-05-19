import java.io.PrintStream;
import java.util.*;

public class Graph {

    private HashMap<String, HashSet<String>> g;

    public Graph() {
        g = new HashMap<>();
    }
    public void add(String node1, String node2){
        if(!g.containsKey(node1))
            g.put(node1, new HashSet<>());
        if(!g.containsKey(node2))
            g.put(node2, new HashSet<>());
        g.get(node1).add(node2);
        g.get(node2).add(node1);
    }

    @Override
    public String toString() {
        String out = "";
        for(Map.Entry<String, HashSet<String>> entry : g.entrySet()){
            out += entry.getKey() + " -->" + entry.getValue() + "\n";
        }
        return out;
    }

    public void bfsIteratePoints(HashMap<String, Integer> points, String start) {
        Queue<String> path = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String, String> table = new HashMap<>();

        String curr = start;

        path.add(curr);
        table.put(curr, null);

        while(!path.isEmpty()){
            curr = path.poll();
            if(!visited.contains(curr)) {
                visited.add(curr);
                addPoints(points, table, start, curr);
                for(String n : g.get(curr)) {
                    path.add(n);
                    if (!table.containsKey(n))
                        table.put(n, curr);
                }
            }
        }
        //System.out.println(visited.size() + ": " + g.size());
    }

    public void addPoints(HashMap<String, Integer> points, HashMap<String, String> table, String start, String end){
        String curr = end;
        int depth = 0;
        while (curr != null) {
            depth++;
            curr = table.get(curr);
        }
        int newPoints = 0;
        switch(depth-1){
            case 1:
                newPoints = -16;
                break;
            case 2:
                newPoints = -8;
                break;
            case 3:
                newPoints = -4;
                break;
            case 4:
                newPoints = -2;
                break;
            case 5:
                newPoints = -1;
                break;
        }
        if(points.containsKey(start))
            points.put(start, points.get(start) + newPoints);
        else
            points.put(start, newPoints);
    }

    public void findCentral(HashMap<String, Integer> points){ // 1->16 point, 2->8 point, 3->4 point, 4->2 point, 5->1 point
        for(String e: g.keySet()){
            bfsIteratePoints(points, e);
        }
    }
}
