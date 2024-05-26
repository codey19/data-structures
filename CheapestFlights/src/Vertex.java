public class Vertex {
    private final String name;
    private String description;

    public Vertex(String name){
        this.name=name;
    }

    public Vertex(String name, String description){
        this.name=name;
        this.description=description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String setDescription(String s){
        description=s;
        return s;

    }

    public boolean equals(String s){
        return this.name.equals(s);
    }

    public String toString(){
        return name+": "+description;
    }







}
