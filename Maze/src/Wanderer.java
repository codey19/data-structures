import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class Wanderer extends MazeElement{
    private int dir;//0-North, 1-East, 2-South, 3-West
    private String[] imgStrings;//0 - left, 1 - right
    private HashSet<Location> previousLocs;

    public Wanderer(Location loc, int size, String[] imgStrings){
        super(loc, size, imgStrings[0]);
        this.imgStrings = imgStrings;
        dir = 0;
        previousLocs = new HashSet<>();
    }

    @Override
    public BufferedImage getImg() {
        try {
            return ImageIO.read(new File(imgStrings[dir]));
        }catch (IOException e) {
            System.out.println("Image ->["+imgStrings[dir]+"] not loaded");
        }
        return null;
    }

    @Override
    public void move(int key, char[][] maze) {
        int targetDir = (int)(Math.random() * 4);
        Location loc = new Location(this.getLoc().getR() - 1, this.getLoc().getC());
        if (targetDir == 0 && maze[loc.getR()][loc.getC()] != '#' && !previousLocs.contains(loc)) {
            dir = 0;
            previousLocs.add(new Location(this.getLoc().getR(), this.getLoc().getC()));
            super.getLoc().incR(-1);
            return;
        }
        loc = new Location(this.getLoc().getR(), this.getLoc().getC() + 1);
        if (targetDir == 1 && maze[loc.getR()][loc.getC()] != '#' && !previousLocs.contains(loc)){
            dir = 1;
            previousLocs.add(new Location(this.getLoc().getR(), this.getLoc().getC()));
            super.getLoc().incC(+1);
            return;
        }
        loc = new Location(this.getLoc().getR() + 1, this.getLoc().getC());
        if (targetDir == 2 && maze[loc.getR()][loc.getC()] != '#'  && !previousLocs.contains(loc)){
            dir = 2;
            previousLocs.add(new Location(this.getLoc().getR(), this.getLoc().getC()));
            super.getLoc().incR(+1);
            return;
        }
        loc = new Location(this.getLoc().getR(), this.getLoc().getC() - 1);
        if (targetDir == 3 && maze[loc.getR()][loc.getC()] != '#' && !previousLocs.contains(loc)) {
            dir = 3;
            previousLocs.add(new Location(this.getLoc().getR(), this.getLoc().getC()));
            super.getLoc().incC(-1);
            return;
        }
        previousLocs = new HashSet<>();
        move(key, maze);
    }

    /*public void move(int key, char[][] maze, Explorer explorer) {
        int udDifference = explorer.getLoc().getR() - getLoc().getR();
        int lrDifference = explorer.getLoc().getC() - getLoc().getC();
        int targetDir = 0;
        if(Math.abs(udDifference) > Math.abs(lrDifference)){
            targetDir = udDifference > 0? 0: 2;
        }else if(Math.abs(udDifference) < Math.abs(lrDifference)) {
            targetDir = lrDifference > 0? 1: 3;
        }else
            targetDir = 0;

        boolean[] availableDir = new boolean[]{false, false, false, false};
        Location loc = this.getLoc();
        if (targetDir == 0 && maze[loc.getR() - 1][loc.getC()] != '#') {
            dir = targetDir;
            super.getLoc().incR(-1);
            return;
        }else if(maze[loc.getR() - 1][loc.getC()] != '#'){
             availableDir[0] = true;
        }
        if (targetDir == 1 && maze[loc.getR()][loc.getC() + 1] != '#') {
            dir = targetDir;
            super.getLoc().incC(+1);
            return;
        }else if(maze[loc.getR()][loc.getC() + 1] != '#'){
            availableDir[1] = true;
        }
        if (targetDir == 2 && maze[loc.getR() + 1][loc.getC()] != '#') {
            dir = targetDir;
            super.getLoc().incR(+1);
            return;
        }else if(maze[loc.getR() + 1][loc.getC()] != '#'){
            availableDir[2] = true;
        }
        if (targetDir == 3 && maze[loc.getR()][loc.getC() - 1] != '#') {
            dir = targetDir;
            super.getLoc().incC(-1);
            return;
        }else if(maze[loc.getR()][loc.getC() - 1] != '#'){
            availableDir[3] = true;
        }

        while(true){
            targetDir = (int)(Math.random() * 4);
            if(availableDir[targetDir]){
                dir = targetDir;
                if (targetDir == 0)
                    super.getLoc().incR(-1);
                if (targetDir == 1)
                    super.getLoc().incC(+1);
                if (targetDir == 2)
                    super.getLoc().incR(+1);
                if (targetDir == 3)
                    super.getLoc().incC(-1);
                return;
            }
        }
    }*/
}
