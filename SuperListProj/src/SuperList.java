import java.util.EmptyStackException;

public class SuperList<E>{
    //Codey Sheng
    private ListNode<E> root, end;
    private int size;

    public SuperList(){
        root = null;
        end = null;
    }

    public void add(E val){
        ListNode<E> newNode = new ListNode<>(val);
        size ++;
        if(size == 1) {
            end = root = newNode;
            return;
        }
        end.setNext(newNode);
        newNode.setPrev(end);
        end = newNode;
    }

    public void add(int index, E val){
        ListNode<E> newNode = new ListNode<>(val);
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if(index == 0){
            size ++;
            root.setPrev(newNode);
            newNode.setNext(root);
            root = newNode;
        }else if (index == size - 1){
            add(val);
        }else{
            ListNode<E> temp = root;
            for(int i = 0; i < index; i++)
                temp = temp.next;
            ListNode<E> prev = temp.prev;
            temp.setPrev(newNode);
            prev.setNext(newNode);
            newNode.setNext(temp);
            newNode.setPrev(prev);
            size++;
        }
    }

    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        ListNode<E> removed = root;
        if(size == 1){
            clear();
            return removed.getVal();
        }
        if(index == 0){
            ListNode<E> temp = root.next;
            temp.setPrev(null);
            root = temp;
        }else if (index == size - 1){
            removed = end;
            ListNode<E> temp = end.prev;
            temp.setNext(null);
            end = temp;
        }else{
            for(int i = 0; i < index; i++)
                removed = removed.next;
            ListNode<E> prev = removed.prev;
            ListNode<E> next = removed.next;
            prev.setNext(next);
            next.setPrev(prev);
        }
        size--;
        return removed.val;
    }

    public int size(){return size;}

    public void clear(){
        root = end = null;
        size = 0;
    }

    public boolean isEmpty(){return size == 0;}

    public boolean contains(E val){
        ListNode<E> cur = root;
        for(int i  = 0; i < size -1;i++){
            if(cur.val == val)
                return true;
            cur = cur.next;
        }
        return false;
    }

    public void push(E val){add(val);}

    public E pop(){
        if(size == 0)
            throw new EmptyStackException();
        return remove(size - 1);
    }

    public E poll(){
        if(size == 0)
            return null;
        return remove(0);
    }

    public E stackPeek(){
        if(size == 0)
            throw new EmptyStackException();
        return end.val;
    }

    public E queuePeek(){
        if(size == 0)
            return null;
        return root.val;
    }

    public E get(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if(index == 0)
            return root.val;
        else if (index == size - 1)
            return end.val;
        ListNode<E> temp = root;
        for(int i = 0; i < index; i++)
            temp = temp.next;
        return temp.val;
    }

    public E set(int index, E val){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        ListNode<E> temp = root;
        for(int i = 0; i < index; i++)
            temp = temp.next;
        E oldValue = temp.getVal();
        temp.setVal(val);
        return oldValue;
    }

    public String toString(){
        String print = "[";
        ListNode<E> cur = root;
        for (int i = 0; i <  size; i ++) {
            if(i == size - 1) {
                print += cur.getVal();
            }else {
                print += cur.getVal() + ", ";
                cur = cur.getNext();
            }
        }
        return print + "]";
    }

    public static void main(String[] args){
//        SuperList<Integer> superlist = new SuperList<>();
//        superlist.add(0);
//        superlist.add(1);
//        superlist.add(2);
//        superlist.add(3);
//        superlist.add(4);
//        System.out.println(superlist + "\tSize: " + superlist.size());
//        superlist.add(2, 5);
//        System.out.println(superlist + "\nContains 5: " + superlist.contains(5));
//        System.out.println("Removed: " + superlist.remove(3) + "\n" + superlist + "\tSize: " + superlist.size());
//        System.out.println("Queue Peek: " + superlist.queuePeek() + "\n" + "Stack Peek: " + superlist.stackPeek());
//        System.out.println("Queue Peek: " + superlist.queuePeek() + "\n" + "Stack Peek: " + superlist.stackPeek());
//        System.out.println("Removed: " + superlist.remove(3) + "\n" + superlist + "\tSize: " + superlist.size());
        SuperList<Integer> list = new SuperList<Integer>();

        // ArrayList Methods Test

        for(int i = 0; i < 10; i++)
            list.add(i);

        list.add(2, 6);
        //list.add(11, 100); out of bounds

        System.out.println(list);

        list.add(0, 20);
        list.add(list.size() - 1, 43);

        System.out.println(list);

        System.out.println(list.size());
        System.out.println(list.contains(6));
        System.out.println(list.contains(266238));

        for(int i  = list.size() - 1; i >= 0; i--)
        {
            list.remove(i);
            System.out.println(list);
        }

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(2, 6);
        System.out.println(list);

        list.set(2, 4);
        list.set(0, 10000);
        System.out.println(list);

        System.out.println(list.get(0));
        System.out.println(list.get(list.size() - 1));

        list.clear();
        System.out.println(list + "\n");
        System.out.println(list.isEmpty());

        SuperList<String> list2 = new SuperList<String>();
        list2.add("Hello");
        System.out.println(list);
        //Stack Methods Test

        list.push(2);
        System.out.println(list);
        list.push(13);
        list.push(10);
        list.push(55);
        System.out.println(list);
        System.out.println(list.pop());
        System.out.println(list);
        System.out.println(list.stackPeek());

        list.clear();
        System.out.println(list + "\n");

        //Queue Methods Test

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);

        list.poll();
        System.out.println(list);
        System.out.println(list.poll());
        System.out.println(list);
        System.out.println(list.queuePeek());

        SuperList<String> stk = new SuperList<String>();
        // checking stack is empty or not
        boolean result = stk.isEmpty();
        System.out.println("Is the stack empty? " + result);

        // pushing elements into stack
        stk.push("Shrihith");
        stk.push("Simarbir");
        stk.push("Aryan");
        System.out.println("Stack: " + stk);

        System.out.println(stk.stackPeek()); // print top without removing
        String x = stk.pop(); // remove top and save to x
        stk.pop();            // remove without saving
        System.out.println("Current Top: "+stk.pop());
        System.out.println("x = "+x + "\n");

        stk.push("Shrihith");
        stk.push("Simarbir");
        stk.push("Aryan");
        stk.push("Shrihith");
        stk.push("Simarbir");
        stk.push("Aryan");
        stk.push("Shrihith");
        stk.push("Simarbir");
        stk.push("Aryan");
        System.out.println(stk);

        for(int i = stk.size() - 1; i >= 0; i--)
            System.out.println(stk.pop() + "    " + stk);

        System.out.println();
        /////////

        // create an empty Queue with LinkedList
        SuperList<Character> q = new SuperList<Character>();
        System.out.println(q.poll()); // returns null
        //System.out.println(q.remove(7)); // throws error

        // adding elements to Queue
        q.add('A');
        q.add('W');
        q.add('Q');
        System.out.println("Queue: " + q);

        System.out.println(q.queuePeek()); // print head without removing
        char s = q.poll(); // get head element and save to s
        q.poll();                 // get head element without saving
        System.out.println("Current Head: "+q.poll());
        System.out.println("s = "+s + "\n");

        q.add('A');
        q.add('W');
        q.add('Q');
        q.add('A');
        q.add('W');
        q.add('Q');
        q.add('A');
        q.add('W');
        q.add('Q');

        for(int i = q.size() - 1; i >= 0; i--)
            System.out.println(q.poll() + "    " + q);

        System.out.println(q);
        System.out.println(q.poll());


        SuperList<Integer> stack = new SuperList<Integer>();
        System.out.println("\nSTACK EXAMPLE TESTS");
        for (int i = 5; i <= 10; i++)
            stack.push(i);
        System.out.println(stack);
        System.out.println("peek = "+stack.stackPeek());
        System.out.println("size = "+stack.size());
        System.out.println("pop = "+stack.pop());
        System.out.println("size = "+stack.size());
        System.out.println(stack);
        System.out.println("Popping 1-by-1:");
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
        System.out.println();
        try{  stack.stackPeek(); } catch(Exception e) { System.out.println(e);}
        stack.push(5);
        System.out.println(stack);
        stack.clear();
        try{  stack.pop(); } catch(Exception e) { System.out.println(e);}
    }

    public class ListNode<E>{
        private E val;
        private ListNode<E> prev, next;
        public ListNode(E v){
            val = v;
            prev = next = null;
        }

        public E getVal(){return val;}

        public ListNode<E> getPrev(){return prev;}

        public ListNode<E> getNext(){return next;}

        public void setVal(E v){val = v;}

        public void setPrev(ListNode<E> prev) {this.prev = prev;}

        public void setNext(ListNode<E> next) {this.next = next;}

        public boolean hasPrev(){return prev != null;}

        public boolean hasNext(){return next != null;}
    }
}