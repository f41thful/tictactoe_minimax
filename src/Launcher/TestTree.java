package Launcher;

import java.util.NoSuchElementException;

import lib.tree.ITreeVisitor;
import lib.tree.Tree;

import Tests.GenerationFunction;

public class TestTree {
	
	
	public static Tree<Integer> getTestTree(){
		Tree<Integer> t0 = new Tree<Integer>(0);
		Tree<Integer> tc0i0 = new Tree<Integer>(1);
		Tree<Integer> tc0i0i0 = new Tree<Integer>(2);
		Tree<Integer> tc0i0i1 = new Tree<Integer>(3);
		Tree<Integer> tc0i1 = new Tree<Integer>(4);
		Tree<Integer> tc0i2 = new Tree<Integer>(5);
		Tree<Integer> tc0i2i0 = new Tree<Integer>(6);
		Tree<Integer> tc0i2i1 = new Tree<Integer>(7);
		t0.addChild( tc0i0 );
		t0.addChild( tc0i1 );
		t0.addChild( tc0i2 );
		tc0i0.addChild(tc0i0i0);
		tc0i0.addChild(tc0i0i1);
		tc0i2.addChild(tc0i2i0);
		tc0i2.addChild(tc0i2i1);
		return t0;
	}
	
	public static void testTreeVisitor(){
		Tree<Integer> tree = getTestTree();
		ITreeVisitor<Integer> v = new ITreeVisitor<Integer>() {

			@Override
			public void visitLeaf(Tree<Integer> leaf) {
				System.out.println("Leaf: depth " + leaf.getDepth() + ", val " + leaf.getElem());
			}

			@Override
			public void visitInternalNode(Tree<Integer> internalNode) {
				System.out.println("Internal node: depth " 
			+ internalNode.getDepth() + ", val" + internalNode.getElem());
			}

			@Override
			public void visitNode(Tree<Integer> node) {
				// TODO Auto-generated method stub
				
			}
		};
		
		tree.getVisitPostOrderIteratorTree( v ).applyVisitor();
	}
	
	public static void testTreePostOrder(){
		Tree<Integer> t0 = getTestTree();
		System.out.println(t0.toPostOrderString());
	}
	
	public static void testTreePreOrder(){
		Tree<Integer> t0 = getTestTree();
		System.out.println(t0.toPreOrderString());
	}
	
	public static void testTreeGeneration(){
		Tree<Integer> tree = Tree.generateTree( 0, new GenerationFunction(), 1, 0 );
		System.out.println(tree.toPreOrderString());
	}
	public static void main(String[] args) {
		testTreeVisitor();
		//testTreePostOrder();
		//testTreePreOrder();
		//testTreeGeneration();
	}
}
