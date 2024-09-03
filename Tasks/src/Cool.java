import java.util.Stack;

public class Cool {
    public static void main(String[] args){
        System.out.println(expandedForm(12));
    }
    public static String expandedForm(int num)
    {
        String stringNum = num + "";
        Stack<Character> stack = new Stack<>();
        for(char e: stringNum.toCharArray())
            stack.push(e);
        String expanded = "";
        int factor = 1;
        for(int i = stack.size(); i > 0; i --){
            int temp = stack.peek();
            //if(temp != 0)
            expanded +=temp;
            //print = factor + " + " + print;
            factor *= 10;
            stack.pop();
        }
        return expanded;
    }
}
