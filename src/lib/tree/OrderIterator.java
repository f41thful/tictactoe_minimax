package lib.tree;

import java.util.Iterator;


public class OrderIterator<T> implements Iterator<T>{
	Tree<T> obj;
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
		return it.next().getElem();
	}

	@Override
	public void remove() {
		it.remove();
	}

}
