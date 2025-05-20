package game;

import java.util.ArrayList;
import java.util.Collection;

public class GameImpl implements Game{
  
    private Grid grid;
    private PieceColour player;

    public GameImpl(int i) {
        if (i <= 0){
            throw new IllegalArgumentException("Cannot make grid that size");
        }
        this.grid = new GridImpl(i);
        this. player = PieceColour.WHITE;

        
    }

    @Override
    public boolean isOver() {
       if(winner() != PieceColour.NONE || getMoves().isEmpty()) {
        return true;
       }
       return false;
    }

    @Override
    public PieceColour winner() {
        if (PathFinder.leftToRight(grid, PieceColour.WHITE) || PathFinder.topToBottom(grid, PieceColour.WHITE)){
            return PieceColour.WHITE;
        } else if (PathFinder.leftToRight(grid, PieceColour.BLACK) || PathFinder.topToBottom(grid, PieceColour.BLACK)){
            return PieceColour.BLACK;
        }
        return PieceColour.NONE;
        
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
        Collection<Move> validmoves = new ArrayList<>();
        int size = grid.getSize(); 
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid.getPiece(row, col) == PieceColour.NONE) {
                    validmoves.add(new MoveImpl(row, col));
                }
            }
        }

        return validmoves;
        
    }

    @Override
    public void makeMove(Move move) {
       
        
        int row = move.getRow();
        int col = move.getCol();

       if (row < 0 || row >= grid.getSize() || col < 0 || col >= grid.getSize()) {
            throw new IllegalArgumentException("Move is out of bounds");
        }   
       if (grid.getPiece(row, col) != PieceColour.NONE) {
            throw new IllegalArgumentException("Spot Occupied"); 
        }
        grid.setPiece(row, col, player);
        nextPlayer();

        
    }

    @Override
    public Grid getGrid() {
        Grid copy = grid.copy();
        return copy;
    }

    private GameImpl(GameImpl other) {
        this.grid = other.grid.copy(); 
        this.player = other.player;    
    }

    @Override
    public Game copy() {
        return new GameImpl(this);
    }

    
    
    
}
