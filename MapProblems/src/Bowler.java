public class Bowler implements Comparable<Bowler>{
    private String firstName;
    private String lastName;
    private int score;
    public Bowler(String firstName, String lastName, int score){
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public int getScore() {return score;}

    @Override
    public int compareTo(Bowler o) {
        if(lastName.compareTo(o.getLastName()) == 0)
            return firstName.compareTo(o.getFirstName());
        return lastName.compareTo(o.getLastName());
    }

    @Override
    public String toString() {return firstName + " " + lastName;}
}
