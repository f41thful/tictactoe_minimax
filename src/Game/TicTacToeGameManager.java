package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import lib.tree.ITreeVisitor;
import lib.tree.Tree;

import utils.FactoryGUI;

import GUI.GUI;
import Game.Board.SquareState;
import Logic.Minimax;
import Logic.Minimax.IMinimaxStructure;
import TreeGUI.BoardTreetoTreeGUI;

public class TicTacToeGameManager implements ActionListener{
	// cannot be 0
	public static final int DEPTH = 2;
	public static final int BRANCH = 9;
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
		
		//nextValue = nextValue.alternate();
		
		calculateAIMove(b);
		//nextValue = nextValue.alternate();
	}
	
	public void calculateAIMove(Board b){
		Tree<Board> tree = FacadeAI.generateNaive( b, DEPTH, BRANCH );
		BoardTreetoTreeGUI v = new BoardTreetoTreeGUI();
		tree.applyVisitors( new ITreeVisitor[]{v} );
		
		Board newBoard = Minimax.getSol( tree );
		
		// the two boards should be different in only one square.
		b.syncBoard( newBoard );
		
		gui.addSideBoard(FactoryGUI.getPanel(tree));
	}
}
