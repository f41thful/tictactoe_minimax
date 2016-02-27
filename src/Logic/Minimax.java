package Logic;

import java.util.Collection;

import lib.IGenerator;
import lib.functional.F;
import lib.functional.Function;
import lib.tree.ITreeVisitor;
import lib.tree.Tree;
import lib.tree.VisitIterator;

public class Minimax<T> {
	public interface IMinimaxStructure<T> extends IGenerator<T>{
		public int evaluate(T value);
	}
	
	class Evaluation implements ITreeVisitor<T>{
		Function min = new Function<Integer, Integer>(){

			@Override
			public Integer apply(Integer... args) {
				return Math.min( args[0], args[1] );
			}

		};
		
		Function max = new Function<Integer, Integer>(){

			@Override
			public Integer apply(Integer... args) {
				return Math.max( args[0], args[1] );
			}
			
		};
		
		Function getKey = new Function<Tree<T>, Integer>(){

			@Override
			public Integer apply(Tree<T>... args) {
				return (Integer) args[0].getData( key );
			}
			
		};
		
		String key = "key";

		Function[] funcs;
		
		public Evaluation() {
			 funcs = new Function[2];
		}
		
		@Override
		public void visitLeaf(Tree<T> leaf) {
			leaf.putdata( key, st.evaluate( leaf.getElem() ));
		}

		@Override
		public void visitInternalNode(Tree<T> internalNode) {
			int depth = internalNode.getDepth();
			Collection<Tree<T>> children = internalNode.getChildren();
			int value = F.reduce( F.map( children, getKey), funcs[(depth % 2)]);
			internalNode.putdata( key, value );
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
		Evaluation eval = new Evaluation();
		VisitIterator<T> it = tree.getVisitPostOrderIteratorTree( eval );
		it.applyVisitor();
		return tree;
	}
	
	public void setDepth(int depth){
		this.depth = depth;
	}
	
	public void setBreadth(int breadth){
		this.breadth = breadth;
	}
}
