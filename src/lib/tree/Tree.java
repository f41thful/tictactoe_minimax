package lib.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import lib.IGenerator;
import lib.tree.visitors.CalculateBranchId;

public class Tree<T> {
	
	public interface GetString<T>{
		public String get(Tree<T> elem);
	}

	protected Tree<T> p;
	protected T e;
	protected ArrayList<Tree<T>> c;
	protected Map<String, Object> map;	
	
	// generate a tree with nodes of depth depth included.
	// a call will breadth == 0 will generate a tree with one node.
	public static <E> Tree<E> generateTree(E elem, IGenerator<E> g, int depth, int breadth){
		return generateTreeImp( elem, g, 0, depth, breadth );
	}
	
	private static <E> Tree<E> generateTreeImp
	(E elem, IGenerator<E> g, int curDepth, int maxDepth, int maxBreadth){
		if(curDepth > maxDepth) return null;
		Tree<E> tree = new Tree<E>(elem);
		List<E> children = g.generate( elem );
	
		for(int i = 0; i < maxBreadth && i < children.size(); i++){
			E child = children.get( i );
			Tree<E> childTree = generateTreeImp(child, g, curDepth + 1, maxDepth, maxBreadth);
			if(childTree != null)
				tree.addChild(childTree);
		}
		return tree;
	}
	
	public Tree(T elem){
		e = elem;
		c = new ArrayList<Tree<T>>();
		map = new HashMap<String, Object>();
	}
	
	public void addChild(T elem){
		Tree<T> e = new Tree<T>(elem);
		addChild(e);
	}
	
	public void addChild(Tree<T> elem){
		elem.setP( this );
		c.add( elem );
	}

	public void setP(Tree<T> p){
		this.p = p;
	}
	
	public Iterator<Tree<T>> getChildrenIterator(){
		return c.iterator();
	}
	
	
	public PreOrderIteratorTree<T> getPreOrderIteratorTree(){
		return new PreOrderIteratorTree<T>(this);
	}
	
	public PostOrderIteratorTree<T> getPostOrderIteratorTree(){
		return new PostOrderIteratorTree<T>(this);
	}
	
	public VisitIterator<T> getVisitPostOrderIteratorTree(ITreeVisitor<T> visitor){
		PostOrderIteratorTree<T> it = getPostOrderIteratorTree();
		return new VisitIterator<T>( this, visitor, it );
	}
	
	
	
	public boolean isLeaf(){
		return c.size() == 0;
	}
	
	public boolean isInternalNode(){
		return !isLeaf();
	}
	
	public T getElem(){
		return e;
	}
	
	
	public String toPostOrderString(){
		PostOrderIteratorTree<T> treeIt = getPostOrderIteratorTree();
		return toOrderString( treeIt, null );
	}

	
	public String toPreOrderString(){
		PreOrderIteratorTree<T> treeIt = getPreOrderIteratorTree();
		return toOrderString( treeIt, null );
	}
	
	public void applyVisitors(ITreeVisitor<T>[] visitors){
		for(ITreeVisitor<T> v : visitors)
		{
			TreeIterator<Tree<T>> it = new PreOrderIteratorTree<T>( this );
			VisitIterator<T> vi = new VisitIterator<T>( this, v, it );
			vi.applyVisitor();
		}
	}
	
	public String toPostOrderStringWithBranchId(GetString<T>[] pgetters){
		List<GetString<T>> getters = new ArrayList<GetString<T>>();
		applyVisitors( new ITreeVisitor[]{new CalculateBranchId<T>()});
		
		PostOrderIteratorTree<T> treeIt = getPostOrderIteratorTree();
		GetString<T> gId = new GetString<T>(){

			@Override
			public String get(Tree<T> elem) {
				return (String) elem.getData( CalculateBranchId.KEY_COMPOSE_BRANCH );
			}
			
		};
		
		GetString<T> gDepth = new GetString<T>(){

			@Override
			public String get(Tree<T> elem) {
				return "Depth: " + elem.getDepth();
			}
			
		};
		
		getters.add(gId);
		getters.add(gDepth);
		for(GetString<T> g : pgetters) getters.add(g);
		return toOrderString( treeIt, getters );
		
	}
	
	public int getDepth(){
		int depth = 0;
		Tree<T> cur = p;
		while(cur != null){
			cur = cur.p;
			depth++;
		}
		return depth;
	}
	
	public void putdata(String key, Object data){
		map.put( key, data );
	}
	
	
	public Object getData(String key){
		return map.get( key );
	}
	
	public List<Tree<T>> getChildren(){
		return c;
	}
	
	public Tree<T> getParent(){
		return p;
	}
	
	
	
	private String toOrderString(TreeIterator<Tree<T>> it, List<GetString<T>> get){
		OrderIterator<T> oit = new OrderIterator<T>( this, it );
		String s = "";
		while(oit.hasNext()){
			Object t = oit.next();
			if(get != null){
				for(GetString<T> g : get)
					s = s + " " + g.get( oit.getLastTree() ) + " ";
			}
			if(t.getClass().isArray()){
				Object[] tArray = (Object[]) t;
				s = s + Arrays.deepToString(tArray) + "\n";
			}else{
				s = s + t + " ";
			}
		}
		return s;
	}
}

