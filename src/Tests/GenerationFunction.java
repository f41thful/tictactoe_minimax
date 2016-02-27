package Tests;

import java.util.ArrayList;
import java.util.List;

import lib.IGenerator;


public class GenerationFunction implements IGenerator<Integer>{

	@Override
	public List<Integer> generate(Integer elem) {
		List<Integer> l = new ArrayList<Integer>();
		if(elem > 2) return l;
		l.add( elem + 1 );
		l.add( elem + 2 );
		return l;
	}
	
}
