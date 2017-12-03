package app;

import Escuela.Alumno;
import btree.btree;
import node.node;

public class App {

	public static void main(String[] args) {
		btree<Integer> nums = new btree<Integer>();
		nums.add(15);
		nums.add(27);
		nums.add(3);
		nums.add(8);
		nums.add(0);
		nums.add(23);
		nums.add(43);
		nums.add(12);
		nums.add(4);
		nums.add(2);
		nums.add(9);
		nums.add(11);
		nums.add(19);
		nums.add(26);
		nums.add(54);
		
		System.out.println("Imprimir PreOrder");
		nums.Preorder();
		System.out.println("Imprimir In Order");
		nums.Inorder();
		System.out.println("Imrimir PosOrder");
		nums.PostOrder();
		System.out.println("Busqueda de profundidad maxima");
		nums.maxSearch();
		System.out.println("Busqueda de profundidad minima");
		nums.minSearch();
		System.out.println("Profundidad maaxima");
		System.out.println(nums.maxDepth());
		System.out.println("Profundidaad minima");
		System.out.println(nums.minDepth());
		System.out.println("Busqueda de anchura");
		nums.breadthSearch();
		System.out.println("Eliminar elemento 11");
		nums.remove(11);
		System.out.println("Esta Vacio");
		System.out.println(nums.isEmpty());
		System.out.println("Existen elementos");
		System.out.println(nums.exists(9));
		System.out.println("Limpiar Lista");
		nums.clear();
	}

}
