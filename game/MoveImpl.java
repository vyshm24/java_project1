package game;
public class MoveImpl implements Move {

    private int row;
    private int col;

    public MoveImpl(int i, int j) {
        row = i;
        col = j;
        

    }

    @Override
    public int getRow() {
        return row;
     
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "("+ row + "," + col + ")";
    }
    
}
