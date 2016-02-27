package Logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Tree<T> {
	public interface Generator<T>{
		public List<T> generate(T elem);
	}
	
	public class PreOrderIterator implements Iterator<T>{

		boolean elemVisited = false;
		int cVisited = 0;
		PreOrderIterator currentIt;
		boolean getNewIterator = true;
		@Override
		public boolean hasNext() {
			return !elemVisited || cVisited < c.size();
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			if(!elemVisited){
				elemVisited = true;
				return e;
			}else{
				if(getNewIterator){
					currentIt = c.get(cVisited).getPreOrderIterator();
					getNewIterator = false;
				}
				T cElem = currentIt.next();
				if(!currentIt.hasNext()){
					cVisited++;
					getNewIterator = true;
				}
				return cElem;
			}
		}
		

		@Override
		public void remove() {}
		
	}

	Tree<T> p;
	T e;
	ArrayList<Tree<T>> c;
	
	// generate a tree with nodes of depth depth included.
	// a call will breadth == 0 will generate a tree with one node.
	public static <E> Tree<E> generateTree(E elem, Generator<E> g, int depth, int breadth){
		return generateTreeImp( elem, g, 0, depth, breadth );
	}
	
	private static <E> Tree<E> generateTreeImp
	(E elem, Generator<E> g, int curDepth, int maxDepth, int maxBreadth){
		if(curDepth > maxDepth) return null;
		Tree<E> tree = new Tree<E>(elem);
		List<E> children = g.generate( elem );
	
		for(int i = 0; i < maxBreadth && i < children.size(); i++){
			E child = children.get( i );
			Tree<E> childTree = generateTreeImp(child, g, curDepth + 1, maxDepth, maxBreadth);
			if(childTree != null)
				tree.addChild(childTree);
		}
		return tree;
	}
	
	public Tree(T elem){
		e = elem;
		c = new ArrayList<Tree<T>>();
	}
	
	public void addChild(T elem){
		Tree<T> e = new Tree<T>(elem);
		addChild(e);
	}
	
	public void addChild(Tree<T> elem){
		elem.setP( this );
		c.add( elem );
	}

	public void setP(Tree<T> p){
		this.p = p;
	}
	
	public Iterator<Tree<T>> getChildrenIterator(){
		return c.iterator();
	}
	
	public PreOrderIterator getPreOrderIterator(){
		return new PreOrderIterator();
	}
	
	public String toPreOrderString(){
		PreOrderIterator it = getPreOrderIterator();
		String s = "";
		while(it.hasNext()){
			s = s + it.next() + " ";
		}
		return s;
	}
}
