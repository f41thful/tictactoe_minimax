package lib.tree;

public class VisitIterator<T> extends TreeIterator<Tree<T>>{
	ITreeVisitor<T> visitor;
	TreeIterator<Tree<T>> it;

	public VisitIterator(Tree<T> obj, ITreeVisitor<T> visitor, TreeIterator<Tree<T>> it){
		super(obj);
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
	
	public void applyVisitor(){
		while(hasNext()) next();
	}

	@Override
	public void remove() {
		}
}
