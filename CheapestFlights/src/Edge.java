public class Edge {
    private Vertex start;
    private Vertex end;
    private int distance;
    private String description;

    public Edge(Vertex start,Vertex end,int distance, String description) {
        this.start = start;
        this.end = end;
        this.description = description;
        this.distance = distance;
    }

    public Vertex getStart() {
        return start;
    }

    public String getDescription() {
        return description;
    }

    public Vertex getEnd() {
        return end;
    }

    public int getDistance() {
        return distance;
    }

    public boolean equals(Edge o){
        return (start.equals(o.start) && end.equals(o.end)) || (start.equals(o.end) && end.equals(o.start));
    }

    public String toString(){
        return start.toString()+" -> "+end.toString()+": "+distance + " " + description;
    }
}
