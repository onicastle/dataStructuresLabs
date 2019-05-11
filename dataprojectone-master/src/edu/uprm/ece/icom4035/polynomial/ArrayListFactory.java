package edu.uprm.ece.icom4035.polynomial;


import edu.uprm.ece.icom4035.list.ArrayList;
import edu.uprm.ece.icom4035.list.List;
import edu.uprm.ece.icom4035.list.ListFactory;

public class ArrayListFactory<E> implements ListFactory<E> {

	private static final int DEFAULT_SIZE = 10;
	

	@Override
	public List<E> newInstance() {
		return new ArrayList<E>(DEFAULT_SIZE);
	
	}

}
