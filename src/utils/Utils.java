package utils;

import java.util.List;

public class Utils {
	public static <T> void printList(List<T> list){
		String s = "[";
		for(T t : list){
			s = s + " " + t;
		}
		s = s + "]";
		System.out.println(s);
	}
}
