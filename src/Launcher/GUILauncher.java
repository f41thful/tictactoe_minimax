package Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI;
import Game.Board;
import Game.TicTacToeGameManager;

public class GUILauncher {
	
	
	
	public static void main(String[] args) {
		ActionListener restart = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Restarting");
			}
			
		};
		
		Board b = new Board();
		TicTacToeGameManager m = new TicTacToeGameManager( null, b );
		GUI gui = new GUI(new GUI.NormalIcons(), m, restart);
		m.gui = gui;
		b.addObserver( gui );
	}
}
