package edu.uprm.ece.icom4035.polynomial;

import edu.uprm.ece.icom4035.list.ListFactory;

public class TermListFactory {
	private static  String factory = "array";
	
	public static ListFactory<Term> newListFactory(){
		// Return a factory for ArrayList
		if (factory == "array") {
			return new ArrayListFactory<Term>();
		}
		else {
			// Return a factory for linked list
			return new SinglyLinkedListFactory<Term>();
		}
	}

	public static void setFactory(String newValue) {
		factory = newValue;
	}

}
