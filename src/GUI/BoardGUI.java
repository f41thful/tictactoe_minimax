package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import GUI.GUI.Icons;
import Game.Board;
import Game.Board.Message;
import Game.Board.MessageType;
import Game.Board.SquareState;
import Game.Board.Winner;

public class BoardGUI implements Observer{
	
	
	static final int SIZE = 3;
	JButton[][] buttons;
	GridLayout layout;
	JPanel boardPanel;
	
	Icons icons;
	ActionListener al;
	
	public BoardGUI(Icons icons, ActionListener al, Board board){
		this(icons, al);
		for(int i = 0; i < board.getNumRows(); i++){
			for(int j = 0; j < board.getNumCols(); j++){
				set(i, j, board.get( i, j ));
			}
		}
	}
	
	public BoardGUI(Icons icons, ActionListener al){
		this.icons = icons;
		this.al = al;
		
		layout = new GridLayout( SIZE, SIZE );
		boardPanel = new JPanel();
		boardPanel.setLayout( layout );
		buttons = new JButton[SIZE][SIZE];

		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				buttons[i][j] = getButton();
				buttons[i][j].addActionListener( al );
				buttons[i][j].putClientProperty( "row", i);
				buttons[i][j].putClientProperty( "col", j);
				boardPanel.add( buttons[i][j] );
			}
		}
		
		boardPanel.setPreferredSize( new Dimension(500, 500));
	}
	
	public JPanel getPanel(){
		return boardPanel;
	}
	
	private JButton getButton(){
		JButton button = new JButton();
		button.setBackground( Color.WHITE );
		button.setIcon( icons.getEmpty() );
		return button;
	}
	
	private void disableBoardInput(){
		for(JButton[] ba : buttons){
			for(JButton b : ba){
				b.setEnabled( false );
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Message m = (Message) arg1;
		if(m.type == MessageType.SET)
		{
			set(m.row, m.col, (SquareState) m.value);
		}else if(m.type == MessageType.WINNER && m.value != Winner.NONE){
			disableBoardInput();
		}
	}
	
	public void setSize(int width, int height){
		boardPanel.setSize( width, height );
		boardPanel.setPreferredSize( new Dimension(width, height) );
	}
	
	private void set(int row, int col, SquareState value){
		JButton b = buttons[row][col];
			if(value == SquareState.CROSS) b.setIcon( icons.getCross() );
			else if(value == SquareState.NOTCH) b.setIcon( icons.getNotch() );
			b.setEnabled(false);
	}
}
