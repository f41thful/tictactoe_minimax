package Logic;

import lib.IGenerator;

public class Minimax {
	public interface IMinimaxStructure<T> extends IGenerator<T>{
		public int evaluate(T value);
	}
	
	
}
