package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;


import lib.tree.ITreeVisitor;
import lib.tree.Tree;

import utils.FactoryGUI;
import utils.TreeMinimax;
import utils.Utils;
import utils.TreeMinimax.Return;

import GUI.GUI;
import Game.Board.SquareState;
import Game.Board.Winner;
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
		if(b.getWinner() == Winner.NONE)
			calculateAIMove(b);
		//nextValue = nextValue.alternate();
	}
	
	public void calculateAIMove(Board b){
		Tree<Board> tree = FacadeAI.generateNaive( b, DEPTH, BRANCH );
		/*
		BoardTreetoTreeGUI v = new BoardTreetoTreeGUI();
		tree.applyVisitors( new ITreeVisitor[]{v} );
		*/
		
		List<Return<Board>> possibleSol = TreeMinimax.getPossibleSol( tree );
		Utils.printList(possibleSol);
		
		if(possibleSol != null && possibleSol.size() > 0){
			Return<Board> sol = possibleSol.get( 0 );
			Board newBoard = sol.elem;
			
			// the two boards should be different in only one square.
			b.syncBoard( newBoard );
			
			BoardTreetoTreeGUI v = FactoryGUI.getGUIInfo( tree );
			v.getTopTreeItemLayout().select( TreeMinimax.getIndices(possibleSol), 0 );
			gui.addSideBoard(v.getPanel());
		}else{
			System.out.println("In TicTacToeGameManager, possibleSol should not be 0 or null");
		}
	}
}
