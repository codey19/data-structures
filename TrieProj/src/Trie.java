import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Trie {
    private TrieNode root;
    Trie() {
        root = new TrieNode(); // Note root has no
    }

    public void add(String word){
        TrieNode curr = root;
        for(char e: word.toCharArray()){
            Map<Character, TrieNode> kids = curr.getChildren();
            if(kids.containsKey(e)) {
                curr = kids.get(e);
                curr.incrementCount();
            }else{
                kids.put(e, new TrieNode());
                curr = kids.get(e);
            }
        }
        curr.setEndOfWord(true);
    }

    public boolean contains(String word){
        TrieNode curr = root;
        for(char e: word.toCharArray()){
            Map<Character, TrieNode> kids = curr.getChildren();
            if(kids.containsKey(e)){
                curr = kids.get(e);
            }else
                return false;
        }
        return curr.isEndOfWord();
    }

    public Character mostLikelyNextChar(String str){
        TrieNode curr = root;
        for(char e: str.toCharArray()){
            Map<Character, TrieNode> kids = curr.getChildren();
            if(kids.containsKey(e)){
                curr = kids.get(e);
            }else
                return '_';
        }
        Map<Character, TrieNode> kids = curr.getChildren();
        Character nextChar = '_';
        Integer max = 0;
        for (Map.Entry<Character, TrieNode> entry : kids.entrySet()) {
            if(entry.getValue().getCount() > max) {
                max = entry.getValue().getCount();
                nextChar = entry.getKey();
            }
        }
        return nextChar;
    }

    public Map<Character, Double> likelyNextCharsFrequency(String str){
        Map<Character, Double> nextChars = new HashMap<>();
        TrieNode curr = root;
        for(char e: str.toCharArray()){
            Map<Character, TrieNode> kids = curr.getChildren();
            if(kids.containsKey(e)){
                curr = kids.get(e);
            }else
                return nextChars;
        }
        Map<Character, TrieNode> kids = curr.getChildren();
        for (Map.Entry<Character, TrieNode> entry : kids.entrySet())
            nextChars.put(entry.getKey(), entry.getValue().getCount() * 100/ (double)curr.getCount());
        return nextChars;
    }

    public Map<Double, String> overTenPercentFreq(String str){
        Map<Double, String> nextChars = new HashMap<>();
        TrieNode curr = root;
        for(char e: str.toCharArray()){
            Map<Character, TrieNode> kids = curr.getChildren();
            if(kids.containsKey(e)){
                curr = kids.get(e);
            }else
                return nextChars;
        }
        Map<Character, TrieNode> kids = curr.getChildren();
        for (Map.Entry<Character, TrieNode> entry : kids.entrySet()) {
            double prob = entry.getValue().getCount() *100/ (double) curr.getCount();
            if(prob < 10)
                continue;
            if(nextChars.containsKey(prob))
                nextChars.put(prob, nextChars.get(prob) + entry.getKey());
            else
                nextChars.put(prob, entry.getKey() + "");
        }
        return nextChars;
    }

    /***********   INNER CLASS ********/
    // Even though we will use an inner class here, we will
    // access it via methods rather than directly using its attributes
    // this allows for a more "swapable" design to change node
    class TrieNode {
        private final Map<Character, TrieNode> children;
        private boolean endOfWord;
        private int count;

        public TrieNode(){
            children = new HashMap<>();
            endOfWord = false;
            count = 1;
        }

        private Map<Character, TrieNode> getChildren() {
            return children;
        }

        private void incrementCount(){
            count++;
        }

        private int getCount(){
            return count;
        }

        private boolean isEndOfWord() {
            return endOfWord;
        }

        private void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }

        public String toString(){
            return "("+count+")";
        }
    }
    /*********   END INNER CLASS   ************/
}