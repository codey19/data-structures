public class UDB extends MazeElement{
    private boolean goUp;

    public UDB(Location loc, int size, String imgString){
        super(loc, size, imgString);
        goUp = true;
    }

    @Override
    public void move(int key, char[][] maze){
        int r = getLoc().getR();
        int c = getLoc().getC();
        if(goUp && r > 0 && maze[r -1][c] != '#') {
            super.getLoc().incR(-1);
        }else if(!goUp && r < maze.length - 1 && maze[r +1][c] != '#') {
            super.getLoc().incR(+1);
        }else if(r == 0 || maze[r -1][c] == '#'){
            super.getLoc().incR(+1);
            goUp = false;
        }else if(r == maze.length - 1 || maze[r +1][c] == '#'){
            super.getLoc().incR(-1);
            goUp = true;
        }

//        else if(r < maze.length -1 && maze[r + 1][c] == ' ') {
//            super.getLoc().incR(+1);
//            goUp = false;
//        }
    }
}
