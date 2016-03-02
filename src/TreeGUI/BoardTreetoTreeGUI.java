package TreeGUI;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.BoardGUI;
import GUI.GUI.Icons;
import Game.Board;
import TreeGUI.TreeItemLayout.PanelLayout;
import lib.tree.ITreeVisitor;
import lib.tree.Tree;
import GUI.GUI;
import Logic.Minimax;

/*
 * Creates Tree when applied with a preorder walk of a tree.
 */
public class BoardTreetoTreeGUI implements ITreeVisitor<Game.Board>{
	
	
	
	public static final String KEY = "BoardTreetoTreeGUI_Panel";
	public static final String KEY_TIL = "BoardTreetoTreeGUI_Til";
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	public static final Icons SMALL_ICONS = new GUI.SmallIcons();
	
	JScrollPane scrollTop;
	PanelLayout top;
	TreeItemLayout topTreeItemLayout;
	
	
	public BoardTreetoTreeGUI(){
		top = new PanelLayout( BoxLayout.X_AXIS );
		scrollTop = new JScrollPane( top.panel );
	}
	
	@Override
	public void visitLeaf(Tree<Board> leaf) {
	}

	@Override
	public void visitInternalNode(Tree<Board> internalNode) {
	}

	@Override
	public void visitNode(Tree<Board> node) {
		PanelLayout cTop;
		if(node.isRoot()){
			node.putdata( KEY, top );
		}
		
		cTop = (PanelLayout) node.getData( KEY );
		if(cTop == null){
			System.out.println("ERROR in BoardTreetoTreeGUI, the top PanelLayout is null");
		}
		TreeItemLayout til = new TreeItemLayout( cTop, node.getChildren().size() );
		node.putdata( KEY_TIL, til );
		if(!node.isRoot()){
			((TreeItemLayout)node.getParent().getData( KEY_TIL )).add( til );
		}else{
			topTreeItemLayout = til;
		}

		String value = String.valueOf(node.getData( Minimax.KEY ));
		if(value != null)
			til.setValue( value );
		
		int depth = node.getDepth();
		til.setDepth( String.valueOf(depth) );
		til.setFunc( Minimax.getFuncName( depth ) );

		BoardGUI board = new BoardGUI(SMALL_ICONS, GUI.defaultAl, node.getElem());
		board.setSize(WIDTH, HEIGHT);
		til.addComponent(board.getPanel());
		
		List<Tree<Board>> children = node.getChildren();
		for(int i = 0; i < children.size(); i++){
			if(i < til.getChildren().size()){
				children.get(i).putdata( KEY, til.getChildren().get( i ));
			}else{
				System.out.println("ERROR in BoardTreetoTreeGUI, children in the tree and" +
						"int the GUI don't match");
			}
		}
	}
	
	public PanelLayout getTopPanelLayout(){
		return top;
	}
	
	public JScrollPane getPanel(){
		return scrollTop;
	}
	
	public TreeItemLayout getTopTreeItemLayout(){
		return topTreeItemLayout;
	}

}
