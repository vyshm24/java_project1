package game;

public class GridImpl implements Grid, Cloneable {

    private int grid_size; 
    private char[][] board;


    public GridImpl(int i) { //grid constructor
        grid_size = i;
        board = new char[i][i]; //board is square 2D array 

        for (int row = 0; row < i; row++) {
            for (int col = 0; col < i; col++) {
                board[row][col] = '.'; //fills grid with dots using nested loop
            }
        }
        
    }

    @Override
    public String toString() {
        String result = new String(); //creates string object
        for (int row = 0; row < grid_size; row++) {
            for (int col = 0; col < grid_size; col++) {
                result += board[row][col]; //concatonates each character of board to string
            }
            
            result += "\n";  //new line at end of each row
            
        }

        return result;
    }
        
    

    @Override
    public int getSize() {
        return grid_size;

    }

    @Override
    public PieceColour getPiece(int row, int col) {
        if (row < 0 || row >= grid_size || col < 0 || col >= grid_size) {
            throw new IllegalArgumentException("Out of bounds");
        } //checks if entered coordinate is out of bounds
        
        if (board[row][col] == 'W'){ //use conditional branches to assign a PieceColor to return for each type of detected character
            return PieceColour.WHITE; 
        } 
        else if (board[row][col] == 'B'){
            return PieceColour.BLACK;
        }
        return PieceColour.NONE;
    }

    @Override
    public void setPiece(int row, int col, PieceColour piece) {
        if (row < 0 || row >= grid_size || col < 0 || col >= grid_size) {
            throw new IllegalArgumentException("Out of bounds");
        }
       
        switch (piece){ //use switch since its best used with enum data type of PieceColour
            case PieceColour.WHITE:
                board[row][col] = 'W';
                break;
            case PieceColour.BLACK:
                board[row][col] = 'B';
                break;

        }
        
    }

    @Override
    public Grid copy() { 
        
        GridImpl copy = new GridImpl(this.grid_size);

        for (int row = 0; row < grid_size; row++) {
            for (int col = 0; col < grid_size; col++) {
                copy.board[row][col] = this.board[row][col];
            } // uses nested loop to duplicate grid charatcers from original to copy grid object
        }

        return copy;

    }

}
    

