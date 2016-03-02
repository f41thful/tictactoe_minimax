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
import javax.swing.border.Border;

public class TreeItemLayout{
	public static class PanelLayout{
		public static final int NUM_LABELS = 3;
		public JPanel panel;
		public JLabel[] labels;
		public BoxLayout layout;
		
		public PanelLayout(int orientation){
			this.panel = new JPanel();
			this.layout = new BoxLayout(panel, orientation);
			labels = new JLabel[NUM_LABELS];
			panel.setLayout( layout );
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			for(int i = 0; i < NUM_LABELS; i++){
				JLabel l = new JLabel();
				l.setAlignmentX( SwingConstants.CENTER );
				panel.add( l);
				labels[i] = l;
			}
			
			
		}
		
		public void add(PanelLayout pl){
			panel.add( pl.panel );
		}
		
		public void add(Component c){
			panel.add(c);
		}
		
		public void setLabel(int i, String text){
			if(i < labels.length)
				labels[i].setText( text );
		}
		
		public void setSelected(){
			panel.setBorder( null );
			panel.setBorder(BorderFactory.createLineBorder(Color.RED,7));
		}
	}
	
	PanelLayout topContainer;
	PanelLayout nodeContainer;
	PanelLayout childrenContainer;
	List<PanelLayout> children;
	List<TreeItemLayout> childrenItemLayout;
	public TreeItemLayout(PanelLayout topC, int numChilds){
		children = new ArrayList<PanelLayout>();
		topContainer = topC;
		if(topContainer == null) topContainer = new PanelLayout(BoxLayout.X_AXIS);
			
		nodeContainer = new PanelLayout(BoxLayout.Y_AXIS);
		childrenContainer = new PanelLayout(BoxLayout.Y_AXIS);
		childrenItemLayout = new ArrayList<TreeItemLayout>();
		
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
		nodeContainer.setLabel(0, "Value: " + text );
	}
	
	public void setDepth(String text){
		nodeContainer.setLabel(1, "Depth: " + text );
	}
	
	public void setFunc(String text){
		nodeContainer.setLabel(2, text );
	}
	
	public void select(List<Integer> list, int cIndex){
		if(list != null && cIndex < list.size()){
			int index = list.get( cIndex );
			children.get( index ).setSelected();
			childrenItemLayout.get( index ).select( list, cIndex + 1 );
		}
	}
	
	public void add(TreeItemLayout item){
		childrenItemLayout.add( item );
	}
}
