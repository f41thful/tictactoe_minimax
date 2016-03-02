package Game;

import Game.Board.SquareState;
import Game.Board.Winner;
import Logic.Minimax;
import Logic.Minimax.IMinimaxStructure;
import lib.tree.Tree;

public class FacadeAI {
	public static Tree<Board> generateNaive(Board b, int depth, int breadth){
		IMinimaxStructure<Board> st = new AIMinimaxNaive( Winner.NOTCH, SquareState.CROSS );
		Minimax<Board> m = new Minimax<>( st, depth, breadth);
		//otherwise b would be the content of the root of the tree, and it will be
		//changed when the main board is changed to update the AI movement.
		Board newBoard = new Board(b);
		return m.generate( newBoard );
	}
}
