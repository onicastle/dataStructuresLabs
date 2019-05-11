package labUtils;

import java.util.Iterator;

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
	


	public static <E> void displayIter(String msg, Iterator<E> iter) { 
		System.out.println(msg); 
			while (iter.hasNext()) 
				System.out.println(iter.next()); 
} 
	
	public static LinkedTree<Character> buildTrieAsLinkedTree() { 
		   //String[] words = {"sal", "sala", "salado", "salto", "si", "u", "un", "uno"}; 

		   String[] words = {"aleluya", "aleluyado", "aleta", "aletas", "alerta", 
		        "alertado", "altercado", "altercados", "altura", "alturo", "alturito", 
		         "balacera", "bala", "balas", "bala", "balon", "barato"};
		        
		   LinkedTree<Character> t = new LinkedTree<>(); 
		   Position<Character> p = t.addRoot('['); 
		   for (String s : words)
		    addWordToTrie(t, p, s, 0);   // auxiliary method below
		            
		   return t; 
		}
		    
		private static void addWordToTrie(LinkedTree<Character> t, 
		        Position<Character> r, String s, int index) {
		   if (index == s.length()) 
		    t.addChild(r,  ']'); 
		   else { 
		      Position<Character> pcc = null;   // position in children of r containing s[index] 
		    for (Position<Character> p : t.children(r))
		       if (p.getElement().equals(s.charAt(index))) pcc = p; 
		    if (pcc == null)
		       r = t.addChild(r, s.charAt(index)); 
		    else 
		       r = pcc;
		    addWordToTrie(t, r, s, index+1); 

		   }
		}


	
	public static LinkedTree<Integer> buildExampleTreeAsLinkedTree() { 
		LinkedTree<Integer> t = new LinkedTree<>(); 
		t.addRoot(4);
		Position<Integer> a = t.addChild(t.root(), 9);
		t.addChild(a, 7);
		t.addChild(a, 10);
		a = t.addChild(t.root(), 20);
		Position<Integer> b = t.addChild(a, 15);
		t.addChild(b, 12);
		t.addChild(t.addChild(b, 17),19);
		b = t.addChild(a, 21);
		a= t.addChild(b, 40);
		t.addChild(a, 30);
		t.addChild(a, 45);
		return t; 
	}
	
	public static LinkedBinaryTree<Integer> buildExampleTreeAsLinkedBinaryTree() { 
		LinkedBinaryTree<Integer> t = new LinkedBinaryTree<>(); 
		t.addRoot(4);
		Position<Integer> a = t.addLeft(t.root(), 9);
		t.addLeft(a, 7);
		t.addRight(a, 10);
		a = t.addRight(t.root(), 20);
		Position<Integer> b = t.addLeft(a, 15);
		t.addLeft(b, 12);
		t.addLeft(t.addRight(b, 17), 19);
		b = t.addRight(a, 21);
		a = t.addLeft(b, 40);
		t.addLeft(a, 30);
		t.addRight(a, 45);
		
		return t; 
	}
//ejercicios para terminar para el viernes 3,4,6,7

}
