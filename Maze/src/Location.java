import java.util.Objects;

public class Location {
    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getR() {return row;}

    public int getC() {return col;}

    public void incR(int x){row+=x;}

    public void incC(int x){col+=x;}

    public boolean equals(Location other) {
        if(row == other.getR())
            return col == other.getC();
        return false;
    }

    public boolean equals(int r, int c) {
        if(row == r)
            return col == c;
        return false;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return row == location.row && col == location.col;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
