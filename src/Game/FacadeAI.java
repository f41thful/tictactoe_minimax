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
		return m.generate( b );
	}
}
