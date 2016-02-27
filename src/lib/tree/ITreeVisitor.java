package lib.tree;

public interface ITreeVisitor<T> {
	public void visitLeaf(Tree<T> leaf);
	public void visitInternalNode(Tree<T> internalNode);
	public void visitNode(Tree<T> node);
}
