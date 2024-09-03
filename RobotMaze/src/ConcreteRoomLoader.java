import java.util.*;
import java.io.*;

public class ConcreteRoomLoader extends AbstractRoomLoader {
    HashMap<Character, Door> h = new HashMap<Character, Door>();

    public ConcreteRoomLoader() {
        cave = new CaveData();
        //deserialize("aarav.ser");
    }

    public void load() {
        h.put('r', Door.RED);
        h.put('g', Door.GREEN);
        h.put('b', Door.BLUE);
        h.put('p', Door.PINK);
        h.put('y', Door.YELLOW);

        String line = "";
        try {
//            System.out.print("test");
            File file = new File("C:\\JavaProj\\RobotMaze\\src\\labrynth1.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<Room> arr = new ArrayList<Room>();
            int i = 0;
            for (int j = 1; j < 33; j++) {//weird indexing: only 31 rooms
                Room room = new Room(j + "", "");
                cave.addRoom(room);
                arr.add(room);
                if (j == 1)
                    cave.setStart(room);
                if (j == 30)
                    cave.setEnd(room);
            }

            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                Room room = arr.get(i++);//i++ adds it afterwards to this is index 0 which should have nothing
                while (st.hasMoreTokens()) {
//                    System.out.print("test");
                    String s = st.nextToken();
                    char[] c = s.toCharArray();
                    if (c.length == 3)
                        room.addDoor(h.get(c[0]),
                                arr.get(Integer.parseInt(Character.toString(c[1]) + Character.toString(c[2])) - 1));
                    else
                        room.addDoor(h.get(c[0]), arr.get(Integer.parseInt(Character.toString(c[1])) - 1));

                    Integer integer = Integer.parseInt(Character.toString(c[1]));
                    System.out.print(integer.toString());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

//public class ConcreteRoomLoader extends AbstractRoomLoader{
//    public void load()
//    {
//        /* Test 1
//        cave = new CaveData();
//        //start = new Room("start", "first");
//        Room start = new Room("one", "level one");
//        Room  r2 = new Room("two", "level two");
//        Room r3 = new Room("three", "level two");
//        Room r4 = new Room("four", "level three");
//        Room r5 = new Room("five", "level three");
//        Room r6 = new Room("six", "test");
//        Room r7= new Room("seven", "test");
//        Room r8 = new Room("eight", "test");
//
//
//       // start.addDoor(Door.RED,  r1);
//        start.addDoor(Door.BLUE, r2);
//        start.addDoor(Door.RED, r3);
//        r2.addDoor(Door.GREEN, r3);
//        r2.addDoor(Door.YELLOW, r4);
//        r2.addDoor(Door.PINK, r5);
//        r5.addDoor(Door.GREEN, r6);
//        r3.addDoor(Door.BLUE, r7);
//        r3.addDoor(Door.PINK, r8);
//
//        cave.addRoom(start);
//        cave.addRoom(r2);
//        cave.addRoom(r3);
//        cave.addRoom(r4);
//        cave.addRoom(r5);
//        cave.addRoom(r6);
//        cave.addRoom(r7);
//        cave.addRoom(r8);
//        cave.setStart(start);
//        cave.setEnd(r8);*/
//
//        /*Test 2
//        cave = new CaveData();
//        //start = new Room("start", "first");
//        Room start = new Room("one", "level one");
//        Room  r2 = new Room("two", "level two");
//        Room r3 = new Room("three", "level two");
//        Room r4 = new Room("four", "level three");
//        Room r5 = new Room("five", "level three");
//        Room r6 = new Room("six", "test");
//        Room r7= new Room("seven", "test");
//        Room r8 = new Room("eight", "test");
//
//
//        // start.addDoor(Door.RED,  r1);
//        start.addDoor(Door.BLUE, r2);
//        start.addDoor(Door.RED, r3);
//        r2.addDoor(Door.GREEN, r3);
//        r2.addDoor(Door.YELLOW, r4);
//        r2.addDoor(Door.PINK, r5);
//        r5.addDoor(Door.GREEN, r6);
//        r3.addDoor(Door.BLUE, r7);
//        r3.addDoor(Door.PINK, r8);
//
//        cave.addRoom(start);
//        cave.addRoom(r2);
//        cave.addRoom(r3);
//        cave.addRoom(r4);
//        cave.addRoom(r5);
//        cave.addRoom(r6);
//        cave.addRoom(r7);
//        cave.addRoom(r8);
//        cave.setStart(start);
//        cave.setEnd(r7);*/
//
//        //Test 3
//        cave = new CaveData();
//        //start = new Room("start", "first");
//        Room start = new Room("one", "level one");
//        Room  r2 = new Room("two", "level two");
//        Room r3 = new Room("three", "level two");
//        Room r4 = new Room("four", "level three");
//        Room r5 = new Room("five", "level three");
//        Room r6 = new Room("six", "test");
//        Room r7= new Room("seven", "test");
//        Room r8 = new Room("eight", "test");
//
//
//        // start.addDoor(Door.RED,  r1);
//        start.addDoor(Door.BLUE, r2);
//        start.addDoor(Door.RED, r3);
//        r2.addDoor(Door.GREEN, r3);
//        r2.addDoor(Door.YELLOW, r4);
//        r2.addDoor(Door.PINK, r5);
//        r5.addDoor(Door.GREEN, r6);
//        r3.addDoor(Door.BLUE, r7);
//        r3.addDoor(Door.PINK, r8);
//
//        cave.addRoom(start);
//        cave.addRoom(r2);
//        cave.addRoom(r3);
//        cave.addRoom(r4);
//        cave.addRoom(r5);
//        cave.addRoom(r6);
//        cave.addRoom(r7);
//        cave.addRoom(r8);
//        cave.setStart(start);
//        cave.setEnd(r6);
////        serialize("aarav.ser");
//
//    }
////    @Override
////    public Room getStart() {
////        // TODO Auto-generated method stub
////        return null;
////    }
////    @Override
////    public Room getEnd() {
////        // TODO Auto-generated method stub
////        return null;
////
////    }
//
//    public static void main(String[] args)
//    {
//
//    }
//}