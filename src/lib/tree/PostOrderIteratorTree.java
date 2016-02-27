package lib.tree;

import java.util.NoSuchElementException;

public class PostOrderIteratorTree<T> extends TreeIterator<Tree<T>>{
	

	PostOrderIteratorTree<T> currentIt = null;
	int currentChildrenIndex = 0;
	boolean nodeValueVisited = false;
	
	public PostOrderIteratorTree(Tree<T> obj) {
		super( obj );
	}
	
	@Override
	public boolean hasNext() {
		return !nodeValueVisited || currentChildrenIndex < obj.c.size();
	}

	@Override
	public Tree<T> next() {
		if(!hasNext()) throw new NoSuchElementException();
		
		if(currentIt == null && currentChildrenIndex < obj.c.size()) 
		{
			currentIt = obj.c.get(currentChildrenIndex).getPostOrderIteratorTree();
		}
		else if((currentIt != null && !currentIt.hasNext())){
			currentChildrenIndex++;
			if(currentChildrenIndex < obj.c.size()){
				currentIt = obj.c.get(currentChildrenIndex).getPostOrderIteratorTree();
			}
		}
		
		if(currentIt == null || !currentIt.hasNext()){
			nodeValueVisited = true;
			return obj;
		}else{
			return currentIt.next();
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
		
}
