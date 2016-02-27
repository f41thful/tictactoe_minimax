package lib;

import java.util.List;

public interface IGenerator<T> {
	public List<T> generate(T elem);
}
