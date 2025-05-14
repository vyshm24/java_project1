package game;

public class GridImpl implements Grid, Cloneable {

    private int grid_size; 
    private char[][] board;


    public GridImpl(int i) {
        grid_size = i;
        board = new char[i][i];

        for (int row = 0; row < i; row++) {
            for (int col = 0; col < i; col++) {
                board[row][col] = '.';
            }
        }
        
    }

    @Override
    public String toString() {
        String result = new String();
        for (int row = 0; row < grid_size; row++) {
            for (int col = 0; col < grid_size; col++) {
                result += board[row][col];
            }
            
            result += "\n";
            
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
        }
        
        if (board[row][col] == 'W'){
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
       
        switch (piece){
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
            }
        }

        return copy;

    }

}
    

