package game;
public class MoveImpl implements Move {

    private int row;
    private int col;

    public MoveImpl(int i, int j) { //constructor for move object
        row = i; //assigns first value to row
        col = j; //second value to column
        

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
        return "("+ row + "," + col + ")"; //returns move coordinate which is a concatonated string to 
    }
    
}
