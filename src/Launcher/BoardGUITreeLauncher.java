package Launcher;

import javax.swing.JFrame;

import lib.tree.ITreeVisitor;
import lib.tree.Tree;

import utils.FactoryGUI;
import GUI.BoardGUI;
import GUI.GUI;
import Game.Board;
import Game.Board.SquareState;
import Game.FacadeAI;
import TreeGUI.BoardTreetoTreeGUI;

public class BoardGUITreeLauncher {
	
	
	public static void main(String[] args) {
		//singleBoard();
		wholeTree();
	}
	
	public static void wholeTree(){
		Board b = new Board();
		Tree<Board> tree = FacadeAI.generateNaive( b, 6, 2 );
		BoardTreetoTreeGUI v = new BoardTreetoTreeGUI();
		tree.applyVisitors( new ITreeVisitor[]{v} );
	
		JFrame frame = FactoryGUI.getJFrame();
		frame.add( v.getPanel() );
		frame.pack();
	}
	
	public static void singleBoard(){
		Board b = new Board();
		b.set( 0, 0, SquareState.CROSS );
		b.set( 0, 1, SquareState.CROSS );
		b.set( 0, 2, SquareState.NOTCH );
		JFrame frame = FactoryGUI.getJFrame();
		BoardGUI boardgui = new BoardGUI(GUI.defaultIcons, GUI.defaultAl, b);
		frame.add( boardgui.getPanel() );
		frame.pack();
	}
}
