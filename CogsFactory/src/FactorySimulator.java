import java.util.*;
public class FactorySimulator{
    //Codey S, Shrihith M, Ram T, Puneet 
    private ArrayList<Worker> workers;
    private Queue<Integer> cogOrders;
    private ArrayList<Integer> cogList;

    private static int[] winCnt = new int[3];
    private static int[] totalHours = new int[3];

    public FactorySimulator(ArrayList<Worker> w, ArrayList<Integer> c){
        workers = w;
        cogList = c;
        resetQueue();
    }

    public void resetQueue(){
        cogOrders = new LinkedList<Integer>();
        for(int x : cogList){
            cogOrders.add(x);
        }
    }

    public void run1(){	//runs the factory simulation
        //Assign workers to initial task

        //So long as there are orders on the queue
        //Process one hour at a time
        //Routinely call the workOneHour method
        //Use a Queue (eventually PriorityQueue)
        //To manage work assignment
        //re-assign workers who are not busy
        //Keep going until all orders are complete

        //Print out relevant details for all answers
        //Separate method?
        int hours = 0;
        while(!cogOrders.isEmpty()) {
            for(int i = 0; i < workers.size(); i++) {
                if(!workers.get(i).isBusy() && !cogOrders.isEmpty())
                    workers.get(i).assignOrder(cogOrders.poll());
                workers.get(i).workOneHour();
            }
            hours++;
        }
        while(!allDone()){
            for (Worker w: workers)
                w.workOneHour();
            hours++;
        }
        printResults(hours);
        addToTotal(0, hours);
    }

    public void run2(){//gives workers the orders with minimal waste
        int hours = 0;
        Collections.sort(workers);//prioritize the most efficient workers
        while(!cogOrders.isEmpty()) {
            int numFreeWorkers = getNumFreeWorkers();//gets number of non-busy workers
            ArrayList<Integer> nextOrders = new ArrayList<>();
            for(int i = 0; i < numFreeWorkers; i ++)//takes out appropriate number of orders
                if(!cogOrders.isEmpty())
                    nextOrders.add(cogOrders.poll());
            if(!nextOrders.isEmpty()) {
                Collections.sort(nextOrders);
                for (int i = 0; i < workers.size(); i++)//loop through for zeros, account for division
                    if (!workers.get(i).isBusy() && !nextOrders.isEmpty()) {
                        workers.get(i).assignOrder(assignEfficientOrder(nextOrders, workers.get(i)));
                    }
            }
            //printResults(hours);for debug
            for(int i = 0; i < workers.size(); i++)
                workers.get(i).workOneHour();
            hours++;
        }
        while(!allDone()){
            for (Worker w: workers)
                w.workOneHour();
            hours++;
        }
        printResults(hours);
        addToTotal(1, hours);
    }

    public int assignEfficientOrder(ArrayList<Integer> nextOrders, Worker worker){
        Queue<Integer> modOrders = new PriorityQueue<>();
        for(Integer e: nextOrders)
            modOrders.add(e % worker.getCph());
        int lowestMod = modOrders.poll();
       if(lowestMod != 0){//remove for assignment based solely on mod
            while(!modOrders.isEmpty())//loop through to get to the highest mod
                lowestMod = modOrders.poll();
        }
        int bestOrder = 0;
        for(int i = 0; i < nextOrders.size(); i++){//looks for the highest occurrence of lowestMod(in case of duplicates)
            if(nextOrders.get(i) % worker.getCph() == lowestMod)
                bestOrder = nextOrders.remove(i);
        }
        return bestOrder;
    }

