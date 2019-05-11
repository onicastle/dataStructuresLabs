package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>{

	private SNode<E> header;
	int length = 0;


	public SinglyLinkedList() {
		this.header = new SNode<E>();
		this.length = 0;


	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}


	private class ListIterator<T> implements Iterator<E>{

		private SNode<E> currentNode;

		public ListIterator() {
			this.currentNode = (SNode<E>)header.getNext();
		}
		@Override
		public boolean hasNext() {
			return this.currentNode != null;
		}

		@Override
		public E next() {
			if (this.hasNext()) {
				E result= null;
				result = this.currentNode.getElement();
				this.currentNode = this.currentNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}

	}




	@Override
	public void add(E obj) {


		if(length == 0) {
			
			this.addFirst(obj);
			
		}
		else{
			
			SNode<E> curr = header.getNext();
			
			while(curr.getNext()!=null)
				curr = curr.getNext();
			SNode<E> nuevo = new SNode<E>(obj);
			curr.setNext(nuevo);
			
		}
		
		length++;
		
	}



	@Override
	public void add(int index, E obj) {
		
		if(index<0 || index >length) throw new IndexOutOfBoundsException("add: invalid index = "+index);
		
		if(index == 0) this.addFirst(obj);
		
		else {
			
			SNode<E> curr = header.getNext();
			
			int count = 1;
			
			while(count<index) {
				
				curr = curr.getNext();
				
				count++;
				
			}
			
			SNode<E> nuevo = new SNode<E>(obj,curr.getNext());
			
			curr.setNext(nuevo);
			
		}
		
		length++;
		
	}
	
	@Override
	public boolean remove(E obj) {
		
		SNode<E> curr = header.getNext();
		
		if(curr.getElement().equals(obj)) {
			
			header.setNext(header.getNext().getNext());
			
			length--;
			
			return true;
		}

		while(curr.getNext()!=null) {
			
			if(curr.getNext().getElement().equals(obj)) {
				
				curr.setNext(curr.getNext().getNext());
				
				length--;
				
				return true;
				
			}
			
			curr=curr.getNext();
			
		}
		if(curr.getNext().getElement().equals(obj)) {
			
			curr.setNext(null);
			
			length--;
			
			return true;
			
		}
		
		return false;
		
	}

	@Override
	public boolean remove(int index) {
		
		if(index<0 || index >length) throw new IndexOutOfBoundsException("add: invalid index = " + index);
		
		if(index==0) {
			
			header.setNext(header.getNext().getNext());
			
			length--;
			
			return true;
			
		}
		
		int count = 1;
		
		SNode<E> curr = header.getNext();
		
		while(count<index) {
			
			count++;
			
			curr=curr.getNext();
			
		}
		
		curr.setNext(curr.getNext().getNext());
		
		length--;
		
		return true;

	}

	@Override
	public int removeAll(E obj) {
		
		int count =0;
		
		while(!this.contains(obj)) {
			
			count++;
			
			this.remove(obj);
			
		}
		
		return count;
		
	}

	@Override
	public E get(int index) {
		
		int count = 0;
		
		SNode<E> curr = header.getNext();
		
		while(count!=index) {
			
			count++;
			
			curr=curr.getNext();
			
		}
		
		return curr.getElement();
		
	}

	@Override
	public E set(int index, E obj) {
		
		int count = 0;
		
		SNode<E> curr = header.getNext();
		
		while(count!=index) {
			
			count++;
			
			curr=curr.getNext();
			
		}
		
		E etr = curr.getElement();
		
		curr.setElement(obj);
		
		return etr;
		
	}

	@Override
	public E first() {

		return header.getNext().getElement();
		
	}

	@Override
	public E last() {
		
		SNode<E> curr =header.getNext();
		
		for(int i =1;i<length;i++)
			curr=curr.getNext();
		
		return curr.getElement();
	}

	@Override
	public int firstIndex(E obj) {
		
		int index = 0;
		
		SNode<E> curr = header.getNext();
		
		while(curr.getNext()!=null) {
			
			if(curr.getElement().equals(obj)) {
				
				return index;
				
			}
			
			curr=curr.getNext();
			
			index++;
			
		}
		
		if(curr.getElement().equals(obj)) {return length-1;}
		
		return -1;
	}

	@Override
	public int lastIndex(E obj) {
		
		int index = 0;
		
		int itr = -1;
		
		SNode<E> curr  = header.getNext();
		
		while(curr.getNext()!=null) {
			
			if(curr.getElement().equals(obj))
				itr=index;
			
			curr=curr.getNext();
			
			index++;
			
		}
		
		if(curr.getElement().equals(obj)) return length-1;
		
		return itr;
		
	}

	@Override
	public int size() {

		return length;
		
	}

	@Override
	public boolean isEmpty() {

		return length==0;
		
	}

	@Override
	public boolean contains(E obj) {
		
		SNode<E> curr = header.getNext();
		
		while(curr.getNext()!=null) {
			
			if(curr.getElement().equals(obj)) return true;
			
		}
		
		if(curr.getNext().getElement().equals(obj))return true;
		
		return false;
		
	}

	@Override
	public void clear() {
		
		header.setNext(null);
		
		length=0;
		
	}
	
	private void addFirst(E obj) {
		
		SNode<E> nuevo = new SNode<E>(obj,header.getNext());
		
		header.setNext(nuevo);
		
	}
	
	private static class SNode<E> {
		
		private E element;
		
		private SNode<E> next;
		
		public SNode() {
			
			element = null;
			
			next = null;
			
		}
		public SNode(E data, SNode<E> next) {
			this.element = data;
			this.next = next;
		}
		public SNode(E data)  {
			this.element = data;
			next = null;
		}
		public E getElement() {
			return element;
		}
		public void setElement(E data) {
			this.element = data;
		}
		public SNode<E> getNext() {
			return next;
		}
		public void setNext(SNode<E> next) {
			this.next = next;
		}
	}
}
