package Launcher;

import javax.swing.JFrame;

import utils.GUIFactory;
import GUI.BoardGUI;
import GUI.GUI;
import Game.Board;
import Game.Board.SquareState;

public class BoardGUITreeLauncher {
	
	
	public static void main(String[] args) {
		singleBoard();
	}
	
	public static void singleBoard(){
		Board b = new Board();
		b.set( 0, 0, SquareState.CROSS );
		b.set( 0, 1, SquareState.CROSS );
		b.set( 0, 2, SquareState.NOTCH );
		JFrame frame = GUIFactory.getJFrame();
		BoardGUI boardgui = new BoardGUI(new GUI.NormalIcons(), GUI.defaultAl, b);
		frame.add( boardgui.getPanel() );
		frame.pack();
	}
}
