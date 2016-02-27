package Launcher;

import java.util.NoSuchElementException;

import lib.Tree;
import lib.Tree.PreOrderIterator;

import Tests.GenerationFunction;

public class TestTree {
	public static void testTreePreorder(){
		Tree<Integer> t0 = new Tree<Integer>(0);
		Tree<Integer> tc0i0 = new Tree<Integer>(00);
		Tree<Integer> tc0i1 = new Tree<Integer>(01);
		Tree<Integer> tc0i2 = new Tree<Integer>(02);
		Tree<Integer> tc0i0i0 = new Tree<Integer>(000);
		Tree<Integer> tc0i0i1 = new Tree<Integer>(001);
		Tree<Integer> tc0i2i0 = new Tree<Integer>(020);
		Tree<Integer> tc0i2i1 = new Tree<Integer>(021);
		t0.addChild( tc0i0 );
		t0.addChild( tc0i1 );
		t0.addChild( tc0i2 );
		tc0i0.addChild(tc0i0i0);
		tc0i0.addChild(tc0i0i1);
		tc0i2.addChild(tc0i2i0);
		tc0i2.addChild(tc0i2i1);
		System.out.println(t0.toPreOrderString());
	}
	
	public static void testTreeGeneration(){
		Tree<Integer> tree = Tree.generateTree( 0, new GenerationFunction(), 1, 0 );
		System.out.println(tree.toPreOrderString());
	}
	public static void main(String[] args) {
		testTreeGeneration();
	}
}
