package game;

import java.util.Collection;

public class GameImpl implements Game{

    public GameImpl(int i) {
        
    }

    @Override
    public boolean isOver() {
        
        throw new UnsupportedOperationException("Unimplemented method 'isOver'");
    }

    @Override
    public PieceColour winner() {
        
        throw new UnsupportedOperationException("Unimplemented method 'winner'");
    }

    @Override
    public PieceColour currentPlayer() {
        
        throw new UnsupportedOperationException("Unimplemented method 'currentPlayer'");
    }

    @Override
    public Collection<Move> getMoves() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getMoves'");
    }

    @Override
    public void makeMove(Move move) {
        
        throw new UnsupportedOperationException("Unimplemented method 'makeMove'");
    }

    @Override
    public Grid getGrid() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getGrid'");
    }

    @Override
    public Game copy() {
        
        throw new UnsupportedOperationException("Unimplemented method 'copy'");
    }
    
}
