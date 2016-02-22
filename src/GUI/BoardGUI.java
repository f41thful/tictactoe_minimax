package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BoardGUI{
	static final int SIZE = 3;
	JButton[][] buttons;
	GridLayout layout;
	JPanel panel;
	JFrame frame;
	
	public BoardGUI(){
		layout = new GridLayout( SIZE, SIZE );
		frame = new JFrame();
		panel = new JPanel();
		
		panel.setLayout( layout );
		frame.add(panel);
		
		buttons = new JButton[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				buttons[i][j] = new JButton();
				panel.add( buttons[i][j] );
			}
		}
		
		panel.setPreferredSize( new Dimension(500, 500));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
	}
}
