import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GoldenRatio {
    public static void main(String[] args){
        String irrational = "1.61803398874989484820458683436563811772030917980576286213544862270526046281890244970720720418939113748475408807538689175212663386222353693179318006076672635";
        HashMap<Integer, Integer> combos = new HashMap<>();
        for(int i = 2; i < irrational.length() - 1; i++){
            Integer pair = Integer.valueOf(irrational.substring(i, i + 2));
            if(combos.containsKey(pair))
                combos.put(pair, combos.get(pair) + 1);
            else
                combos.put(pair, 1);
        }
        System.out.println(combos.size() + " 2-digit combinations: ");
        Set<Map.Entry<Integer, Integer>> siteSet = combos.entrySet();
        for (Map.Entry<Integer, Integer> entry : siteSet)
            if(entry.getValue() > 4)
                System.out.println(entry.getKey() + " occurred " + entry.getValue() + " times");

    }
}
