package labUtils;

import treeClasses.LinkedBinaryTree;
import treeClasses.LinkedTree;
import treeInterfaces.Position;
import treeInterfaces.Tree;

public class Utils {
	public static <E> void displayTree(String msg, Tree<E> t) { 
		System.out.println("\n\n" + msg); 
		t.display(); 
	}

	public static <E> void displayTreeElements(String msg, Tree<E> t) {
		System.out.println("\n\n" + msg); 
		for (E element : t)
			System.out.println(element); 
		
	}
	
	public static LinkedTree<Integer> buildExampleTreeAsLinkedTree() { 
		LinkedTree<Integer> t = new LinkedTree<>(); 
		Position<Integer> z = t.addRoot(4); 
		z = t.addChild(z, 9); 
		t.addChild(z, 7); 
		t.addChild(z, 10); 
		z = t.root(); 
		z = t.addChild(z, 20); 
		Position<Integer> q = t.addChild(z, 15); 
		t.addChild(q, 12); 
		q=t.addChild(q, 17); 
		t.addChild(q, 19);
		z = t.addChild(z, 21); 
		z = t.addChild(z, 40); 
		t.addChild(z, 30); 
		t.addChild(z, 45); 

		return t; 

	}
	
	public static LinkedBinaryTree<Integer> buildExampleTreeAsLinkedBinaryTree() { 
		LinkedBinaryTree<Integer> t = new LinkedBinaryTree<>(); 
		Position<Integer> k = t.addRoot(4); 
		k = t.addLeft(k, 9); 
		t.addLeft(k, 7); 
		t.addRight(k, 10); 
		k = t.root(); 
		k = t.addRight(k, 20); 
		Position<Integer> q = t.addLeft(k, 15); 
		t.addLeft(q, 12);
		q = t.addRight(q, 17); 
		t.addLeft(q, 19); 
		k = t.addRight(k, 21); 
		k = t.addRight(k, 40); 
		t.addLeft(k, 30); 
		t.addRight(k, 45); 

		
		return t; 
	}


}
