package lib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class R {
	Class clazz;
	
	public R(Class clazz){
		this.clazz = clazz;
	}

	public <T> T invoke(String name, Object o, Object... args){
		Class<?>[] argClasses = new Class<?>[args.length];
		for(int i = 0; i < argClasses.length; i++){
			argClasses[i] = args[i].getClass();
		}
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(name, argClasses);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		method.setAccessible(true);
		try {
			return (T) method.invoke(o, args);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public <T> T invoke(String name, Object o, int[] args){
		Class<?>[] argClasses = new Class<?>[args.length];
		for(int i = 0; i < argClasses.length; i++){
			argClasses[i] = int.class;
		}
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(name, argClasses);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		method.setAccessible(true);
		Object[] objs = new Object[args.length];
		for(int i = 0; i < objs.length; i++){
			objs[i] = (Object) args[i];
		}
		try {
			return (T) method.invoke(o, objs);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
