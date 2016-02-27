package Launcher;

import java.util.Collection;
import java.util.LinkedList;

import lib.functional.F;
import lib.functional.Function;

public class FunctionalTest {

	public static void main(String[] args) {
		Collection<Integer> c = new LinkedList<Integer>();
		c.add( 1 );
		c.add( 2 );
		c.add( 3 );
		c.add( 4 );
		
		Function<Integer, Integer> f = new Function(){

			@Override
			public Object apply(Object... args) {
				Integer arg0 = (Integer) args[0];
				Integer arg1 = (Integer) args[1];
				return arg0 + arg1;
			}
			
		};
		
		Integer val = F.reduce( c, f );
		System.out.println("Value: " + val);
	}
}
