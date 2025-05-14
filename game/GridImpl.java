package game;

public class GridImpl implements Grid {

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
    public int getSize() {
        return grid_size;

    }

    @Override
    public PieceColour getPiece(int row, int col) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getPiece'");
    }

    @Override
    public void setPiece(int row, int col, PieceColour piece) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setPiece'");
    }

    @Override
    public Grid copy() {
        
        throw new UnsupportedOperationException("Unimplemented method 'copy'");
    }
    
}
