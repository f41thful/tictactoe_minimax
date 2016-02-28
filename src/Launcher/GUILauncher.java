package Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import GUI.BoardGUI.Icons;
import GUI.GUI;
import Game.Board;
import Game.TicTacToeGameManager;
import Game.Board.SquareState;

public class GUILauncher {
	
	static class NormalIcons extends Icons{
		public NormalIcons(){}
		
		@Override
		public ImageIcon getNotch() {
			return new ImageIcon("icons/notch.png");
		}

		@Override
		public ImageIcon getCross() {
			return new ImageIcon("icons/cross.png");
		}
		
	}
	
	public static void main(String[] args) {
		ActionListener restart = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Restarting");
			}
			
		};
		
		Board b = new Board();
		TicTacToeGameManager m = new TicTacToeGameManager( null, b );
		GUI gui = new GUI(new NormalIcons(), m, restart);
		m.gui = gui;
		b.addObserver( gui );
	}
}
