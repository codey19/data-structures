import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class TrieDisplay extends JPanel implements KeyListener
{
    private JFrame frame;
    private int size = 30, width = 1000, height = 600;
    private Trie trie;
    private String word;			// Word you are trying to spell printed in large font
    private char likelyChar;  		// Used for single most likely character
    private boolean wordsLoaded;  	// Use this to make sure words are alll loaded before you start typing


    public TrieDisplay(){

        frame=new JFrame("Trie Next");
        frame.setSize(width,height);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Default Settings
        word = "";
        likelyChar = ' ';  // Used for single most likely character
        wordsLoaded = false;

        trie = new Trie();
        // Load words from file
        try{
            File file = new File("src/words.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine())!= null){
                trie.add(line);
            }
        }catch(IOException io){
            System.out.print("No work");
        }
        // YOUR CODE HERE

        wordsLoaded = true;   // Set flag to true indicating program is ready
        repaint();

    }

    // All Graphics handled in this method.  Don't do calculations here
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);								// Setup and Background
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

        g2.setFont(new Font("Arial",Font.BOLD,60));				// Header
        g2.setColor(Color.WHITE);
        if (wordsLoaded)
            g2.drawString("Start Typing:",40,100);
        else
            g2.drawString("Loading... please wait",40,100);

        g2.setFont(new Font("Arial",Font.BOLD,100));			// Typed text:  White == valid partial word
        if (trie.contains(word))								//              Red == invalid
            g2.setColor(Color.GREEN);							//				Green == full word
        else
        if (likelyChar == '_')
            g2.setColor(Color.RED);
        else
            g2.setColor(Color.WHITE);
        g2.drawString(word,40,250);
        g2.setFont(new Font("Arial",Font.BOLD,26));


        //  YOUR CODE HERE
        if(!word.equals("")) {
            g2.setColor(Color.MAGENTA);
            Map<Double, String> chars = new TreeMap<>(trie.overTenPercentFreq(word));
            String possibilities = "";
            if(trie.mostLikelyNextChar(word) == '_')
                possibilities = "No Further Possibilities";
            else if(chars.size() == 0){//if there are no chars with freq >= 10
                Map<Character, Double> allChars = new TreeMap<>(trie.likelyNextCharsFrequency(word));
                for (Map.Entry<Character, Double> entry : allChars.entrySet()) {
                    possibilities += entry.getKey() + "=>" + Math.round(entry.getValue()) + "% ";
                }
            }
            for (Map.Entry<Double, String> entry : chars.entrySet()) {
                if(entry.getValue().length() == 1){
                    possibilities += entry.getValue() + "=>" + Math.round(entry.getKey()) + "% ";
                    continue;
                }
                for(char e: entry.getValue().toCharArray()){
                    possibilities = e + "=>" + Math.round(entry.getKey()) + "% " + possibilities;
                }
            }
            g2.drawString(possibilities, 40, 300);
            g2.setFont(new Font("Arial", Font.BOLD, 16));
        }
        // Draw String below here for next most likely letter / letters
        // If there ae no possible next letters write something like "no further possibilities"

    }


    public void keyPressed(KeyEvent e){              // This handles key press
        int keyCode = e.getKeyCode();
        if (keyCode == 8)  // Backspace -> remove last letetr
            word = word.substring(0,word.length()-1);
        if (keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z)  // alphabetic key
            word += KeyEvent.getKeyText(keyCode);
        likelyChar = trie.mostLikelyNextChar(word);
        //System.out.println("keyCode =>"+keyCode+", word =>"+word+", likelyChar =>"+likelyChar);     // Uncomment to Debug
        repaint();
    }

    /*** empty methods needed for interfaces **/
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void actionPerformed(ActionEvent e) {}

    public static void main(String[] args){
        TrieDisplay app=new TrieDisplay();
    }
}