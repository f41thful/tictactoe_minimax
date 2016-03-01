package TreeGUI;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TreeItemLayout{
	public static class PanelLayout{
		public JPanel panel;
		public JLabel label1;
		public JLabel label2;
		public BoxLayout layout;
		
		public PanelLayout(int orientation){
			this.panel = new JPanel();
			this.layout = new BoxLayout(panel, orientation);
			label1 = new JLabel();
			label2 = new JLabel();
			panel.setLayout( layout );
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			label1.setAlignmentX( SwingConstants.CENTER );
			label2.setAlignmentX( SwingConstants.CENTER );
			
			panel.add( label1 );
			panel.add( label2 );
			
		}
		
		public void add(PanelLayout pl){
			panel.add( pl.panel );
		}
		
		public void add(Component c){
			panel.add(c);
		}
		
		public void setLabel1(String text){
			label1.setText( text );
		}
		
		public void setLabel2(String text){
			label2.setText( text );
		}
	}
	
	PanelLayout topContainer;
	PanelLayout nodeContainer;
	PanelLayout childrenContainer;
	List<PanelLayout> children;
	public TreeItemLayout(PanelLayout topC, int numChilds){
		children = new ArrayList<PanelLayout>();
		topContainer = topC;
		if(topContainer == null) topContainer = new PanelLayout(BoxLayout.X_AXIS);
			
		nodeContainer = new PanelLayout(BoxLayout.Y_AXIS);
		childrenContainer = new PanelLayout(BoxLayout.Y_AXIS);
		
		setChildNumber(numChilds);
		
		topContainer.add( nodeContainer );
		topContainer.add( childrenContainer );
	}
	
	public List<PanelLayout> getChildren(){
		return children;
	}
	
	public void addComponent(Component c){
		nodeContainer.add(c);
	}
	
	public PanelLayout getTopContainerChild(int index){
		if(index < children.size()) return children.get( index );
		return null;
	}
	
	public void setChildNumber(int number){
		for(int i = 0; i < number; i++){
			PanelLayout pl = new PanelLayout(BoxLayout.X_AXIS);
			children.add( pl );
			childrenContainer.add( pl );
		}
	}
	
	public int getChildNumber(){
		return children.size();
	}
	
	public void setTopContainer(PanelLayout pl){
		pl.add( nodeContainer );
		pl.add(childrenContainer);
	}
	
	public void setValue(String text){
		nodeContainer.setLabel1("Value: " + text );
	}
	
	public void setDepth(String text){
		nodeContainer.setLabel2( "Depth: " + text );
	}
}