    public void run3(){//gives most efficient worker the largest cog order
        int hours = 0;
        Collections.sort(workers);
        while(!cogOrders.isEmpty()) {
            int numFreeWorkers = getNumFreeWorkers();//gets number of non-busy workers
            Queue<Integer> nextOrders = new PriorityQueue<>();
            for(int i = 0; i < numFreeWorkers; i ++)//takes out appropriate number of orders
                if(!cogOrders.isEmpty())
                    nextOrders.add(cogOrders.poll());
            if(!nextOrders.isEmpty()) {
                for (int i = 0; i < workers.size(); i++)
                    if (!workers.get(i).isBusy() && !nextOrders.isEmpty()) {
                        workers.get(i).assignOrder(nextOrders.poll());
                    }
            }
            for(int i = 0; i < workers.size(); i++)
                workers.get(i).workOneHour();
            hours++;
        }
        while(!allDone()){
            for (Worker w: workers)
                w.workOneHour();
            hours++;
        }
        printResults(hours);
        addToTotal(2, hours);
    }

    public int getNumFreeWorkers(){
        int numFreeWorkers = 0;
        for(int i = 0; i < workers.size(); i++)
            if(!workers.get(i).isBusy())
                numFreeWorkers++;
        return numFreeWorkers;
    }

    public static void main(String[]args) {
        for(int j = 0; j < 100000; j++){
            ArrayList<Worker> workers = new ArrayList();
            int numWorkers = (int) (Math.random() * 10) + 3;
            for (int i = 0; i < numWorkers; i++)
                workers.add(new Worker(i + "", (int) (Math.random() * 56) + 15));
            /*workers.add(new Worker("Simarbir", 30));
            workers.add(new Worker("Ronaldo", 25));*/

            //Queue<Integer> cogs = new LinkedList<>();
            ArrayList<Integer> a = new ArrayList<Integer>();
            int totalCogs = 0;
            for (int i = 0; i < 100; i++) {
                totalCogs += (int) (Math.random() * 81) + 20;
                a.add(totalCogs);
            }
            /*a.add(40);
            a.add(50);
            a.add(15);
            a.add(32);
            a.add(9);
            a.add(14);*/

            FactorySimulator fs = new FactorySimulator(workers, a);
            //System.out.println("Run 1:\tCurrent Order|Produced|Waste\n-------------------------------------");//for debug
            System.out.println("Run 1:\tProduced|Waste\n-----------------------");
            fs.run1();
            fs.resetQueue();
            //System.out.println("Run 2:\tCurrent Order|Produced|Waste\n-------------------------------------");
            System.out.println("Run 2:\tProduced|Waste\n-----------------------");
            fs.run2();
            fs.resetQueue();
            //System.out.println("Run 3:\tCurrent Order|Produced|Waste\n-------------------------------------");
            System.out.println("Run 3:\tProduced|Waste\n-----------------------");
            fs.run3();
            fs.resetQueue();

            int bestIndex = -1;
            int lowestTime = Integer.MAX_VALUE;
            for(int i = 0; i < totalHours.length; i++){
                if(lowestTime > totalHours[i]){
                    bestIndex = i;
                    lowestTime = totalHours[i];
                }else if(lowestTime == totalHours[i]){
                    bestIndex = -2;
                }
            }
            if(bestIndex == -2){
                for(int i = 0; i < totalHours.length; i++){
                    if(lowestTime == totalHours[i])
                        winCnt[i]++;
                }
            }else if(bestIndex != -1){
                winCnt[bestIndex] ++;
            }
            totalHours = new int[totalHours.length];
        }
        System.out.println("Win Counts: ");
        for(int wins: winCnt)
            System.out.print(wins + " ");
    }
    void printResults(int hrs){
        int totalWaste = 0;
        for(int i = 0; i < workers.size(); i++) {
            System.out.println(workers.get(i));
           totalWaste += workers.get(i).getTotalWaste();
        }
        System.out.println("Hours Worked: " + hrs);
        System.out.println("Total Waste: " + totalWaste + "\n");
    }

    public void addToTotal(int runIndex, int hours){
        totalHours[runIndex] += hours;
    }

    public boolean allDone(){
        for(int i = 0; i < workers.size(); i++){
            if(workers.get(i).isBusy()){
                return false;
            }
        }
        return true;
    }
}