package Launcher;

import java.util.NoSuchElementException;

import lib.Tree;
import lib.Tree.PreOrderIterator;

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
		testTreePostOrder();
		//testTreePreorder();
		//testTreeGeneration();
	}
}
