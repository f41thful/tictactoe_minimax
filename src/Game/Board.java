package Game;

public class Board {
	public enum SquareState{
		EMPTY, NOTCH, CROSS;
	}
	
	public enum Winner{
		NONE, TIE, NOTCH, CROSS;
	}
	
	public class ValueWinner{
		public boolean value;
		public Winner winner;
		
		public ValueWinner(){
			winner = Winner.TIE;
		}

		public ValueWinner orWinnerD(ValueWinner vsP){
			ValueWinner vs = new ValueWinner();
			vs.value = value || vsP.value;
			if(value) vs.winner = winner;
			if(vsP.value) vs.winner = vsP.winner;
			return vs;
		}
		
		public void valueOf(SquareState ss){
			if(ss == SquareState.NOTCH) winner = Winner.NOTCH;
			else if(ss == SquareState.CROSS) winner = Winner.CROSS;
			else if(ss == SquareState.EMPTY) winner = Winner.TIE;
		}
	}
	
	Winner winner;
	
	SquareState[][] board;
	int bR = 3;
	int bC = 3;
	
	public Board(){
		board = new SquareState[bR][bC];
		for(int i = 0; i < bR; i++){
			for(int j = 0; j < bC; j++){
				board[i][j] = SquareState.EMPTY;
			}
		}
		
		winner = Winner.NONE;
	}
	
	
	public void set(int i, int j, SquareState state){
		board[i][j] = state;
	}
	
	
	public SquareState get(int i, int j){
		return board[i][j];
	}
	
	public int numberOf(SquareState s){
		int n = 0;
		for(SquareState[] s0 : board){
			for(SquareState s1 : s0){
				if(s1 == s) n++;
			}
		}
		return n;
	}
	
	public String toString(){
		String v = "";
		for(int i = 0; i < 3; i++){
			v = v + "[";
			for(int j = 0; j < 3; j++){
				v = v + board[i][j] + ", ";
			}
			v = v + "]\n";
		}
		return v;
	}
	
	public Winner getWinner(){
		if(winner != winner.NONE) return winner;
		calculateWinner();
		return winner;
	}
	
	private Winner calculateWinner(){
		ValueWinner vs = 
						  existsSameNonEmptyValueRow()
				.orWinnerD( existsSameNonEmptyValueCol() )
				.orWinnerD( sameNonEmptyValueDiagonal() );
		if(vs.value || numberOf(SquareState.EMPTY) == 0) winner = vs.winner;
		else winner = Winner.NONE;

		return winner;
	}
	
	private ValueWinner sameNonEmptyValueRow(int row){
		ValueWinner vs = new ValueWinner();
		if(board[row][0] == SquareState.EMPTY){
			vs.value = false;
			return vs;
		}
		vs.valueOf(board[row][0]);
		vs.value = board[row][0] == board[row][1];
		vs.value =  vs.value && (board[row][0] == board[row][2]);
		return vs;
	}
	
	private ValueWinner existsSameNonEmptyValueRow(){
		return sameNonEmptyValueRow(0).orWinnerD(sameNonEmptyValueRow(1)).orWinnerD(sameNonEmptyValueRow(2));
	}
	
	private ValueWinner sameNonEmptyValueCol(int col){
		ValueWinner vs = new ValueWinner();
		if(board[0][col] == SquareState.EMPTY){
			vs.value = false;
			return vs;
		}
		vs.valueOf(board[0][col]);
		vs.value = board[0][col] == board[1][col];
		vs.value = vs.value && (board[0][col] == board[2][col]);
		return vs;
	}
	
	private ValueWinner existsSameNonEmptyValueCol(){
		return sameNonEmptyValueCol(0).orWinnerD(sameNonEmptyValueCol(1)).orWinnerD(sameNonEmptyValueCol(2));
	}
	
	private ValueWinner sameNonEmptyValueMainDiagonal(){
		ValueWinner vs = new ValueWinner();
		if(board[0][0] == SquareState.EMPTY){
			vs.value = false;
			return vs;
		}
		vs.valueOf(board[0][0]);
		vs.value = board[0][0] == board[1][1];
		vs.value = vs.value && (board[0][0] == board[2][2]);
		return vs;
	}
	
	private ValueWinner sameNonEmptyValueInverseDiagonal(){
		ValueWinner vs = new ValueWinner();
		if(board[0][2] == SquareState.EMPTY){
			vs.value = false;
			return vs;
		}
		vs.valueOf(board[0][2]);
		vs.value = board[0][2] == board[1][1];
		vs.value = vs.value && (board[0][2] == board[2][0]);
		return vs;
	}
	
	private ValueWinner sameNonEmptyValueDiagonal(){
		return sameNonEmptyValueMainDiagonal().orWinnerD(sameNonEmptyValueInverseDiagonal());
	}
	
	
}