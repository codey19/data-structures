public class Edge {
    private Vertex start;
    private Vertex end;
    private int weight;

    public Edge(Vertex startV, Vertex endV, int inputWeight) {
        this.start = startV;
        this.end = endV;
        this.weight = inputWeight;
    }

    public Vertex getStart() {
        return this.start;
    }

    public Vertex getEnd() {
        return this.end;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "start=" + start +
                ", end=" + end +
                ", weight=" + weight;
    }

    public boolean equals(Edge e){
        return start.equals(e.start) || start.equals(e.end) || end.equals(e.end);
    }

}