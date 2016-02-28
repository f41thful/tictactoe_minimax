package Launcher;

import lib.tree.Tree;
import Game.AIMinimaxNaive;
import Game.Board;
import Game.Board.Winner;
import Logic.Minimax;
import Logic.Minimax.IMinimaxStructure;
import TreeGetters.GetValue;

// AI Playing against itself :D
public class MinimaxNaiveWithoutGUITest {
	
	public static void main(String[] args) {
		Board b = new Board();
		Tree<Board> treeB;
		IMinimaxStructure<Board> ms0 = new AIMinimaxNaive( Winner.CROSS );
		IMinimaxStructure<Board> ms1 = new AIMinimaxNaive( Winner.NOTCH );
		
		Minimax<Board> m0 = new Minimax<Board>( ms0, 2, Integer.MAX_VALUE);
		Minimax<Board> m1 = new Minimax<Board>( ms1, 2, Integer.MAX_VALUE);
		
		Minimax<Board> c = m0;
		
		System.out.println("STARTING");
		System.out.println("--------");
		
		while(b.getWinner() == Winner.NONE){
			treeB = c.generate( b );
			b = c.getSol( treeB );
			if(c == m0) c = m1;
			else c = m0;
			
			System.out.println(treeB.toPostOrderStringWithBranchId( 
					new Tree.GetString[]{new GetValue()} ));
			System.out.print("\n\n\n");
		}
		
		System.out.println("Winner: " + b.getWinner());
	}
}
