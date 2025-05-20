package game.tests;

import java.util.Collection;
import game.*;

public class GameTest extends Test {

    public static String arToStr(Object[] moves) {
        String s = "";
        for (int i = 0; i < moves.length; i++) {
            Move move = (Move) moves[i]; // cast each object of array to type Move
            s = s + "(" + move.getRow() + "," + move.getCol() + ")";
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
        Game game = new GameImpl(5);
        expect(PieceColour.WHITE, game.currentPlayer());
        game.makeMove(new MoveImpl(0, 0));
        expect("W....\n.....\n.....\n.....\n.....\n", game.getGrid().toString());
        var moves = game.getMoves().toArray();
        expect("(0,1)\n(0,2)\n(0,3)\n(0,4)\n(1,0)\n(1,1)\n(1,2)\n(1,3)\n(1,4)\n(2,0)\n(2,1)\n(2,2)\n(2,3)\n(2,4)\n(3,0)\n(3,1)\n(3,2)\n(3,3)\n(3,4)\n(4,0)\n(4,1)\n(4,2)\n(4,3)\n(4,4)\n", arToStr(moves));
        game.makeMove(new MoveImpl(1, 0));
        expect("W....\nB....\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(0, 1));
        expect("WW...\nB....\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(1, 1));
        expect("WW...\nBB...\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(0, 2));
        expect("WWW..\nBB...\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(1, 2));
        expect("WWW..\nBBB..\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(0, 3)); 
        expect("WWWW.\nBBB..\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(1, 3)); 
        expect("WWWW.\nBBBB.\n.....\n.....\n.....\n", game.getGrid().toString());

        game.makeMove(new MoveImpl(0, 4)); // white wins here
        expect("WWWWW\nBBBB.\n.....\n.....\n.....\n", game.getGrid().toString());
        var moves_2 = game.getMoves().toArray();
        expect("(1,4)\n(2,0)\n(2,1)\n(2,2)\n(2,3)\n(2,4)\n(3,0)\n(3,1)\n(3,2)\n(3,3)\n(3,4)\n(4,0)\n(4,1)\n(4,2)\n(4,3)\n(4,4)\n", arToStr(moves_2));
        expect(true, game.isOver());
        expect(PieceColour.WHITE, game.winner());

        Game game_2 = new GameImpl(5);
        try {
            game_2.makeMove(new MoveImpl(5, 5)); 
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(true, caught);

        Game game_3 = new GameImpl(5);
        game_3.makeMove(new MoveImpl(1, 3)); 
        try {
            game_3.makeMove(new MoveImpl(1, 3)); 
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(true, caught);

        checkAllTestsPassed();
    }
}
