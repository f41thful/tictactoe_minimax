package lib.tree;

import java.util.Iterator;

public abstract class TreeIterator<T> implements Iterator<T> {
	T obj;
	public TreeIterator(T obj){
		this.obj = obj;
	}
}
