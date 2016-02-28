package Launcher;

import lib.tree.Tree;
import Game.AIMinimaxNaive;
import Game.Board;
import Game.Board.SquareState;
import Game.Board.Winner;
import Logic.Minimax;
import Logic.Minimax.IMinimaxStructure;
import TreeGetters.GetValue;

// AI Playing against itself :D
public class MinimaxNaiveWithoutGUITest {
	
	public static void main(String[] args) {
		Board b = new Board();
		Tree<Board> treeB;
		SquareState firstMove = SquareState.CROSS;
		IMinimaxStructure<Board> ms0 = new AIMinimaxNaive( Winner.CROSS, firstMove);
		IMinimaxStructure<Board> ms1 = new AIMinimaxNaive( Winner.NOTCH, firstMove);
		
		Minimax<Board> m0 = new Minimax<Board>( ms0, 2, 2);
		Minimax<Board> m1 = new Minimax<Board>( ms1, 2, 2);
		
		Minimax<Board> c = m0;
		
		System.out.println("STARTING");
		System.out.println("--------");
		int times = 2;
		int current = 0;
		while(b.getWinner() == Winner.NONE && current < times){
			treeB = c.generate( b );
			b = c.getSol( treeB );
			if(c == m0) c = m1;
			else c = m0;
			
			System.out.println(treeB.toPostOrderStringWithBranchId( 
					new Tree.GetString[]{new GetValue()} ));
			System.out.print("\n\n\n");
			current++;
		}
		
		System.out.println("Winner: " + b.getWinner());
	}
}
