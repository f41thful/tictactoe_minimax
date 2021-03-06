package Game;

import java.util.Observable;

public class Board extends Observable{
	public enum SquareState{
		EMPTY, NOTCH, CROSS;
		
		public SquareState alternate(){
			if(this == NOTCH) return CROSS;
			if(this == CROSS) return NOTCH;
			return EMPTY;
		}
	}
	
	public enum Winner{
		NONE, TIE, NOTCH, CROSS;
	}
	
	public static enum MessageType{
		WINNER, SET
	}
	public static class Message{
		public MessageType type;
		public Object value;
		public int row, col;
		
		public Message(MessageType type, Object value, int row, int col){
			this.type = type;
			this.value = value;
			this.row = row;
			this.col = col;
		}
		
		public String toString(){
			return "[Type: " + type  + ", Value: " + value + "]";
		}
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
	
	/*
	 * Create a board with the same board values as board and with the same 
	 * winner value.
	 */
	public Board(Board boardObj){
		board = new SquareState[bR][bC];
		for(int i = 0; i < boardObj.getNumRows(); i++){
			for(int j = 0; j < boardObj.getNumCols(); j++){
				set( i, j, boardObj.get( i, j ) );
			}
		}
		winner = boardObj.winner;
	}
	
	
	public void set(int i, int j, SquareState state){
		board[i][j] = state;
		setChanged();
		notifyObservers(new Message(MessageType.SET, state, i, j));
		calculateWinner();
	}
	
	
	public SquareState get(int i, int j){
		return board[i][j];
	}
	
	public int getNumRows(){
		return bR;
	}
	
	public int getNumCols(){
		return bC;
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
	
	
	public SquareState nextMove(SquareState equal){
		int cross = 0;
		int notch = 0;
		for(SquareState[] ss : board){
			for(SquareState s : ss){
				if(s == SquareState.CROSS) cross++;
				else if(s == SquareState.NOTCH) notch++;
			}
		}
		
		if(cross < notch) return SquareState.CROSS;
		else if(notch < cross) return SquareState.NOTCH;
		else return equal;
	}
	
	public void syncBoard(Board b){
		for(int i = 0; i < b.bR; i++){
			for(int j = 0; j < bC; j++){
				if(b.get( i, j ) != get(i, j)){
					set(i, j, b.get(i, j));
				}
			}
		}
	}


	private Winner calculateWinner(){
		ValueWinner vs = 
						  existsSameNonEmptyValueRow()
				.orWinnerD( existsSameNonEmptyValueCol() )
				.orWinnerD( sameNonEmptyValueDiagonal() );
		if(vs.value || numberOf(SquareState.EMPTY) == 0) setWinner(vs.winner);
		else setWinner(Winner.NONE);

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
	
	
	private void setWinner(Winner winner){
		this.winner = winner;
		setChanged();
		notifyObservers( new Message(MessageType.WINNER, winner, 0, 0) );
	}
}
