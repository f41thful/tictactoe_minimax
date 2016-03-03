package Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Game.Board.SquareState;
import Game.Board.Winner;
import Logic.Minimax.IMinimaxStructure;

public class AIMinimaxNaive implements IMinimaxStructure<Board>{

	private Winner myPlayer;
	private SquareState firstMove;
	
	public AIMinimaxNaive(Winner player, SquareState firstMove){
		myPlayer = player;
		this.firstMove = firstMove;
	}
	
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
	public int evaluate(Board board) {
		Winner winner = board.getWinner();
		if(winner == myPlayer) return 80;
		else if(winner == Winner.CROSS) return 0;
		else if(board.get(1, 1) == SquareState.NOTCH) return 6;
		
		if(winner == Winner.NONE){
			int value = 1;
			if(board.get(0,0) == SquareState.NOTCH){
				value += 1;
				if(board.get( 2, 2 ) == SquareState.CROSS) value += 6;
			}
			if(board.get(0,2) == SquareState.NOTCH){
				value += 1;
				if(board.get( 2, 0 ) == SquareState.CROSS) value += 6;
			}

			if(board.get(2,0) == SquareState.NOTCH){
				value += 1;
				if(board.get( 0, 2 ) == SquareState.CROSS) value += 6;
				}
			if(board.get(2,2) == SquareState.NOTCH){
				value += 1;
				if(board.get(2,2) == SquareState.NOTCH) value += 6;
			}
			
			return value;
		}
		else return 0;
	}
	
	private void generateBoards(Board elem, int i, int j, Collection<Board> boards){
		Board newBoard = new Board(elem);
		newBoard.set( i, j, elem.nextMove( firstMove ) );
		boards.add( newBoard );
	}

}
