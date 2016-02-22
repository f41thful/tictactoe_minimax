package GUI;

import java.awt.GridLayout;

import javax.swing.JButton;

public class BoardGUI{
	static final int SIZE = 3;
	JButton[][] buttons;
	GridLayout layout;

	
	public BoardGUI(){
		buttons = new JButton[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				buttons[i][j] = new JButton();
			}
		}
	}
}
