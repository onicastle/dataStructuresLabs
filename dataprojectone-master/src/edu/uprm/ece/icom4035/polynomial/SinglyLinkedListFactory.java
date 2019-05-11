package edu.uprm.ece.icom4035.polynomial;

import edu.uprm.ece.icom4035.list.List;
import edu.uprm.ece.icom4035.list.ListFactory;
import edu.uprm.ece.icom4035.list.SinglyLinkedList;

public class SinglyLinkedListFactory<T> implements ListFactory<Term> {

	@Override
	public List<Term> newInstance() {
		// TODO Auto-generated method stub
		return new SinglyLinkedList<Term>();
	}

	

}
