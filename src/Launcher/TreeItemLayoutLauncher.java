package Launcher;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import TreeGUI.TreeItemLayout;
import TreeGUI.TreeItemLayout.PanelLayout;

public class TreeItemLayoutLauncher {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PanelLayout top = new PanelLayout(BoxLayout.Y_AXIS);
		TreeItemLayout til = new TreeItemLayout(top, 2);
		
		
		JButton button = new JButton("Button");
		til.addComponent(button);
		
		addElements(til, 3, 0);
		frame.add( top.panel );

		frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		frame.setSize( new Dimension(500, 500) );
		frame.setVisible( true );
	}
	
	public static void addElements(TreeItemLayout til, int maxDepth, int currentDepth){
		if(currentDepth == maxDepth) return;
		for(int i = 0; i < til.getChildNumber(); i++){
			TreeItemLayout tl = new TreeItemLayout(til.getTopContainerChild( i ), 4);
			tl.addComponent( new JButton("Button " + i) );
			addElements(tl, maxDepth, currentDepth + 1);
		}
	}
}
