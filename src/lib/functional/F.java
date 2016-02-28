package lib.functional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class F {
	public static <T, U> Collection<U> map(Collection<T> c, Func1<T, U> f){
		Collection<U> out = new LinkedList<U>();
		if(c == null) return out;
		for(T val : c){
			out.add( f.apply( val ) );
		}
		return out;
	}
	
	public static <T> T reduce(Collection<T> c, Func2<T> f){
		if(c == null || c.size() <= 1) return null;
		List<T> list = new ArrayList<T>();
		for(T t : c){
			list.add(t);
		}
		
		T elem = list.get(0);
		for(int i = 1; i < c.size(); i++){
			if(list == null) System.out.println("List is null");
			if(list.get( i ) == null) System.out.println("Value is null");
			System.out.flush();
			elem = f.apply( elem, list.get(i));
		}
		return elem;
	}
}
