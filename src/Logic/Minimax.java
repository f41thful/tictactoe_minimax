package Logic;

import lib.IGenerator;
import lib.tree.ITreeVisitor;
import lib.tree.Tree;

public class Minimax<T> {
	public interface IMinimaxStructure<T> extends IGenerator<T>{
		public int evaluate(T value);
	}
	
	class Evaluation implements ITreeVisitor<T>{
		String key = "key";
		@Override
		public void visitLeaf(Tree<T> leaf) {
			leaf.putdata( key, st.evaluate( leaf.getElem() ));
		}

		@Override
		public void visitInternalNode(Tree<T> internalNode) {
			int depth = internalNode.getDepth();
			
		}

		@Override
		public void visitNode(Tree<T> node) {
			// TODO Auto-generated method stub
		}
		
	}
	
	IMinimaxStructure<T> st;
	Tree<T> tree;
	int depth, breadth;
	
	public Minimax(IMinimaxStructure<T> st){
		this.st = st;
		depth = Integer.MAX_VALUE;
		breadth = Integer.MAX_VALUE;
	}
	
	public Minimax(IMinimaxStructure<T> st, int depth, int breadth){
		this(st);
		this.depth = depth;
		this.breadth = breadth;
	}
	
	public Tree<T> generate(T value){
		tree = Tree.generateTree( value, st, depth, breadth );
		return tree;
	}
	
	public void setDepth(int depth){
		this.depth = depth;
	}
	
	public void setBreadth(int breadth){
		this.breadth = breadth;
	}
}
