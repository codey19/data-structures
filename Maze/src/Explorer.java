import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Explorer extends MazeElement{
    private int dir;//0-North, 1-East, 2-South, 3-West
    private int steps;
    private int numCoins;
    private int liveLeft;
    private String[] imagesFiles;
    private BufferedImage[] images;

    public Explorer(Location loc, int size, String[] imagesFiles){
        super(loc, size, imagesFiles[1]);
        this.dir = 1;
        this.steps = 0;
        numCoins = 0;
        liveLeft = 3;
        this.imagesFiles = imagesFiles;
        images = new BufferedImage[imagesFiles.length];
        for(int i = 0; i < images.length; i++)
            try {
                images[i] = ImageIO.read(new File(imagesFiles[dir]));
            }catch (IOException e) {
                System.out.println("Image ->["+imagesFiles[dir]+"] not loaded");
            }
    }
    @Override
    public BufferedImage getImg() {
        try {
            return ImageIO.read(new File(imagesFiles[dir]));
        }catch (IOException e) {
            System.out.println("Image ->["+imagesFiles[dir]+"] not loaded");
        }
        return null;
    }

    @Override
    public void move(int key, char[][] maze) {
        if (key == KeyEvent.VK_UP) {//up arrow
            Location loc = this.getLoc();
            if (dir == 0 && maze[loc.getR() - 1][loc.getC()] != '#')
                super.getLoc().incR(-1);
            else if (dir == 1 && maze[loc.getR()][loc.getC() + 1] != '#')
                super.getLoc().incC(+1);
            else if (dir == 2 && maze[loc.getR() + 1][loc.getC()] != '#')
                super.getLoc().incR(+1);
            else if (dir == 3 && maze[loc.getR()][loc.getC() - 1] != '#')
                super.getLoc().incC(-1);
            else
                return;
            steps++;
        } else if (key == KeyEvent.VK_LEFT) {//left arrow
            dir = dir - 1 < 0 ? 3 : dir - 1;
        } else if (key == KeyEvent.VK_RIGHT) {//right arrow
            dir = dir + 1 > 3 ? 0 : dir + 1;
        }
    }

    public int getSteps(){return steps;}

    public int getNumCoins() {return numCoins;}

    public int getLiveLeft(){return liveLeft;}

    public void die(){
        liveLeft--;
        numCoins = 0;
    }

    public void incCoin(){numCoins++;}
}
