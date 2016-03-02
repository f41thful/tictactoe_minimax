package Launcher;


import GUI.GUI;
import Game.Board;
import Game.TicTacToeGameManager;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		Board b = new Board();
		TicTacToeGameManager m = new TicTacToeGameManager( null, b );
		GUI gui = new GUI(new GUI.NormalIcons(), m);
		m.gui = gui;
		b.addObserver( gui );
	}
}
