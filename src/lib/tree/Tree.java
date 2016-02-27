package lib.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import lib.IGenerator;

public class Tree<T> {
	
	public interface TreeVisitor<T>{
		public void visitLeaf(Tree<T> leaf);
		public void visitInternalNode(Tree<T> internalNode);
		public void visitNode(Tree<T> node);
	}
	
	public interface TreeIterator<T> extends Iterator<T>{}
	public class VisitIterator implements TreeIterator<Tree<T>>{
		TreeVisitor<T> visitor;
		TreeIterator<Tree<T>> it;

		public VisitIterator(TreeVisitor<T> visitor, TreeIterator<Tree<T>> it){
			this.visitor = visitor;
			this.it = it;
		}
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public Tree<T> next() {
			Tree<T> elem = it.next();
			visitor.visitNode( elem );
			if(elem.isLeaf()) visitor.visitLeaf( elem );
			else visitor.visitInternalNode( elem );
			return elem;
		}

		@Override
		public void remove() {
		}
		
	}
	
	public class PreOrderIteratorTree implements TreeIterator<Tree<T>>{

		boolean nodeValueVisited = false;
		int cVisited = 0;
		PreOrderIteratorTree currentIt;
		boolean getNewIterator = true;
		@Override
		public boolean hasNext() {
			return !nodeValueVisited || cVisited < c.size();
		}

		@Override
		public Tree<T> next() {
			if(!hasNext()) throw new NoSuchElementException();
			if(!nodeValueVisited){
				nodeValueVisited = true;
				return Tree.this;
			}else{
				if(getNewIterator){
					currentIt = c.get(cVisited).getPreOrderIteratorTree();
					getNewIterator = false;
				}
				Tree<T> cElem = currentIt.next();
				if(!currentIt.hasNext()){
					cVisited++;
					getNewIterator = true;
				}
				return cElem;
			}
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}

	public class PostOrderIteratorTree implements TreeIterator<Tree<T>>{
		PostOrderIteratorTree currentIt = null;
		int currentChildrenIndex = 0;
		boolean nodeValueVisited = false;
		
		@Override
		public boolean hasNext() {
			return !nodeValueVisited || currentChildrenIndex < c.size();
		}

		@Override
		public Tree<T> next() {
			if(!hasNext()) throw new NoSuchElementException();
			
			if(currentIt == null && currentChildrenIndex < c.size()) 
			{
				currentIt = c.get(currentChildrenIndex).getPostOrderIteratorTree();
			}
			else if((currentIt != null && !currentIt.hasNext())){
				currentChildrenIndex++;
				if(currentChildrenIndex < c.size()){
					currentIt = c.get(currentChildrenIndex).getPostOrderIteratorTree();
				}
			}
			
			if(currentIt == null || !currentIt.hasNext()){
				nodeValueVisited = true;
				return Tree.this;
			}else{
				return currentIt.next();
			}
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
		
	}

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
	
	
	public PreOrderIteratorTree getPreOrderIteratorTree(){
		return new PreOrderIteratorTree();
	}
	
	public PostOrderIteratorTree getPostOrderIteratorTree(){
		return new PostOrderIteratorTree();
	}
	
	
	
	public boolean isLeaf(){
		return c.size() == 0;
	}
	
	public boolean isInternalNode(){
		return !isLeaf();
	}
	
	//TODO
	/*
	public String toPostOrderString(){
		PostOrderIterator it = getPostOrderIterator();
		String s = "";
		while(it.hasNext()){
			s = s + it.next() + " ";
		}
		
		return s;
	}

	
	public String toPreOrderString(){
		PreOrderIterator it = getPreOrderIterator();
		String s = "";
		while(it.hasNext()){
			s = s + it.next() + " ";
		}
		return s;
	}
	*/
	
}
