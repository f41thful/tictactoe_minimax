package Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Game.Board.SquareState;
import Logic.Minimax.IMinimaxStructure;

public class AIMinimaxNaive implements IMinimaxStructure<Board>{

	@Override
	public List<Board> generate(Board elem) {
		List<Board> boards = new ArrayList<Board>();
		for(int i = 0; i < elem.getNumRows(); i++){
			for(int j = 0; j < elem.getNumCols(); j++){
				if(elem.get( i, j ) == SquareState.EMPTY){
					generateBoards(elem, i, j, boards);
				}
			}
		}
		return boards;
	}

	@Override
	public int evaluate(Board value) {
		return 0;
	}
	
	private void generateBoards(Board elem, int i, int j, Collection<Board> boards){
		SquareState[] opts = {SquareState.CROSS, SquareState.EMPTY};
		for(SquareState op : opts){
			Board newBoard = new Board(elem);
			newBoard.set( i, j, op );
			boards.add( newBoard );
		}
	}

}
