package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import GUI.GUI;
import Game.Board.SquareState;
import Logic.Minimax.IMinimaxStructure;

public class TicTacToeGameManager implements ActionListener{
	
	public GUI gui;
	Board b;
	
	SquareState nextValue;
	public TicTacToeGameManager(GUI gui, Board b){
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
