package lib.tree;

import java.util.Iterator;


public class OrderIterator<T> implements Iterator<T>{
	Tree<T> obj;
	Tree<T> last;
	TreeIterator<Tree<T>> it;

	public OrderIterator(Tree<T> obj, TreeIterator<Tree<T>> it) {
		this.obj = obj;
		this.it = it;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public T next() {
		last = it.next();
		return last.getElem();
	}

	@Override
	public void remove() {
		it.remove();
	}

	public Tree<T> getLastTree(){
		return last;
	}
}
