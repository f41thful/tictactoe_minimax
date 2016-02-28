package Launcher;

import java.util.Collection;
import java.util.LinkedList;

import lib.functional.F;
import lib.functional.Func2;

public class FunctionalTest {

	public static void main(String[] args) {
		Collection<Integer> c = new LinkedList<Integer>();
		c.add( 1 );
		c.add( 2 );
		c.add( 3 );
		c.add( 4 );
		
		Func2<Integer> f = new Func2<Integer>(){

			@Override
			public Integer apply(Integer a0, Integer a1) {
				return a0 + a1;
			}
		};
		
		Integer val = F.reduce( c, f );
		System.out.println("Value: " + val);
	}
}
