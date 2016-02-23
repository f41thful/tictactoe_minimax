package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GUI.BoardGUI;
import Game.Board.SquareState;

public class TicTacToeGameManager implements ActionListener{
	public BoardGUI gui;
	Board b;
	
	SquareState nextValue;
	public TicTacToeGameManager(BoardGUI gui, Board b){
		this.gui = gui;
		this.b = b;
		nextValue = SquareState.CROSS;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = ((JButton)e.getSource());
		int row = (int) button.getClientProperty( "row" );
		int col = (int) button.getClientProperty( "col" );
		b.set( row, col, nextValue );
		
		nextValue = nextValue.alternate();
	}
}
