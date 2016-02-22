package Launcher;

import Game.Board;
import Game.Board.SquareState;

public class TestLauncher {
	public static void main(String[] args) {
		testWinnerRow();
	}
	
	public static void testWinnerRow(){
		Board b = new Board();
		b.set( 0, 0, SquareState.CROSS );
		b.set( 0, 1, SquareState.CROSS );
		b.set( 0, 2, SquareState.CROSS );
		System.out.println(b);
		System.out.println("Winner: " + b.getWinner());
	}
}
