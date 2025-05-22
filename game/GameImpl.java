package game;

import java.util.ArrayList;
import java.util.Collection;

public class GameImpl implements Game{
  
    private Grid grid;
    private PieceColour player;

    public GameImpl(int i) { //constructor that creates a new object for each game
        if (i <= 0){
            throw new IllegalArgumentException("invalid size number, must be more than 0");
        } 
        
       
        this.grid = new GridImpl(i); //grid size property
        this. player = PieceColour.WHITE; //starter player property

        
    }

    public GameImpl (String input) { //constructor for if new GameImpl object is made with string
    try {
        int num = Integer.parseInt(input);
        if (num <= 0){
            throw new IllegalArgumentException("invalid size number, must be more than 0");
        }
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("nnput must be an integer.");
    }
}

    @Override
    public boolean isOver() {
       if(winner() != PieceColour.NONE || getMoves().isEmpty()) { // checks condition of whether there has been a winner OR run out of moves, to end game
        return true;
       }
       return false; //default return false to keep game going
    }

    @Override
    public PieceColour winner() {
        if (PathFinder.leftToRight(grid, PieceColour.WHITE) || PathFinder.topToBottom(grid, PieceColour.WHITE)){ //checks if path finder has found winning path for black or white either top to bottom or left to right
            return PieceColour.WHITE;
        } else if (PathFinder.leftToRight(grid, PieceColour.BLACK) || PathFinder.topToBottom(grid, PieceColour.BLACK)){ //vice versa for black 
            return PieceColour.BLACK;
        }
        return PieceColour.NONE; //returns none as default
        
    }

    @Override
    public PieceColour currentPlayer() {
        if(isOver()){ //calls isOver method, if isOver is true, returns value inside conditional 
            return PieceColour.NONE; //returns none if game is over 
        }
        return player; //returns player var value
    
    }

    public void nextPlayer(){ //additionally added method to alternate between players every turn
        if (player == PieceColour.WHITE){  //if current player is black, change to white for next turn
            player = PieceColour.BLACK;
        } else if (player == PieceColour.BLACK){ //vice versa
            player = PieceColour.WHITE;
        }
    }

    @Override
    public Collection<Move> getMoves() {
        Collection<Move> validmoves = new ArrayList<>(); //created new arrayList to store valid moves
        int size = grid.getSize(); //defines size of grid
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) { //nested loop to go through each row and column pair
                if (grid.getPiece(row, col) == PieceColour.NONE) { //check if its occupied and if not, add vnew MoveImpl object to array 
                    validmoves.add(new MoveImpl(row, col));
                }
            }
        }

        return validmoves;
        
    }

    @Override
    public void makeMove(Move move) {
        if (isOver()){
            return; //just returns when game is over since nothing is meant to happen
        }
       
        
        int row = move.getRow(); //uses getRow and getCol method from move class
        int col = move.getCol();

       if (row < 0 || row >= grid.getSize() || col < 0 || col >= grid.getSize()) {
            throw new IllegalArgumentException("Move is out of bounds");
        }   //throws  exception if entered coordinate is out of bounds, by comparing values of entered coords to grid size 
       if (grid.getPiece(row, col) != PieceColour.NONE) {
            throw new IllegalArgumentException("Spot Occupied"); 
        } 
        grid.setPiece(row, col, player); //uses setPiece method from grid class
        nextPlayer(); 

        
    }

    @Override
    public Grid getGrid() {
        Grid copy = grid.copy(); //returns deep copy of grid by calling copy() from grid class
        return copy;
    }

    private GameImpl(GameImpl other) { //constructor for deep copy using other GameImpl object as parameter
        this.grid = other.grid.copy(); 
        this.player = other.player;    
    }

    @Override
    public Game copy() {
        return new GameImpl(this);
    }

    
    
    
}
