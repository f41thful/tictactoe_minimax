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
	
	public static <E> Tree<E> generateTree(E elem, Generator<E> g){
		Tree<E> tree = new Tree<E>(elem);
		List<E> children = g.generate( elem );
		for(E child : children){
			tree.addChild(generateTree(child, g));
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
