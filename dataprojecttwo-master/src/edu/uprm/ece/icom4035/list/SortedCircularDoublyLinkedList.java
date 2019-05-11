package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {
	Node header;
	int currentSize;

	//Default Constructor
	
	public SortedCircularDoublyLinkedList(){
		
		header = new Node();
		
		header.setValue(null);
		
		header.setNext(header);
		
		header.setPrev(header);
		
		currentSize = 0;
	}

	//Iterator Method 
	@Override
	public Iterator<E> iterator() {
		
		return new ListIterator();
		
	}

	/**Add method
	 * @param obj element to be added to the DLL
	 */
	@Override
	public boolean add(E obj) {

		if(obj == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		boolean term = false; //helper bool variable to verify if the element is added. 

		for(Node temporary = header.getNext() ; temporary.getValue()!= null ; temporary=temporary.getNext()){
			if(obj.compareTo(temporary.getValue()) < 0){
				
				if(temporary.getPrev().getValue() == (header.getValue())){
					
					Node temporary2= new Node();
					
					temporary2.setValue(obj);
					
					temporary2.setNext(temporary);
					
					temporary2.setPrev(header);
					
					header.setNext(temporary2);
					
					temporary.setPrev(temporary2);
					
					term = true;
					
					currentSize++;
					
					break;
					
				}

				else{
					
					Node temporary2= new Node();
					
					temporary2.setValue(obj);
	
					temporary2.setNext(temporary);
					
					temporary2.setPrev(temporary.getPrev());
					
					temporary.getPrev().setNext(temporary2);
					
					temporary.setPrev(temporary2);
					
					term =true;
					
					currentSize++;
					
					break;
				}
			}
		}

		if(term == false){
			
			Node temporary = new Node();
			
			temporary.setValue(obj);
			
			temporary.setNext(header);
			
			temporary.setPrev(header.getPrev());
			
			header.getPrev().setNext(temporary);
			
			header.setPrev(temporary);
			
			term =true;
			
			currentSize++;
		
		}
		
		return term;
	
	}
	/**
	 * @return current size
	 */
	@Override
	public int size() {
		return currentSize;
	}

	/** Remove method to remove an obj at its first instance
	 * @return if the obj was removed
	 * @param obj to be removed on the first appereance
	 */
	@Override
	public boolean remove(E obj) {
		if(obj == null)
			throw new IllegalArgumentException("Parameter cannot be null");

		for(Node temporary = header.getNext(); temporary.getValue() != null; temporary = temporary.getNext()){
			if(obj.compareTo(temporary.getValue())==0){
				temporary.getPrev().setNext(temporary.getNext());
				temporary.getNext().setPrev(temporary.getPrev());
				temporary.setNext(null);
				temporary.setPrev(null);
				temporary.setValue(null);
				currentSize--;
				return true;
			}
		}
		return false;
	}

	/** Remove an element at an specific index
	 * @param int index to be removed
	 * @return return boolean if value is removed or not
	 */
	@Override
	public boolean remove(int index) {
		if((index < 0) || (index > this.currentSize)) throw new IndexOutOfBoundsException();

		int i = 0;
		
		for(Node temporary = header.getNext(); temporary.getValue()!=null; temporary = temporary.getNext(), i++){
			
			if(index == i){
				
				temporary.getPrev().setNext(temporary.getNext());
				
				temporary.getNext().setPrev(temporary.getPrev());
				
				temporary.setNext(null);
				
				temporary.setPrev(null);
				
				temporary.setValue(null);
				
				currentSize--;
				
				return true;
			}
			
		}
		
		return false;
		
	}

	/**
	 * Remove al repetion of an specific object
	 * @param E obj to be remove
	 * @return int removed count
	 */
	@Override
	public int removeAll(E obj) {
		int counter = 0;
		while(this.remove(obj)){
			counter++;
		}
		return counter;
	}

	/**First element of de DLL
	 * @return E value of the first node of the DLL
	 */
	@Override
	public E first() {
		if(isEmpty())
			return null;
		return header.getNext().getValue();
	}
	/** Last element of the DLL
	 * @return E value of the last node of the DLL
	 */
	@Override
	public E last() {
		if(isEmpty()) return null;
		
		return header.getPrev().getValue();
	}

	/**Get an specific node on the DLL
	 * @param index node to be returned
	 * @return E element at the specific node
	 */
	@Override
	public E get(int index) {
		
		if((index < 0) || (index > this.currentSize)) throw new IndexOutOfBoundsException();

		int count = 0;
		for(Node temporaryorary = header.getNext(); temporaryorary.getValue()!=null; temporaryorary = temporaryorary.getNext(), count++){
			if(count == index)
				return temporaryorary.getValue();	
		}

		return null;
	}
	/**Clear the whole DLL
	 * 
	 */
	@Override
	public void clear() {
		while(!this.isEmpty())
			this.remove(0);

	}

	/** Verify if a element is on the DLL or not
	 * @param E element to compare
	 *  if it is on the DLL or not
	 */
	@Override
	public boolean contains(E e) {
		for(Node temporaryorary = header.getNext(); temporaryorary.getValue()!=null; temporaryorary = temporaryorary.getNext()){
			if(temporaryorary.getValue().compareTo(e)==0){
				return true;
			}
		}

		return false;
	}

	/**Verify if the DLL is empty or not
	 * @return boolean size == 0
	 */
	@Override
	public boolean isEmpty() {
		return this.currentSize == 0;
	}

	/**Index iterator
	 *@param int index to start interation
	 */

	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator(index);
	}

	/**fistIndex of the parameter at the DLL
	 * @param element to verify its first index
	 * return int the first index of the list
	 */

	@Override
	public int firstIndex(E e) {
		int count = 0;
		for(Node temporary = header.getNext(); temporary.getValue()!= null; temporary = temporary.getNext(),count++){
			if(e.compareTo(temporary.getValue())==0){
				return count;
			}
		}

		return -1;
	}

	/**lastIndex of the paramter at the DLL
	 * @param element ot verify its last index on the 
	 */
	@Override
	public int lastIndex(E e) {
		int count = currentSize-1;
		
		for(Node temporary = header.getPrev(); temporary.getValue()!= null; temporary = temporary.getPrev(),count--)
		{ if(e.compareTo(temporary.getValue())==0){ return count;}}

		return -1;
	}

	/**
	 * reverse iterator of the DLL
	 */
	@Override
	public ReverseIterator<E> reverseIterator() {
		return new ReverseListIterator();
	}
	
	/**
	 * ReverseIterator from an specific index
	 * @param integer of an index for starting the iteration
	 */
	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		return new ReverseListIterator(index);
	}	

	/**
	 * Private class for nodes
	 *
	 */
	private class Node {
		private E value;
		private Node next;
		private Node prev;

		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node getPrev() {
			return prev;
		}
		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}

	/**
	 * Private class for a foward iterator
	 *
	 */
	private class ListIterator implements Iterator<E>{
		private Node nextNode;

		public ListIterator(){
			this.nextNode = header.getNext();
		}

		public ListIterator(int index){
			if((index < 0) || (index>currentSize))
				throw new IndexOutOfBoundsException();

			int counter = 0;
			Node temporary;

			for(temporary = header.getNext(); counter < index; temporary = temporary.getNext(), counter++);
			this.nextNode = temporary;
		}

		@Override
		public boolean hasNext() {
			return nextNode.getValue() != null;
		}

		@Override
		public E next() {
			
			if (hasNext()){
				
				E result = this.nextNode.getValue();
				
				this.nextNode = this.nextNode.getNext();
				
				return result;
				
			}
			
			else {
				
				throw new NoSuchElementException();
			}
			
		}

		@Override
		public void remove() {
			
			throw new UnsupportedOperationException();

		}	
	}
	/**
	 * Private class for a reverse iterator
	 *
	 */
	private class ReverseListIterator implements ReverseIterator<E>{
		
		private Node prevNode;

		public ReverseListIterator(){
			
			this.prevNode = header.getPrev();
			
		}

		public ReverseListIterator(int index){
			
			int counter = currentSize;
			
			Node temporaryorary;

			for(temporaryorary = header.getPrev(); counter > currentSize-index; temporaryorary = temporaryorary.getPrev(), counter--);
			this.prevNode = temporaryorary;
		}

		@Override
		public boolean hasPrevious() {
			
			return prevNode != header;
			
		}
		@Override
		public E previous() {
			
			if (hasPrevious()){
				
				E result = prevNode.getValue();
				
				prevNode = prevNode.getPrev();
				
				return result;
				
			}
			
			else {
				
				throw new NoSuchElementException();

			}

		}

	}
}
