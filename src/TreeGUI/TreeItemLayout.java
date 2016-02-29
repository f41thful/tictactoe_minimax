package TreeGUI;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TreeItemLayout{
	public static class PanelLayout{
		public JPanel panel;
		public BoxLayout layout;
		
		public PanelLayout(int orientation){
			this.panel = new JPanel();
			this.layout = new BoxLayout(panel, orientation);
			panel.setLayout( layout );
		}
		
		public void add(PanelLayout pl){
			panel.add( pl.panel );
		}
		
		public void add(Component c){
			panel.add(c);
		}
	}
	
	PanelLayout topContainer;
	PanelLayout nodeContainer;
	PanelLayout childrenContainer;
	List<PanelLayout> children;
	public TreeItemLayout(PanelLayout topC, int numChilds){
		children = new ArrayList<PanelLayout>();
		topContainer = topC;
		if(topContainer == null) topContainer = new PanelLayout(BoxLayout.Y_AXIS);
			
		nodeContainer = new PanelLayout(BoxLayout.X_AXIS);
		childrenContainer = new PanelLayout(BoxLayout.X_AXIS);
		
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
			PanelLayout pl = new PanelLayout(BoxLayout.Y_AXIS);
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
}
