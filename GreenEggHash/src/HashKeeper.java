public class HashKeeper<E> {
    private Object[] table;
    private int size;
    public static final double LOAD_FACTOR = 0.75;
    public static final Object TOMB_STONE = new String("tombstone");

    public HashKeeper(int capacity){
        table = new Object[capacity];
        size = 0;
    }

    public int hashToIndex(E val){return Math.abs(val.hashCode())%table.length;}

    public int size(){return size;}

    public void add(E val){
        if (val == null)
            return;
        if (size >= LOAD_FACTOR*table.length)
            rehash();

        int index = hashToIndex(val);
        while (table[index] != null) {
            if (table[index].equals(val))
                return;
            if (table[index].equals(TOMB_STONE))
                break;
            index++;
            if (index == table.length)
                index = 0;
        }
        table[index] = val;
        size++;
    }

    public boolean remove(E val){
        int index = hashToIndex(val);
        if(table[index] == null)
            return false;

        while (index < table.length) {
            if(table[index].equals(val)) {
                table[index] = TOMB_STONE ;
                size--;
                return true;
            }
            index++;
            //System.out.println(index + "\t" + hashToIndex(val));
            if (index == table.length)
                index = 0;
        }
        return true;
    }

    public boolean contains(E val){
        int index = hashToIndex(val);
        if (table[index] == null)
            return false;
        while (index < table.length) {
            if (table[index] == null)
                break;
            else if(table[index].equals(val))
                return true;
            index++;
            if (index == table.length)
                index = 0;
        }
        return false;
    }

    public void printBuckets(){
        System.out.print("[");
        if(size == 0) {
            System.out.print("]");
            return;
        }
        for(int i = 0; i < table.length - 1; i++)
            System.out.print(table[i] + ", ");
        System.out.println(table[table.length - 1] + "]");
    }

    public String toString(){
        String string = "[";
        if(size == 0)
            return "]";
        for(int i = 0; i < table.length - 1; i++)
            if(table[i] != null)
                if(!table[i].equals(TOMB_STONE))
                    string += table[i] + ", ";
        if(table[table.length - 1] == null)
            return string.substring(0, string.length() - 2) + "]";
        return !table[table.length - 1].equals(TOMB_STONE)? string + table[table.length - 1] + "]": string.substring(0, string.length() - 2) + "]";
    }

    public void rehash(){
        int newCapacity = table.length * 2;
        Object[] newtable = new Object[newCapacity];
        for(int i = 0; i < table.length; i ++){
            Object curVal = table[i];
            if(curVal !=null && !curVal.equals((TOMB_STONE))){
                int newIndex = Math.abs(curVal.hashCode()) % newCapacity;
                while(newtable[newIndex] != null){
                    if(newtable[newIndex].equals(table[i]))
                        return;
                    newIndex++;
                    if(newIndex == table.length)
                        newIndex = 0;
                }
                newtable[newIndex] = table[i];
            }
        }
        table = newtable;
    }

    public static void main(String[] args){
        HashKeeper<String> hashKeeper = new HashKeeper<>(6);
        hashKeeper.add("Keys");
        hashKeeper.add("Wallet");
        hashKeeper.add("Glasses");
        hashKeeper.add("Phone Number");
        System.out.println(hashKeeper + "\nsize: " + hashKeeper.size());
        hashKeeper.add("Address");
        hashKeeper.add("First");
        hashKeeper.printBuckets();
        System.out.println("contains address: " + hashKeeper.contains("Address"));
        System.out.println("contains ipods: " + hashKeeper.contains("Ipods"));
        System.out.println("removed: " + hashKeeper.remove("Code"));
        System.out.println(hashKeeper + "\nsize: " + hashKeeper.size());
        //if (table[index] == null)
        //                continue;
        System.out.println(hashKeeper.remove("Keys"));
        System.out.println(hashKeeper + "\nsize: " + hashKeeper.size());

    }
}
