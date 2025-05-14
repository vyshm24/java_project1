package game;

import java.util.Collection;

public class GameImpl implements Game{
    private boolean game_loop = true;
    private Grid grid;
    private PieceColour player = PieceColour.WHITE;

    public GameImpl(int i) {
        this.grid = new GridImpl(i);
        game_loop = false;
        
    }

    @Override
    public boolean isOver() {
        if (game_loop == true){
            return true;
        }
        return false;
    }

    @Override
    public PieceColour winner() {
        
        throw new UnsupportedOperationException("Unimplemented method 'winner'");
    }

    @Override
    public PieceColour currentPlayer() {
        return player;
    
        
    }
    public void nextPlayer(){
        if (player == PieceColour.WHITE){
            player = PieceColour.BLACK;
        } else if (player == PieceColour.BLACK){
            player = PieceColour.WHITE;
        }
    }

    @Override
    public Collection<Move> getMoves() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getMoves'");
    }

    @Override
    public void makeMove(Move move) {
        // Executes a move for the current player
        // Updates the internal game state to reflect the move
        // Changes the current player to the other colour after the move is made
        // If the game is over, the result of this method is undefined
        // That is, it does not matter what this method does if the game is over
        // Throws an IllegalArgumentException if the move is invalid
        // An invalid move is one where the position is already occupied
        // or the position is out of bounds
        
        int row = move.getRow();
        int col = move.getCol();

        if (grid.getPiece(row, col) != null){
            throw new IllegalArgumentException("Spot Occupied");
        }
        try{
            grid.setPiece(row, col, player);
        } catch (IllegalArgumentException e){
            
        }
        grid.setPiece(row, col, player);
        nextPlayer();

        
    }

    @Override
    public Grid getGrid() {
        Grid copy = grid.copy();
        return copy;
    }

    @Override
    public Game copy() {
        
        throw new UnsupportedOperationException("Unimplemented method 'copy'");
    }
    
}
