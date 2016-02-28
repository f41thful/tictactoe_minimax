package TreeGetters;

import Logic.Minimax;
import lib.tree.Tree;
import lib.tree.Tree.GetString;

public class GetValue<T> implements GetString<T>{
	@Override
	public String get(Tree<T> elem) {
		Object o = elem.getData( Minimax.KEY );
		if(o != null){
			return "Value: " + String.valueOf(o);
		}
		return "";
	}
		
}

