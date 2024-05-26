import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class DijkstraHelper {
    private WeightedGraph g;
    private HashMap<Vertex, Vertex> predecessors;
    private HashMap<Vertex, Integer> bestDist;
    private HashSet<Vertex> visited;
    private PriorityQueue<VertexDistance> q;

    private DijkstraHelper(WeightedGraph wg){
        g=wg;
    }

    public void createTravelsPaths(Vertex v){
        predecessors=new HashMap<Vertex,Vertex>();
        bestDist = new HashMap<Vertex, Integer>();
        visited=new HashSet<Vertex>();
        q=new PriorityQueue<VertexDistance>();

        for(Vertex var : g.getVertices())
            bestDist.put(var, Integer.MAX_VALUE);
        bestDist.put(v,0);
        predecessors.put(v,null);
        q.add(new VertexDistance(v,0));

        while(q.size() > 0) {
            Vertex curr = q.poll().v;
            if(visited.contains(curr))
                continue;
            for(Vertex n: g.getNeighbors(curr)){
                int newDist =  bestDist.get(curr) + g.getDistance(curr,n);
                if(bestDist.get(n) > newDist) {
                    bestDist.put(n, newDist);
                    predecessors.put(n, curr);
                    q.add(new VertexDistance(n,bestDist.get(n)));
                }
                visited.add(curr);
            }
        }
    }

    public String getShortestPath(Vertex target){
        return "";
    }

    private class VertexDistance implements Comparator<VertexDistance>{
        Vertex v;
        private int d;

        VertexDistance(Vertex ver, int dist){
            v=ver;
            d=dist;
        }

        public int compareTo(VertexDistance other){
            return this.d - other.d;
        }

        @Override
        public int compare(VertexDistance o1, VertexDistance o2) {
            return 0;
        }

        @Override
        public Comparator<VertexDistance> reversed() {
            return Comparator.super.reversed();
        }

        @Override
        public Comparator<VertexDistance> thenComparing(Comparator<? super VertexDistance> other) {
            return Comparator.super.thenComparing(other);
        }

        @Override
        public <U> Comparator<VertexDistance> thenComparing(Function<? super VertexDistance, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
            return Comparator.super.thenComparing(keyExtractor, keyComparator);
        }

        @Override
        public <U extends Comparable<? super U>> Comparator<VertexDistance> thenComparing(Function<? super VertexDistance, ? extends U> keyExtractor) {
            return Comparator.super.thenComparing(keyExtractor);
        }

        @Override
        public Comparator<VertexDistance> thenComparingInt(ToIntFunction<? super VertexDistance> keyExtractor) {
            return Comparator.super.thenComparingInt(keyExtractor);
        }

        @Override
        public Comparator<VertexDistance> thenComparingLong(ToLongFunction<? super VertexDistance> keyExtractor) {
            return Comparator.super.thenComparingLong(keyExtractor);
        }

        @Override
        public Comparator<VertexDistance> thenComparingDouble(ToDoubleFunction<? super VertexDistance> keyExtractor) {
            return Comparator.super.thenComparingDouble(keyExtractor);
        }
    }
}
