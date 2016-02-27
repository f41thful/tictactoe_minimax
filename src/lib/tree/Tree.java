package lib.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import lib.IGenerator;

public class Tree<T> {

	Tree<T> p;
	T e;
	ArrayList<Tree<T>> c;
	
	// generate a tree with nodes of depth depth included.
	// a call will breadth == 0 will generate a tree with one node.
	public static <E> Tree<E> generateTree(E elem, IGenerator<E> g, int depth, int breadth){
		return generateTreeImp( elem, g, 0, depth, breadth );
	}
	
	private static <E> Tree<E> generateTreeImp
	(E elem, IGenerator<E> g, int curDepth, int maxDepth, int maxBreadth){
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
	
	
	public PreOrderIteratorTree<T> getPreOrderIteratorTree(){
		return new PreOrderIteratorTree<T>(this);
	}
	
	public PostOrderIteratorTree<T> getPostOrderIteratorTree(){
		return new PostOrderIteratorTree<T>(this);
	}
	
	
	
	public boolean isLeaf(){
		return c.size() == 0;
	}
	
	public boolean isInternalNode(){
		return !isLeaf();
	}
	
	public T getElem(){
		return e;
	}
	
	
	public String toPostOrderString(){
		PostOrderIteratorTree<T> treeIt = getPostOrderIteratorTree();
		OrderIterator<T> it = new OrderIterator<T>(this, treeIt);
		String s = "";
		while(it.hasNext()){
			s = s + it.next() + " ";
		}
		
		return s;
	}

	
	public String toPreOrderString(){
		PreOrderIteratorTree<T> treeIt = getPreOrderIteratorTree();
		OrderIterator<T> it = new OrderIterator<T>(this, treeIt);
		String s = "";
		while(it.hasNext()){
			s = s + it.next() + " ";
		}
		return s;
	}
	
	public int getDepth(){
		int depth = 0;
		Tree<T> cur = p;
		while(cur != null){
			cur = cur.p;
			depth++;
		}
		return depth;
	}
	
}