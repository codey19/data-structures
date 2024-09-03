import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LRB extends MazeElement{
    private boolean goLeft;
    private String[] imgStrings;//0 - left, 1 - right

    public LRB(Location loc, int size, String[] imgStrings){
        super(loc, size, imgStrings[0]);
        this.imgStrings = imgStrings;
        goLeft = true;
    }

    @Override
    public BufferedImage getImg() {
        try {
            return ImageIO.read(new File(imgStrings[goLeft? 0: 1]));
        }catch (IOException e) {
            System.out.println("Image ->["+imgStrings[goLeft? 0: 1]+"] not loaded");
        }
        return null;
    }


    @Override
    public void move(int key, char[][] maze){
        int r = getLoc().getR();
        int c = getLoc().getC();
        if(goLeft && c > 0 && maze[r][c -1] != '#') {
            super.getLoc().incC(-1);
        }else if(!goLeft && c < maze[0].length - 1 && maze[r][c +1] != '#') {
            super.getLoc().incC(+1);
        }else if(c == 0 || maze[r][c -1] == '#'){
            super.getLoc().incC(+1);
            goLeft = false;
        }else if(c == maze[0].length - 1 || maze[r][c +1] == '#'){
            super.getLoc().incC(-1);
            goLeft = true;
        }
    }
}
