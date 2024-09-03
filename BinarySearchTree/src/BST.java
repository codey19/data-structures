public class BST<E extends Comparable<E>> {

    private TreeNode<E> root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public void add(E val){
        if(root ==  null) {
            root = new TreeNode<>(val);
            size++;
            return;
        }
        add(root, val);
    }

    private void add(TreeNode<E> curRoot, E val){
        int comp = val.compareTo(curRoot.val);
        if(comp == 0)
            return;
        if(comp < 0){
            if(curRoot.left == null) {
                curRoot.left = new TreeNode<>(val);
                size++;
                return;
            }
            add(curRoot.left, val);
        }else if(comp > 0){
            if(curRoot.right == null) {
                curRoot.right = new TreeNode<>(val);
                size++;
                return;
            }
            add(curRoot.right, val);
        }
    }

    private String commaConcat(String string1, String string2, String string3){
        String temp = "";
        if(!string1.equals(""))
            temp += string1.length() > 1? string1: string1 + ", ";
        if(!string2.equals(""))
            temp += string2.length() > 1? string2: string2 + ", ";
        if(!string3.equals(""))
            temp += string3.length() > 1? string3: string3 + ", ";
        return temp;
    }

    public String preOrder(){
        String string = preOrder(root);
        return "[" + string.substring(0, string.length() - 2) + "]";
    }

    private String preOrder(TreeNode<E> curRoot){
        if(curRoot == null)
            return "";
        return commaConcat(curRoot.val.toString(), preOrder(curRoot.left), preOrder(curRoot.right));
    }

    public String inOrder(){
        String string = inOrder(root);
        return "[" + string.substring(0, string.length() - 2) + "]";
    }

    private String inOrder(TreeNode<E> curRoot){
        if(curRoot == null)
            return "";
        return commaConcat(inOrder(curRoot.left), curRoot.val.toString(), inOrder(curRoot.right));
    }

    public String postOrder(){
        String string = postOrder(root);
        return "[" + string.substring(0, string.length() - 2) + "]";
    }

    private String postOrder(TreeNode<E> curRoot){
        if(curRoot == null)
            return "";
        return commaConcat(postOrder(curRoot.left), postOrder(curRoot.right), curRoot.val.toString());
    }

    public boolean contains(E val){
        return contains(root, val);
    }

    private boolean contains(TreeNode<E> curRoot, E val){
        if(curRoot == null)
            return false;
        if(curRoot.val.equals(val))
            return true;
        boolean left = contains(curRoot.left, val);
        boolean right = contains(curRoot.right, val);
        return left? true: right;
    }

    public void remove(E val) {root = remove(root, val);}

    private TreeNode<E> remove(TreeNode<E> curRoot, E val) {
        if (curRoot == null)
            return null;
        int comp = val.compareTo(curRoot.val);
        if (comp < 0)
            curRoot.left = remove(curRoot.left, val);
        else if (comp > 0)
            curRoot.right = remove(curRoot.right, val);
        else if (comp == 0){
            if (curRoot.left == null){
                return curRoot.right;
            }else if (curRoot.right == null){
                return curRoot.left;
            }else{
                TreeNode<E> successor = findMax(curRoot.right);
                curRoot.val = successor.val;
                curRoot.right = remove(curRoot.right, successor.val);
            }
        }
        return curRoot;
    }

    private TreeNode<E> findMax(TreeNode<E> curRoot) {
        while (curRoot.right != null)
            curRoot = curRoot.right;
        return curRoot;
    }

    public void rotateRight(){
        root = rotateRight(root);
    }

    private TreeNode<E> rotateRight(TreeNode<E> curRoot){
        if(root == null || curRoot.left == null)
            return null;
        TreeNode<E> temp = curRoot.left;
        curRoot.left = temp.right;
        temp.right = curRoot;

        return temp;
    }

    public void rotateLeft(){
        root = rotateLeft(root);
    }

    private TreeNode<E> rotateLeft(TreeNode<E> curRoot){
        if(root == null || curRoot.right == null)
            return null;
        TreeNode<E> temp = curRoot.right;
        curRoot.right = temp.left;
        temp.left = curRoot;

        return temp;
    }
    public int size() { return size;}

    /********** PRINT **********/
    public void print(){
        if (root == null)
            System.out.print("Empty Tree");
        print(root, 0, "");
        System.out.println();
    }

    private void print(TreeNode<E> curr, int depth, String s){
        if (curr == null)
            return;
        for (int i = 1; i <= depth - 1; i++)
            System.out.print("|\t");
        if (depth == 0)
            System.out.println("[" + curr.val + "] <- root (L___ left, R___ right)");
        if (depth > 0)
        {
            System.out.print(s);
            System.out.println(curr.val);
        }
        print(curr.left, depth + 1, "L___"); // indicates left or "less than" side
        print(curr.right, depth + 1, "R___");  // indicate right or "greater than" side
    }
    /********** END PRINT **********/

    class TreeNode<E extends Comparable<E>>{
        private E val;
        private TreeNode<E> left, right;

        public TreeNode(E val){
            this.val = val;
            left = right = null;
        }

        public String toString(){return val.toString();}
    }


    public static void main(String[] args){
//        String word = "metropolitanism";
//        BST<Character> bst = new BST<>();
//        for (int i = 0; i < word.length(); i++)
//            bst.add(word.charAt(i));
//        bst.print();  // don't need to write.   Use the one I gave you
//        System.out.println("Size => "+bst.size());
//        System.out.println("Pre Order => "+bst.preOrder());
//        System.out.println("In Order => "+bst.inOrder());
//        System.out.println("Post Order => "+bst.postOrder());
//        System.out.println("Remove => "+bst.remove('i'));
//        System.out.println("In Order => "+bst.inOrder());
//        System.out.println("Contains 'i' => "+bst.contains('i'));
//        System.out.println("Contains 'u' => "+bst.contains('u'));
        BST<Integer> tree = new BST<>();
        int[] nums = {45,13,6,77,23,5,54,24,19,99,24,72,17,18};
        for (int i: nums)
            tree.add(i);

        tree.print();
        tree.remove(99); // Delete a leaf
        tree.print();
        tree.remove(17); // Delete a 1-Child Node
        tree.print();
        tree.remove(23); // Delete a 2-Child Node
        tree.print();
        tree.remove(45); // Delete the root
        tree.print();



    }
}