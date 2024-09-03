import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class MazeProjectStarter extends JPanel implements KeyListener, ActionListener
{
    private JFrame frame;
    private int size = 30, width = 1500, height = 1000;
    private char[][] maze;
    private String[] mazes;
    private Timer t;
    private Explorer explorer;
    private MazeElement finish;
    private ArrayList<UDB> udb;
    private ArrayList<LRB> lrb;
    private ArrayList<MazeElement> coins;
    private Wanderer wanderer;
    private String print;
    private int curMaze;
    private boolean isDead;
    public boolean finished;
    public boolean gameOver;

    public MazeProjectStarter(){
        //Maze variables
        mazes = new String[]{"src/maze0.txt", "src/maze1.txt", "src/maze2.txt"};
        curMaze = 0;
        setBoard(mazes[curMaze]);
        frame=new JFrame("A-Mazing Program");
        frame.setSize(width,height);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        t = new Timer(500, this);  // will trigger actionPerformed every 500 ms
        print = "PRINT STUFF HERE";
        isDead = false;
        finished = false;
        gameOver = false;
        t.start();
    }
    // All Graphics handled in this method.  Don't do calculations here
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,frame.getWidth(),frame.getHeight());
        if(gameOver && print.equals("CONGRATULATIONS!!!")){
            for(int i = 0; i < 5; i++){
                int[] xLocs ={(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth())};
                int[] yLocs ={(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight())};
                Polygon confetti = new Polygon(xLocs,yLocs,xLocs.length);
                g2.setColor(Color.yellow);
                g2.fill(confetti);
                g2.draw(confetti);
            }
            for(int i = 0; i < 5; i++){
                int[] xLocs ={(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth())};
                int[] yLocs ={(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight())};
                Polygon confetti = new Polygon(xLocs,yLocs,xLocs.length);
                g2.setColor(Color.red);
                g2.fill(confetti);
                g2.draw(confetti);
            }
            for(int i = 0; i < 5; i++){
                int[] xLocs ={(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth())};
                int[] yLocs ={(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight())};
                Polygon confetti = new Polygon(xLocs,yLocs,xLocs.length);
                g2.setColor(Color.green);
                g2.fill(confetti);
                g2.draw(confetti);
            }
            for(int i = 0; i < 5; i++){
                int[] xLocs ={(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth())};
                int[] yLocs ={(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight())};
                Polygon confetti = new Polygon(xLocs,yLocs,xLocs.length);
                g2.setColor(Color.blue);
                g2.fill(confetti);
                g2.draw(confetti);
            }
            for(int i = 0; i < 5; i++){
                int[] xLocs ={(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth()),(int)(Math.random() * frame.getWidth())};
                int[] yLocs ={(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight()),(int)(Math.random() * frame.getHeight())};
                Polygon confetti = new Polygon(xLocs,yLocs,xLocs.length);
                g2.setColor(Color.magenta);
                g2.fill(confetti);
                g2.draw(confetti);
            }
            g2.setFont(new Font("Arial", Font.BOLD, 100));
            if(Math.random() > 0.5)
                g2.setColor(Color.WHITE);
            else
                g2.setColor(Color.cyan);
            g2.drawString(print, 200, frame.getHeight()/2);
            return;
        }else if(gameOver){
            g2.setFont(new Font("Arial", Font.BOLD, 100));
            g2.setColor(Color.WHITE);
            g2.drawString(print, 200, frame.getHeight()/2);
            return;
        }
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                g2.setColor(Color.GREEN);
                Location here = new Location(r, c);
                if (maze[r][c] == '#')
                    g2.fillRect(c * size + size, r * size + size, size, size); //Wall
                else
                    g2.drawRect(c * size + size, r * size + size, size, size);  //Open
                if (here.equals(explorer.getLoc())) {
                    g2.drawImage(explorer.getImg(), c * size + size, r * size + size, size, size, null);
                }
                if (wanderer != null && here.equals(wanderer.getLoc())) {
                    g2.drawImage(wanderer.getImg(), c * size + size, r * size + size, size, size, null);
                }
                if (here.equals(finish.getLoc())) {
                    g2.drawImage(finish.getImg(), c * size + size, r * size + size, size, size, null);
                }
                for(UDB e: udb)
                    if (here.equals(e.getLoc())) {
                        g2.drawImage(e.getImg(), c * size + size, r * size + size, size, size, null);
                }
                for(LRB e: lrb)
                    if (here.equals(e.getLoc())) {
                        g2.drawImage(e.getImg(), c * size + size, r * size + size, size, size, null);
                    }
                for(MazeElement e: coins)
                    if (here.equals(e.getLoc())) {
                        g2.drawImage(e.getImg(), c * size + size, r * size + size, size, size, null);
                    }
            }
        }
        // Display at bottom of page
        int hor = size;
        int vert = maze.length * size + 2 * size;
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.PINK);
        g2.drawString(print, hor, vert);
    }

    public void keyPressed(KeyEvent e){
        System.out.println(e.getKeyCode());
        if(isDead || gameOver)
            return;
        explorer.move(e.getKeyCode(), maze);
        if(finished) {//load new maze
            finished = false;
            curMaze ++;
            if(curMaze < mazes.length)
                resetBoard(mazes[curMaze]);
            else{
                gameOver = true;
                print = "CONGRATULATIONS!!!";
                repaint();
                return;
            }

        }
        print = "STEPS: " + explorer.getSteps() + ". Coins Collected: " + explorer.getNumCoins();
        if(explorer.intersects(finish)) {
            print = "FINISHED";
            finished = true;
            return;
        }
        for(int i = 0; i < coins.size(); i++){
            MazeElement curCoin = coins.get(i);
            if(explorer.intersects(curCoin)) {
                coins.remove(i);
                explorer.incCoin();
            }
        }
        ifDied();//checks for collisions with death causing elements
        repaint();
    }

    /*** empty methods needed for interfaces **/
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void actionPerformed(ActionEvent e) {
        if(gameOver) {
            repaint();
            return;
        }
        if(isDead) {
            resetBoard(mazes[curMaze]);
            isDead = false;
        }
        ifDied();//checks for collisions with death causing elements
        if(!isDead) {
            for (UDB bot : udb)
                bot.move(0, maze);
            for (LRB bot : lrb)
                bot.move(0, maze);
            if(wanderer != null)
                wanderer.move(0, maze);
            if (!finished)
                print = "STEPS: " + explorer.getSteps() + ". Coins Collected: " + explorer.getNumCoins();
        }
        repaint();
    }

    public void setBoard(String fileName){//only for initial build
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            udb = new ArrayList<>();
            lrb = new ArrayList<>();
            coins = new ArrayList<>();
            wanderer = null;
            ArrayList<String> rows = new ArrayList<>();
            while((line=br.readLine()) != null) {
                rows.add(line);
                int temp = -1;
                if((temp = line.indexOf("E")) != -1)
                    explorer = new Explorer(new Location(rows.size() - 1, temp), size, new String[]{"src/marioUp.png", "src/marioRight.png", "src/marioDown.png", "src/marioLeft.png"});
                if((temp = line.indexOf("F")) != -1)
                    finish = new MazeElement(new Location(rows.size() - 1, temp), size, "src/pipe.png");
                if((temp = line.indexOf("U")) != -1)
                    udb.add(new UDB(new Location(rows.size() - 1, temp), size, "src/goomba.png"));
                if((temp = line.indexOf("L")) != -1)
                    lrb.add(new LRB(new Location(rows.size() - 1, temp), size, new String[]{"src/shellLeft.png","src/shellRight.png"}));
                if((temp = line.indexOf("W")) != -1)
                    wanderer = new Wanderer(new Location(rows.size() - 1, temp), size, new String[]{"src/redUp.png", "src/redRight.png", "src/redDown.png", "src/redLeft.png"});
                if((temp = line.indexOf("C")) != -1)
                    coins.add(new MazeElement(new Location(rows.size() - 1, temp), size, "src/coin.png"));
            }
            maze = new char[rows.size()][];
            for(int i = 0; i < rows.size(); i++)
                maze[i] = rows.get(i).toCharArray();
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }

    public void resetBoard(String fileName){//for deaths and loading new mazes where data in explorer is retained
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            udb = new ArrayList<>();
            lrb = new ArrayList<>();
            coins = new ArrayList<>();
            wanderer = null;
            ArrayList<String> rows = new ArrayList<>();
            while((line=br.readLine()) != null) {
                rows.add(line);
                int temp = -1;
                if((temp = line.indexOf("E")) != -1)
                    explorer.setLoc(new Location(rows.size() - 1, temp));
                if((temp = line.indexOf("F")) != -1)
                    finish.setLoc(new Location(rows.size() - 1, temp));
                if((temp = line.indexOf("U")) != -1)
                    udb.add(new UDB(new Location(rows.size() - 1, temp), size, "src/goomba.png"));
                if((temp = line.indexOf("L")) != -1)
                    lrb.add(new LRB(new Location(rows.size() - 1, temp), size, new String[]{"src/shellLeft.png","src/shellRight.png"}));
                if((temp = line.indexOf("W")) != -1)
                    wanderer = new Wanderer(new Location(rows.size() - 1, temp), size, new String[]{"src/redUp.png", "src/redRight.png", "src/redDown.png", "src/redLeft.png"});
                if((temp = line.indexOf("C")) != -1)
                    coins.add(new MazeElement(new Location(rows.size() - 1, temp), size, "src/coin.png"));
            }
            maze = new char[rows.size()][];
            for(int i = 0; i < rows.size(); i++)
                maze[i] = rows.get(i).toCharArray();
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }

    public static void main(String[] args){
        MazeProjectStarter app=new MazeProjectStarter();
    }

    public void ifDied(){
        if(isDead) {
            return;
        }
        for(UDB bot: udb)
            if(explorer.intersects(bot)) {
                deathFunctions();
                return;
            }
        for(LRB bot: lrb)
            if(explorer.intersects(bot)) {
                deathFunctions();
                return;
            }
        if(wanderer != null && explorer.intersects(wanderer))
            deathFunctions();
    }

    public void deathFunctions(){
        explorer.die();
        System.out.println("You Died");
        print = "YOU DIED. YOU HAVE " + explorer.getLiveLeft() + " LIVES LEFT.";
        if(explorer.getLiveLeft() == 0) {//GAME OVER
            gameOver = true;
            print = "YOU RAN OUT OF LIVES.";
            repaint();
            t.setInitialDelay(10000000);
        }
        else
            t.setInitialDelay(3000);
        t.restart();
        isDead = true;
    }
}