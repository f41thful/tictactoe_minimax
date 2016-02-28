package Logic;

import java.util.Collection;

import lib.IGenerator;
import lib.functional.F;
import lib.functional.Func1;
import lib.functional.Func2;
import lib.tree.ITreeVisitor;
import lib.tree.Tree;
import lib.tree.VisitIterator;

public class Minimax<T> {

	public static final String KEY = "minimaxvalue";
	public interface IMinimaxStructure<T> extends IGenerator<T>{
		public int evaluate(T value);
	}
	
	class Evaluation implements ITreeVisitor<T>{
		Func2 min = new Func2<Integer>(){

			@Override
			public Integer apply(Integer a0, Integer a1) {
				
				return Math.min( a0, a1);
			}

		};
		
		Func2 max = new Func2<Integer>(){

			@Override
			public Integer apply(Integer a0, Integer a1) {
				return Math.max( a0, a1 );
			}
			
		};
		
		Func1 getKey = new Func1<Tree<T>, Integer>(){

			@Override
			public Integer apply(Tree<T> a) {
				return (Integer) a.getData( KEY );
			}
			
		};
		

		Func2<Integer>[] funcs;
		
		public Evaluation() {
			 funcs = new Func2[2];
			 funcs[0] = max;
			 funcs[1] = min;
		}
		
		@Override
		public void visitLeaf(Tree<T> leaf) {
			leaf.putdata( KEY, st.evaluate( leaf.getElem() ));
		}

		@Override
		public void visitInternalNode(Tree<T> internalNode) {
			int depth = internalNode.getDepth();
			Collection<Tree<T>> children = internalNode.getChildren();
			int value = F.reduce( F.map( children, getKey), funcs[(depth % 2)]);
			internalNode.putdata( KEY, value );
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
	
	/*
	 * Take the first element that has the value that has been selected.
	 */
	public T getSol(Tree<T> tree){
		Object o = tree.getData( KEY );
		if(o == null) return null;
		
		Integer value = (Integer) o;
		for(Tree<T> t : tree.getChildren()){
			o = t.getData( KEY );

			if(o == null) return null;
			Integer valueC = (Integer) o;
			
			if(value == valueC) return t.getElem();
		}
		return null;
	}
}
