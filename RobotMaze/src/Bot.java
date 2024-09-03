import java.util.*;

public class Bot{

    /*
    Optimizations:
        find shorter path between children
        exclude repetitive movement
    */

    private Room currRoom;
    private static AbstractRoomLoader loader;
    private String str;
    private static Set<Room> visited;
    private static ArrayList<ArrayList<Room>> depthList = new ArrayList<>();
    public static int steps = 0;

    public void load(){
        loader = new ConcreteRoomLoader();
        //loader.load();
        loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\rickRoll.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\MyFile.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\Simarbir & Company.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\KVB.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\CuriousGeorge.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\HaversHomiesCave.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\AbovePAR.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\Aryan.ser");
//        loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\BWTAlpineF1.ser");
        //loader.deserialize("C:\\JavaProj\\RobotMaze\\src\\3A_Caves\\CrystallerCavers.SER");
        currRoom = loader.getStart();
        //str = "["+currRoom.getID()+"]";
    }

    public String run(){
//        steps = pat
        return formatString(shortestPath());
    }

    public static ArrayList<Door> shortestPath() {
        Room start = loader.getStart();
        Room end = loader.getEnd();

        ArrayList<Room> rooms = new ArrayList<>();//list of all the rooms at a certain depth
        rooms.add(start);
        depthList.add(rooms);
        visited = new HashSet<>();//all visited Rooms
        visited.add(start);

        int depth = 0;

        ArrayList<Room> path = new ArrayList<>();//overall path of Rooms taken by the bot
        path.add(start);

        ArrayList<Door> shortestPath = new ArrayList<>();
        depth = explore(rooms, path, depth);
        //method
        Room cur = loader.getEnd();
        for(int i = depthList.size() - 1; i >= 0; i --) {
            Set<Door> doors = cur.getDoors();
            for(Door d: doors){
                boolean found = false;
                for(Room r: depthList.get(i)){
                    if(cur.enter(d).equals(r)) {
                        cur = r;
                        found = true;
                        break;
                    }
                }
                if(found) {
                    shortestPath.add(0, d);
                    break;
                }
            }
        }
        steps = path.size();
        return shortestPath;
    }

    public static int explore(ArrayList<Room> rooms, ArrayList<Room> path, int depth){
        ArrayList<Room> nextRooms = new ArrayList<>();
        Room prev = rooms.get(rooms.size() - 1);
        for(int i = rooms.size() - 1; i >= 0; i --){//looping through ALL the children at the next depth level
            Room cur = rooms.get(i);
            addToPath(path, prev, cur);//traverses to the neighboring Room at the same depth level
            //cur -> prev
            Set<Door> doors = cur.getDoors();
            for(Door d: doors){//go to neighbors of cur at the next depth level

                cur = enterRoom(path, cur, d);//enters room and adds to path
                if(visited.contains(cur)) {//prevents cycles
                    cur = enterRoom(path, cur, d);
                    continue;
                }
                if(cur.equals(loader.getEnd()))//if we reach the end
                    return depth + 1;
                visited.add(cur);
                nextRooms.add(cur);

                cur = enterRoom(path, cur, d);//go back to original cur
            }
            prev = cur;
        }
        depthList.add(nextRooms);
        path.add(nextRooms.get(nextRooms.size() - 1));
        return explore(nextRooms, path, depth + 1);
    }

    public static Room enterRoom(ArrayList<Room> path, Room cur, Door d){
        cur = cur.enter(d);
        path.add(cur);
        return cur;
    }

    public static void addToPath(ArrayList<Room> path, Room prev, Room cur){
        ArrayList<Room> newPath = new ArrayList<>();
        if(prev.equals(cur))
            return;
        //boolean started = false;
        for(int i = path.size() - 1; i >= 0; i--){
            if(path.get(i).equals(cur)){
                //started = true;
                newPath = new ArrayList<>();
                newPath.add(path.get(i));
            }else if(path.get(i).equals(prev))
                break;
            else
                newPath.add(path.get(i));
        }
        path.addAll(newPath);
    }

    public static String formatString(ArrayList<Door> doors){
        String s = "";
        for(Door d: doors)
            s += d + "=>";
        return s.substring(0, s.length() - 2);
    }

    public static void main(String[]args){
        Bot bot = new Bot();
        bot.load();
        System.out.println(bot.run());
    }




}