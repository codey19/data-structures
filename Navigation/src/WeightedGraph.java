import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WeightedGraph {
    private HashMap<Vertex, HashSet<Edge>> vertices;

    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    public ArrayList<Vertex> getVertices(){
        return this.vertices;
    }

    public Vertex getVertex(String name) {
        if(!vertices.containsKey(name)) {
            vertices.put(new Vertex(name), new HashSet<Edge>());
            return new Vertex(name);
        }
        return (vertices.keySet()).;
    }

    public void removeVertex(Vertex v){
        this.vertices.remove(v);
    }

    public void addEdge(Vertex v1, Vertex v2, int weight) {
        Edge e = new Edge(v1, v2, weight);
        vertices.get()

    }

    public HashSet<Edge> getEdges(){
        HashSet<Edge> edges = new HashSet<>();
        for(HashSet<Edge> es: vertices.values()){
            for(Edge e: es)
                edges.add(e);
        }
        return edges;
    }

    public HashSet<Vertex> getNeighbors(Vertex v){
        HashSet<Vertex> neighbors = new HashSet<>();
        for(Edge e: vertices.get(v)){
            neighbors.add(e.getEnd());
            neighbors.add(e.getStart());
        }
        return neighbors;
    }
}