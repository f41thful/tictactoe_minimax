package utils;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import lib.tree.ITreeVisitor;
import lib.tree.Tree;

import Game.Board;
import Game.FacadeAI;
import TreeGUI.BoardTreetoTreeGUI;

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
	
	public static BoardTreetoTreeGUI getPanel(Board b, int depth, int height){
		Tree<Board> tree = FacadeAI.generateNaive( b, depth, height );
		BoardTreetoTreeGUI v = new BoardTreetoTreeGUI();
		tree.applyVisitors( new ITreeVisitor[]{v} );
		return v;
	}
	
	public static JComponent getPanel(Tree<Board> tree){
		BoardTreetoTreeGUI v = new BoardTreetoTreeGUI();
		tree.applyVisitors( new ITreeVisitor[]{v} );
		return v.getPanel();
	}
}
