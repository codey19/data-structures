public class Worker implements Comparable<Worker>{

    private String name;
    private int cph; // hourly rate of cog production
    private int totalCogsProduced;
    private int totalWaste;
    private int cogsInProgress;

    public Worker(String n, int cph){
        name = n;
        this.cph = cph;
        totalCogsProduced = 0;
        totalWaste = 0;
        cogsInProgress = 0;
    }

    public String getName(){
        return name;
    }

    public int getCph(){
        return cph;
    }

    public int getTotalCogsProduced(){
        return totalCogsProduced;
    }

    public int getTotalWaste(){
        return totalWaste;
    }

    public void reset(){
        totalCogsProduced = 0;
        totalWaste = 0;
        cogsInProgress = 0;
    }

    public boolean isBusy(){
        return cogsInProgress > 0;
    }

    public void assignOrder(int x){
        cogsInProgress = x;
    }

    public void workOneHour(){
        int cip = cogsInProgress;
        if(cogsInProgress == 0)
            return;
        cogsInProgress -= cph;
        if(cogsInProgress < 0) {
            totalCogsProduced += cip;
            cogsInProgress = 0;
            totalWaste += cph - cip ;
        }else if(cip != 0){
            totalCogsProduced += cph;
        }
        //update cogsInProgress based on cph
        //update totalCogsProduced
        //add to waste if relevant
        //Do not let inProgress go negative
    }

    public String toString(){
       //return name + "-" + cph +": " + cogsInProgress + "|" +  totalCogsProduced + "|" + totalWaste;// for debug
        return name + "-" + cph +": " + totalCogsProduced + "|" + totalWaste;
    }

    @Override
    public int compareTo(Worker o) {
        return this.cph - o.getCph();
    }
}