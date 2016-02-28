package lib.tree.visitors;

import java.util.List;

import lib.tree.ITreeVisitor;
import lib.tree.Tree;

public class CalculateBranchId<T> implements ITreeVisitor<T>{
	public static final String KEY_BRANCH = "calculateBranchIdKey";
	public static final String KEY_COMPOSE_BRANCH = "calculateBranchIdComposeKey";

	@Override
	public void visitLeaf(Tree<T> leaf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitInternalNode(Tree<T> internalNode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitNode(Tree<T> node) {
		if(node.getParent() == null){
			node.putdata( KEY_BRANCH, "0 " );
			node.putdata( KEY_COMPOSE_BRANCH, "0 " );
		}else{
			int branchId = getBranchId(node.getParent(), node);
			node.putdata( KEY_BRANCH, branchId + " ");
			String composeId = (String) (node.getParent().getData( KEY_COMPOSE_BRANCH ) );
			node.putdata( KEY_COMPOSE_BRANCH, composeId + branchId + " ");
		}
	}
	
	/**
	 * return -1 if error.
	 * @param parent
	 * @param child
	 * @return
	 */
	public int getBranchId(Tree<T> parent, Tree<T> child){
		List<Tree<T>> children = parent.getChildren();
		if(children == null) return -1;
		
		for(int i = 0; i < children.size(); i++){
			if(children.get( i ) == child) return i;
		}
		return -1;
	}

}
