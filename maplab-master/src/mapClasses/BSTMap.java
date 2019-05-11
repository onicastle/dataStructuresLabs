package mapClasses;

import java.util.ArrayList;
import java.util.Comparator;

import treeClasses.LinkedBST;
import interfaces.Entry;
import interfaces.Map;
import positionalStructures.Position;

public class BSTMap<K, V> implements Map<K, V> {
	// the binary search tree supporting this implementation of the map
	private LinkedBST<K, V> tree;   	
	
	/**
	 * Creates an instance of BSTMap. 
	 * @param cmp the comparator of keys that is received and which shall
	 * be used to compare keys of entries. 
	 */
	public BSTMap(Comparator<K> cmp) { 
		tree = new LinkedBST<>(cmp); 
	}
	
	@Override
	/**
	 * the size of the map is the size of the tree. 
	 */
	public int size() {
		return tree.size();
	}

	@Override
	/** 
	 * the map is empty iff the tree is empty
	 */
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	/**
	 * Get operation of the map. 
	 */
	public V get(K key) {
		// for for the entry having given key in the tree, if any
		Entry<K, V> result = tree.get(key); 
		
		// if not found, return null
		if (result == null) return null; 
		
		// if found, the value of the element that matches the search.
		return result.getValue();
	}

	@Override
	/**
	 * put of the map...
	 */
	
	/**
	 * If the collection does not have an entry with key “equal” to 
	 * the given key, then it adds a new entry containing the 
	 * given key-value pair and return null. I it already exists, 
	 * it replaces with value the existing value of the entry with 
	 * key equal to key and return the old value
	 * @param key key of the new element. 
	 * @param value value of the new element
	 * @return null if no entry exists in the collection with the 
	 * given key value; otherwise it returns reference to the value
	 * being replaced.
	 */

	public V put(K key, V value) {
		ModifiableEntry<K, V> result = (ModifiableEntry<K, V>) tree.get(key); 
		
		if(result != null) {
			((ModifiableEntry<K, V>) tree.get(key)).setValue(value);
			return result.getValue();
		}
		
		tree.add(key, value);
		return null;
	}

	@Override
	/**
	 * remove of the map
	 */
	public V remove(K key) {
	
		Entry<K, V> result = tree.get(key);
		
		if (result != null) {
			tree.remove(key);
			return result.getValue();
		}
		
		return result.getValue(); 
	}

	@Override
	public Iterable<K> keySet() {
		ArrayList<K> keys = new ArrayList<>();
		for(Entry<K,V> w : this.tree) {
			keys.add(w.getKey());
		}
		return keys; 
	}

	@Override
	public Iterable<V> values() {
		ArrayList<V> t = new ArrayList<>();
		for(Entry<K, V> w : this.tree) {
			t.add(w.getValue());
		}
		return t;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> list = new ArrayList<>(); 
		
		for (Position<Entry<K, V>> q : tree.positions())
			list.add(q.getElement()); 
		
		return list;
	}

	public void displayMAPTree() {   // This operation has been added just for testing
		this.tree.display();
	}
		
}
