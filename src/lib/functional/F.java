package lib.functional;

import java.util.Collection;
import java.util.LinkedList;

public class F {
	public static <T, U> Collection<U> map(Collection<T> c, Function<T, U> f){
		Collection<U> res = new LinkedList<U>();
		if(c == null) return res;
		for(T val : c){
			res.add( f.apply( val ) );
		}
		return res;
	}
	
	public static <T> T reduce(Collection<T> c, Function<T, T> f){
		if(c == null || c.size() <= 1) return null;
		T[] array = (T[]) c.toArray();
		
		T elem = array[0];
		for(int i = 1; i < c.size(); i++){
			elem = f.apply( elem, array[i] );
		}
		return elem;
	}
}
