package Launcher;

import java.util.NoSuchElementException;

import Logic.Tree;
import Logic.Tree.PreOrderIterator;

public class TestTree {
	public static void main(String[] args) {
		Tree<Integer> t0 = new Tree<Integer>(0);
		Tree<Integer> tc0i0 = new Tree<Integer>(10);
		Tree<Integer> tc0i1 = new Tree<Integer>(11);
		Tree<Integer> tc0i2 = new Tree<Integer>(12);
		Tree<Integer> tc0i0i0 = new Tree<Integer>(20);
		Tree<Integer> tc0i0i1 = new Tree<Integer>(20);
		Tree<Integer> tc0i2i0 = new Tree<Integer>(20);
		Tree<Integer> tc0i2i1 = new Tree<Integer>(20);
		t0.addChild( tc0i0 );
		t0.addChild( tc0i1 );
		t0.addChild( tc0i2 );
		tc0i0.addChild(tc0i0i0);
		tc0i0.addChild(tc0i0i1);
		tc0i2.addChild(tc0i2i0);
		tc0i2.addChild(tc0i2i1);
		PreOrderIterator i = t0.getPreOrderIterator();
		while(i.hasNext()){
			System.out.println(i.next() + " ");
		}
		
	}
}
