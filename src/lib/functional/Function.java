package lib.functional;

public interface Function<T, U>{
	public U apply(T... args);
}
