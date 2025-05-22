package game.tests;

import java.util.Collection;
import game.*;

public class GameTest extends Test {

    public static String arToStr(Object[] moves) { //method convert array to string
        
        String s = "";
        for (int i = 0; i < moves.length; i++) {
            Move move = (Move) moves[i]; // cast each object of array to type Move
            s = s + "(" + move.getRow() + "," + move.getCol() + ")"; //concatonates move to result string
            s += "\n";
        }
        return s;
    }
    public static void main(String[] args) {
        boolean caught = false;
        try {
            new GameImpl(0);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(true, caught);

        caught = false;
        try {
            new GameImpl(5);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(false, caught);

        caught = false;
        try {
            new GameImpl("5"); //tests if int is passed in as string
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(false, caught);

        caught = false;
        try {
            new GameImpl("B"); //tests exception if a non integer charatcer is entered
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(true, caught);

    

        Game game = new GameImpl(5);
        expect(PieceColour.WHITE, game.currentPlayer()); //checks that current player is white
        game.makeMove(new MoveImpl(0, 0));
        expect("W....\n.....\n.....\n.....\n.....\n", game.getGrid().toString()); // checks that makeMove functions correctly by comparing grid strings
        var moves = game.getMoves().toArray();
        expect(PieceColour.BLACK, game.currentPlayer()); //checks that current player correctly alternates 
        // checks that valid moves correctly updates, uses custome arToString method to do so
        expect("(0,1)\n(0,2)\n(0,3)\n(0,4)\n(1,0)\n(1,1)\n(1,2)\n(1,3)\n(1,4)\n(2,0)\n(2,1)\n(2,2)\n(2,3)\n(2,4)\n(3,0)\n(3,1)\n(3,2)\n(3,3)\n(3,4)\n(4,0)\n(4,1)\n(4,2)\n(4,3)\n(4,4)\n", arToStr(moves));

        //code below simulates a game and checks the grid string after each move 
        game.makeMove(new MoveImpl(1, 0));
        expect("W....\nB....\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(0, 1));
        expect("WW...\nB....\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(1, 1));
        expect("WW...\nBB...\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(0, 2));
        expect("WWW..\nBB...\n.....\n.....\n.....\n", game.getGrid().toString());

        expect(false, game.isOver());

        game.makeMove(new MoveImpl(1, 2));
        expect("WWW..\nBBB..\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(0, 3)); 
        expect("WWWW.\nBBB..\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(1, 3)); 
        expect("WWWW.\nBBBB.\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(0, 4)); // white wins here
        expect("WWWWW\nBBBB.\n.....\n.....\n.....\n", game.getGrid().toString());
        var moves_2 = game.getMoves().toArray();
        // checks that validMoves list has correctly updated by end of game
        expect("(1,4)\n(2,0)\n(2,1)\n(2,2)\n(2,3)\n(2,4)\n(3,0)\n(3,1)\n(3,2)\n(3,3)\n(3,4)\n(4,0)\n(4,1)\n(4,2)\n(4,3)\n(4,4)\n", arToStr(moves_2));
        expect(true, game.isOver());
        expect(PieceColour.WHITE, game.winner());
        expect(PieceColour.NONE, game.currentPlayer());

        //simulates another game but should be draw
        Game game_4 = new GameImpl(5);
        game_4.makeMove(new MoveImpl(0, 0));
        game_4.makeMove(new MoveImpl(0,1)); 
        game_4.makeMove(new MoveImpl(0,2)); 
        game_4.makeMove(new MoveImpl(0,3)); 
        game_4.makeMove(new MoveImpl(0,4)); 
        game_4.makeMove(new MoveImpl(1,0)); 
        game_4.makeMove(new MoveImpl(1,1)); 
        game_4.makeMove(new MoveImpl(1,2)); 
        game_4.makeMove(new MoveImpl(1,3)); 
        game_4.makeMove(new MoveImpl(1,4)); 
        game_4.makeMove(new MoveImpl(2,0)); 
        game_4.makeMove(new MoveImpl(2,1)); 
        game_4.makeMove(new MoveImpl(2,2)); 
        game_4.makeMove(new MoveImpl(2,3)); 
        game_4.makeMove(new MoveImpl(2,4)); 
        game_4.makeMove(new MoveImpl(3,0)); 
        game_4.makeMove(new MoveImpl(3,1)); 
        game_4.makeMove(new MoveImpl(3,2)); 
        game_4.makeMove(new MoveImpl(3,3)); 
        game_4.makeMove(new MoveImpl(3,4)); 
        game_4.makeMove(new MoveImpl(4,0)); 
        game_4.makeMove(new MoveImpl(4,1)); 
        game_4.makeMove(new MoveImpl(4,2)); 
        game_4.makeMove(new MoveImpl(4,3)); 
        game_4.makeMove(new MoveImpl(4,4));

        // expect grid to be completely filled
        expect("WBWBW\nBWBWB\nWBWBW\nBWBWB\nWBWBW\n", game_4.getGrid().toString());

        // expect no more valid moves
        expect(0, game_4.getMoves().size());
        expect(true, game_4.isOver());
        expect(PieceColour.NONE, game_4.winner()); //therefore should be draw and return NONE as winning player



        Game game_2 = new GameImpl(5);
        
        try {
            game_2.makeMove(new MoveImpl(5, 5)); //exception for exception when invalid move is entered
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(true, caught);

        //checks the correctness of the deepy copy by comparing memory references, and content of copy and original
        Game game_3 = new GameImpl(5);
        game_3.makeMove(new MoveImpl(1, 3)); 
        Game copy = game_3.copy();
        expect(false, game_3 == copy);
        copy.makeMove(new MoveImpl(1, 4)); 
        
        // check the grids are different
        expect(".....\n...W.\n.....\n.....\n.....\n", game_3.getGrid().toString()); // Only original move
        expect(".....\n...WB\n.....\n.....\n.....\n", copy.getGrid().toString());    // Additional move in copy
                

        checkAllTestsPassed();
    }
}
