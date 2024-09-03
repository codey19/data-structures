import java.util.ArrayList;

public class Vertex {

    private final String NAME;
    private String description;
    public Vertex(String name) {
        this.NAME = name;
        description = "";
    }

    public Vertex(String name, String description){
        this.NAME = name;
        this.description = description;
    }

    public String getName() {return this.NAME;}


    @Override
    public String toString() {
        return NAME + ": " + description;
    }

   // @Override
    public boolean equals(Vertex v){
        return NAME.equals(v.getName());
    }
}