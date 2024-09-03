import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class ColorCaveStarter extends JPanel implements MouseListener {// test
    Rectangle r;
    Room room, end;

    JFrame frame;
    String path;
    Bot bot;
    private Timer timer;
    private long startTime;
    ConcreteRoomLoader loader;

    public ColorCaveStarter() {
        frame = new JFrame("Color Cave");
        frame.setSize(1500, 1000);
        frame.add(this);
        frame.addMouseListener(this);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        loader = new ConcreteRoomLoader(); // need to extend abstract with concrete class
        //loader.load(); // Load your cave data
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\MyFile.ser");
        loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\rickRoll.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\KVB.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\CuriousGeorge.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\HaversHomiesCave.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\Simarbir & Company.ser");
         //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\AbovePAR.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\Aryan.ser");
//        loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\BWTAlpineF1.ser");
        // loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\CrystallerCavers.SER");
        bot = new Bot();
        bot.load();
        path = bot.run();
        room = loader.getStart(); // Initialize room with the start room
        startTime = 0;

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        if (room != null && room.getDoors().size() > 0) { // Check if room is not null
            int doorX = 150;
            for (Door door : room.getDoors()) {
                g2.setColor(enumToColor(door));
                r = new Rectangle(doorX, 300, 100, 200);
                g2.fill(r);
                doorX += 150;
            }

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 44));
            g2.drawString("COLOR CAVE!", 80, 40);
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            g2.drawString(room.getName(), 80, 80);
            //g2.drawString(room.getDescription(), 80, 120);

            g2.drawString("Path: " + path, 80, 220);
            long elapsedTime = startTime == 0? 0: System.currentTimeMillis() - startTime;
            g2.drawString("Time elapsed: " + (elapsedTime / 1000.0) + " seconds", 80, 160);

            if (room.equals(loader.getEnd())) {
                g2.drawString("Congratulations! You reached the end in " + (elapsedTime / 1000.0) + " seconds and " + bot.steps + " steps.", 80, 200);
                timer.stop();
            }
        } else {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            g2.drawString("Loading...", 80, 80);
        }
    }

    public void mouseClicked(MouseEvent e) {
        repaint();
        int doorX = 150;
        for (Door door : room.getDoors()) {
            Rectangle doorRect = new Rectangle(doorX, 330, 100, 200);
            if (doorRect.contains(e.getX(), e.getY())) {
                if(startTime == 0) {
                    startTime = System.currentTimeMillis();
                }
                Room nextRoom = room.enter(door);
                room = nextRoom;
                repaint();
                System.out.println("Inside the Rectangle " + door);
                break;
            }
            doorX += 150;
        }
    }

    // Other mouse listener methods we don't need to use
    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    private Color enumToColor(Door d) {
        switch (d) {
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.GREEN;
            case PINK:
                return Color.PINK;
            case YELLOW:
                return Color.YELLOW;

            default:
                return Color.WHITE;
        }

    }

    public static void main(String[] args) {
        ColorCaveStarter app = new ColorCaveStarter();
    }

}