package utils;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class FactoryGUI {
	public static JFrame getJFrame(){
		JFrame frame = new JFrame();
		JPanel topPane = new JPanel();
		topPane.setLayout( new BoxLayout(topPane, BoxLayout.Y_AXIS) );
		
		//frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		
		return frame;
	}
}
