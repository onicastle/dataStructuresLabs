package testerClasses;

import interfaces.Entry;
import treeClasses.LinkedBST;

public class LinkedBSTTester6 {
	public static void main(String[] args) { 
		
		System.out.println("Tester 6 for LinkedBST.\n");
		
		LinkedBST<Integer, String> bst = 
			new LinkedBST<>(new NumberComparator1()); 
		bstInsert(1, "one", bst); 
		bstInsert(5, "five", bst); 
		bstInsert(12, "twelve", bst); 
		bstInsert(200, "two hundred", bst); 
		bstInsert(22, "twenty two", bst); 
		bstInsert(5, "five", bst); 
		bstInsert(42, "forty two", bst); 
		bstInsert(300, "three hundred", bst); 
		bstInsert(2, "two", bst); 

		
		bstInsert(20, "twenty", bst); 
		bstInsert(13, "thirteen", bst);  
		bstInsert(19, "sixteen", bst);  
		bstInsert(7, "seven", bst); 
		bstInsert(10, "ten", bst); 
		
		bstInsert(7, "seven", bst); 
		bstInsert(21, "twenty one", bst);  
		
		showbst("Current tree is: ", bst); 
		System.out.println("Min key entry is: " + bst.min());
		System.out.println("Max key entry is: " + bst.max());
		bst.removeMin(); 
		showbst("After removing current min", bst); 
		
		System.out.println("Min key entry is: " + bst.min());
		bst.removeMin(); 
		showbst("After removing current min", bst); 
		System.out.println("Min key entry is: " + bst.min());

		System.out.println("Max key entry is: " + bst.max());
		bst.removeMax(); 
		showbst("After removing current max", bst); 
		System.out.println("Max key entry is: " + bst.max());


		showbstEntries("Entries in the bst (visiting nodes in inorder)", bst); 
	}
	private static <K,V> void showbstEntries(final String msg, final LinkedBST<K, V> t) { 
		System.out.println("\n" + msg); 
		for (Entry<K,V> e : t) 
			System.out.println(e); 
	}

	private static void showbstEntries(String msg, LinkedBST<Integer, String> bst, Integer key) {
		System.out.println("\n" + msg);
		for (Entry<Integer, String> e : bst.getAll(key))
			System.out.println(e); 
	}

	private static <K, V> void bstInsert(K key, V value, LinkedBST<K, V> bst) { 
		System.out.println("Inserting entry [key = " + key + ", " + " values = " + value + "]"); 
		bst.add(key, value);
		
	}
	private static <K,V> void showbst(final String msg, final LinkedBST<K, V> t) { 
		System.out.println("\n" + msg); 
		t.display(); 
	}
	private static <K,V> void showbstValues(final String msg, final LinkedBST<K, V> t) { 
		System.out.println("\n" + msg); 
		for (Entry<K, V> value : t) 
			System.out.println(value); 
	}

	private static <K,V> void showbstKeys(final String msg, final LinkedBST<K, V> t) { 
		System.out.println("\n" + msg); 
		for (Entry<K,V> e : t) 
			System.out.println(e.getKey()); 
	}
	

	
	
}