package Launcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import lib.functional.F;
import lib.functional.Func2;
import lib.tree.ITreeVisitor;
import lib.tree.Tree;
import lib.tree.Tree.GetString;
import lib.tree.VisitIterator;

import Logic.Minimax;
import Logic.Minimax.IMinimaxStructure;
import TreeGetters.GetValue;

public class MinimaxTest {
	
	static IMinimaxStructure<Integer[]> st = new IMinimaxStructure<Integer[]>(){

		private Integer[] generateAlt(Integer[] in, int off){
			Integer[] out = new Integer[in.length];
			for(int i = 0; i < out.length; i++) out[i] = 0;
			for(int i = 0; i < in.length; i++){
				if(in[i] != 0 && i + off >= 0 && i + off < out.length){
					out[i+off] = in[i] + off;
				}
			}
			return out;
		}
		@Override
		public List<Integer[]> generate(Integer[] elem) {
			List<Integer[]> list = new ArrayList<Integer[]>();
			list.add(generateAlt( elem, 1 ));
			list.add(generateAlt( elem, 0 ));
			return list;
		}

		@Override
		public int evaluate(Integer[] value) {
			Collection<Integer> c = new ArrayList<Integer>();
			for(Integer i : value){
				c.add(i);
			}
			return F.reduce( c, new Func2<Integer>(){

				@Override
				public Integer apply(Integer a0, Integer a1) {
					return a0 + a1;
				}
				
			});
		}
		
	};
	
	
	
	public static void main(String[] args) {
		Integer[] array = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		Minimax<Integer[]> minimax = new Minimax<>( st, 2, 2 );
		Tree<Integer[]> tree = minimax.generate( array );
		System.out.println(tree.toPostOrderStringWithBranchId(new Tree.GetString[]{
				new GetValue()
		}));
	}
}
