package btree;

import node.node;

public class btree<T extends Comparable<T>> implements Comparable<T> {
	private node<T> root = null;
	private int height = 0;

	public btree(){
		this.root = new node<>();
	}

	public btree(T value){
		this();
		root.setValue(value);
	}

	public void add(T value){
		if (root.getValue() == null)
			root.setValue(value);
		else
			add(value, root);
	}

	private void add(T value, node<T> root) {
		if (root.getValue().compareTo(value) >= 1) {
			if (root.getLeft() == null) {
				root.setLeft(new node<>(value));
				return;
			} else
				add(value, root.getLeft());
		} else if (root.getValue().compareTo(value) <= -1 || root.getValue().compareTo(value) == 0) {
			if (root.getRight() == null) {
				root.setRight(new node<>(value));
				return;
			} else
				add(value, root.getRight());
		}
		height++;
	}
	
	public void clear(){
		root = null;
		System.gc();
	}

	public boolean remove(T value) {
		node<T> tmp = deepSearch(value);
		if (tmp != null)
			return remove(value, tmp, isChild(value));
		return false;
	}

	private boolean remove(T value, node<T> root, node<T> imyourfather) {
		if (isChild(value) == null) {
			node<T> miNode = minSearch(root.getRight());
			miNode.setLeft(root.getLeft());
			this.root = root.getRight();
			return true;
		}if (root.getLeft() == null && root.getRight() == null) {
			if (imyourfather.getLeft() != null && imyourfather.getLeft().equals(root))
				imyourfather.setLeft(null);
			else if (imyourfather.getRight() != null)
				imyourfather.setRight(null);
			return true;
		} else if (root.getLeft() != null && root.getRight() == null) {
			if (imyourfather.getLeft().equals(root))
				imyourfather.setLeft(root.getLeft());
			else
				imyourfather.setRight(root.getLeft());
			return true;
		} else if (root.getLeft() == null && root.getRight() != null) {
			if (imyourfather.getLeft().equals(root))
				imyourfather.setLeft(root.getRight());
			else
				imyourfather.setRight(root.getRight());
			return true;
		} else {
			if (imyourfather.getLeft().equals(root)) {
				node<T> left = minSearch(root.getRight());
				left.setLeft(root.getLeft());
				imyourfather.setLeft(root.getRight());
			} else {
				node<T> left = minSearch(root.getRight());
				left.setLeft(root.getLeft());
				imyourfather.setRight(root.getRight());
			}
			return true;
		}
	}

	public void Inorder() {
		Inorder(root);
	}

	private void Inorder(node<T> root) {
		if (root.getLeft() != null){
			Inorder(root.getLeft());
		}
		System.out.println(root.getValue());
		if (root.getRight() != null){
			Inorder(root.getRight());
		}
	}

	public void Preorder(){
		Preorder(root);
	}

	private void Preorder(node<T> root) 
	{
		System.out.println(root.getRight().getValue());
		if (root.getLeft() != null){
			Inorder(root.getLeft());
		}
		if (root.getRight() != null){
			Inorder(root.getRight());
		}
	}

	public void PostOrder(){
		PostOrder(root);
	}
	
	private void PostOrder(node<T> root)
	{
		if (root.getLeft() != null){
			Inorder(root.getLeft());
		}
		if (root.getRight() != null){
			Inorder(root.getRight());
		}
		System.out.println(root.getRight().getValue());
	}

	public node<T> isChild(T value) 
	{
		if (deepSearch(value) == null)
			return null;
		return isChild(value, root, null);
	}

	private node<T> isChild(T value, node<T> root, node<T> imyourfather) 
	{
		if (root != null) {
			if (root.getValue().equals(value))
				return imyourfather;
			if (root.getValue().compareTo(value) <= -1)
				return isChild(value, root.getRight(), root);
			else
				return isChild(value, root.getLeft(), root);
		} else
			return null;
	}

	public boolean isEmpty(){
		if (root==null) 
			return true;
		else 
			return false;
	}

	public node<T> deepSearch(T value){
		return deepSearch(value, root);
	}

	private node<T> deepSearch(T value, node<T> root){
		if (root != null){
			if (root.getValue().equals(value))
				return root;
			if (root.getValue().compareTo(value) <= -1)
				return deepSearch(value, root.getRight());
			else
				return deepSearch(value, root.getLeft());
		}else
			return null;
	}
	
	public int Deep() {
		return Deep(root);
	}

	private int Deep (node<T> actual) {
		if (actual == null)
			return -1;
		else
			return 1 + Math.max(Deep(actual.getLeft()), Deep(actual.getRight()));
	}
	public void breadthSearch() {
		breadthSearch(root);
	}
	
	public boolean exists(T value){
		if (deepSearch(value)!=null) 
			return true; 
		else 
			return false;
	}
	
	private void breadthSearch(node<T> nodo){
		int prof = 0;
		int n = Deep();
		n = 0;
		int con = 0; 
		int cantidad = 1; 
		int PosFin = 1;

		for (; n >= 0; n--)
			prof += Math.pow(2, n);
		prof++;
		node Cola[] = new node[prof];
		Cola[1] = nodo;
		int x = 1;

		for (int i = 2; i < prof; i += 2, x++) {
			if (Cola[x] == null) {
				Cola[i] = null;
				Cola[i + 1] = null;
			} 
			else {
				Cola[i] = Cola[x].getLeft();
				Cola[i + 1] = Cola[x].getRight();
			}
		}

		for (int i = 1; i < prof; i++) {
			if (i == Math.pow(2, n)) {
				System.out.print("Nivel del arbol " + (n) + ": ");
				n++;
			}
			if (Cola[i] != null) {
				System.out.print("[" + Cola[i].getValue() + "]");
				con++;
			}
			if (PosFin == i && cantidad == Math.pow(2, --n)) {
				if (cantidad == 1)
					System.out.print("Total de nodos en la raiz: " + con);
				else
					System.out.print("Total de nodos: " + con);
				con = 0;
				cantidad *= 2;
				PosFin += (int) Math.pow(2, ++n);
			}
		}
    }
	
	public node<T> maxSearch(){
		return maxSearch(root);
	}
	private node<T> maxSearch(node<T> root){
		while (root.getRight() != null)
			root = root.getRight();
		System.out.println("La busqueda maxima " + root.getValue());
		return root;
	}
	
	public node<T> minSearch(){
		return minSearch(root);
	}
	private node<T> minSearch(node<T> root){
		while (root.getLeft() != null)
			root = root.getLeft();
		System.out.println("La busqueda minima " + root.getValue());
		return root;
	}
	public int maxDepth(){
		return maxDepth(root);
	}
	private int maxDepth(node<T> root) {
		if (root == null)
			return 0;
		else{
	            int LeftDepth = maxDepth(root.getLeft());
	            int RightDepth = maxDepth(root.getRight());
	            if (LeftDepth > RightDepth)
	                return (LeftDepth + 1);
	            else
	                return (RightDepth + 1);
	        }
	}
	public int minDepth(){
		return minDepth(root);
	}
	
	private int minDepth(node<T> root) {
		if (root == null)
			return 0;
		else{
	            int LeftDepth = maxDepth(root.getLeft());
	            int RightDepth = maxDepth(root.getRight());
	  
	            if (LeftDepth < RightDepth)
	                return (LeftDepth + 1);
	            else
	                return (RightDepth + 1);
	        }
	}

	@Override
	public int compareTo(T o) 
	{
		return 0;
	}
}
