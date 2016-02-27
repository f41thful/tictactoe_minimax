package lib.tree;

import java.util.NoSuchElementException;

public class PreOrderIteratorTree<T> extends TreeIterator<Tree<T>>{
	
	public PreOrderIteratorTree(Tree<T> obj) {
		super( obj );
	}

	boolean nodeValueVisited = false;
	int cVisited = 0;
	PreOrderIteratorTree<T> currentIt;
	boolean getNewIterator = true;
	
	
	@Override
	public boolean hasNext() {
		return !nodeValueVisited || cVisited < obj.c.size();
	}

	@Override
	public Tree<T> next() {
		if(!hasNext()) throw new NoSuchElementException();
		if(!nodeValueVisited){
			nodeValueVisited = true;
			return obj;
		}else{
			if(getNewIterator){
				currentIt = obj.c.get(cVisited).getPreOrderIteratorTree();
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
