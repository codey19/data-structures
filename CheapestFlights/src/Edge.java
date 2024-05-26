public class Edge {
    private Vertex v1;
    private Vertex v2;
    private int distance;

    public Edge(Vertex v1, Vertex v2, int distance){
        this.v1=v1;
        this.v2=v2;
        this.distance=distance;
    }
    public Vertex getV1(){
        return v1;
    }

    public Vertex getV2(){
        return v2;
    }

    public int getDistance(){
        return distance;
    }

    public boolean equals(Edge a, Edge b){
        return a.getV1().equals(b.getV1())&&a.getV2().equals(b.getV2());
    }

    public String toString(){
        return v1.toString()+" "+v2.toString()+" "+distance;
    }
}
