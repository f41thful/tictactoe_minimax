package utils;

import java.util.ArrayList;
import java.util.List;

import Logic.Minimax;
import lib.tree.Tree;

public class TreeMinimax {
	public static class Return<T>{
		public T elem;
		public int index; // children index
		public Tree<T> treeElem;
		public boolean valid = false;
		
		@Override
		public String toString(){
			return String.valueOf(index);
		}
		
	}
	
	/*
	 * Take the first element that has the value that has been selected.
	 */
	public static <T> Return<T> getSol(Tree<T> tree){
		Return<T> r = new Return<T>();
		Object o = tree.getData( Minimax.KEY );
		if(o == null) return null;
		if(tree.getChildren() == null || tree.getChildren().size() == 0){ 
			return r;
		}
		
		Integer value = (Integer) o;
		List<Tree<T>> children = tree.getChildren();
		for(int i = 0; i < children.size(); i++){
			Tree<T> t = children.get( i );
			o = t.getData( Minimax.KEY );

			if(o == null) return null;
			Integer valueC = (Integer) o;
			
			if(value == valueC){
				r.treeElem = t;
				r.elem = t.getElem();
				r.index = i;
				r.valid = true;
				return r;
			}
		}
		return null;
	}
	
	public static <T> List<Return<T>> getPossibleSol(Tree<T> tree){
		List<Return<T>> list = new ArrayList<Return<T>>();
		Return<T> r;
		Tree<T> cTree = tree;
		do{
			r = TreeMinimax.getSol( cTree );
			if(r.valid){
				cTree = r.treeElem;
				list.add(r);
			}
		}while(r.valid);
		
		return list;
	}
	
	public static <T> List<Integer> getIndices(List<Return<T>> list)
	{
		List<Integer> out = new ArrayList<Integer>();
		for(Return<T> r : list){
			out.add( r.index );
		}
		return out;
	}
}
