package Launcher;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import TreeGUI.TreeItemLayout;
import TreeGUI.TreeItemLayout.PanelLayout;

public class TreeItemLayoutLauncher {
	public static final int NUM_CHILDREN = 3;
	public static final int DEPTH = 2;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PanelLayout top = new PanelLayout(BoxLayout.X_AXIS);
		TreeItemLayout til = new TreeItemLayout(top, NUM_CHILDREN);
		
		
		JButton button = new JButton("Button");
		til.addComponent(button);
		
		addElements(til, DEPTH, 0);
		frame.add( top.panel );

		frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		frame.setSize( new Dimension(500, 500) );
		frame.setVisible( true );
	}
	
	public static void addElements(TreeItemLayout til, int maxDepth, int currentDepth){
		if(currentDepth == maxDepth) return;
		for(int i = 0; i < til.getChildNumber(); i++){
			TreeItemLayout tl = new TreeItemLayout(til.getTopContainerChild( i ), NUM_CHILDREN);
			tl.addComponent( new JButton("Button " + i) );
			addElements(tl, maxDepth, currentDepth + 1);
		}
	}
}
