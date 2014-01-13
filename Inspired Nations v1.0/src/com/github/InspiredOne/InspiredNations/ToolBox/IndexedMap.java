package com.github.InspiredOne.InspiredNations.ToolBox;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.github.InspiredOne.InspiredNations.Debug;

/**
 * Allows quick checks if something exists in the map, but when iterating over the map
 * the items are always returned in a certain order. The order can be changed just
 * as one would change the order of a list.
 * @author Jedidiah Phillips
 *
 * @param <E>	the item used as the value
 * @param <T>	the item used as the key 
 */
public class IndexedMap<E, T> implements Map<E, T>,Iterable<E>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4902824227056513918L;
	private Vector<E> indexes = new Vector<E>(); 
	//private ArrayList<E> indexes = new ArrayList<E>();
	private HashMap<E, T> map = new HashMap<E, T>();

	@Override
	public boolean containsKey(Object arg0) {
		Debug.print("Inside IndexedMap!!!!!!! 10");
		return map.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		Debug.print("Inside IndexedMap!!!!!!! 11");
		return map.containsValue(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<E, T>> entrySet() {
		Debug.print("Inside IndexedMap!!!!!!! 12");
		return map.entrySet();
	}

	@Override
	public T get(Object arg0) {
		Debug.print("Inside IndexedMap!!!!!!! 13");
		return map.get(arg0);
	}

	@Override
	public Set<E> keySet() {
		Debug.print("Inside IndexedMap!!!!!!! 14");
		return map.keySet();
	}

	@Override
	public T put(E arg0, T arg1) {
		Debug.print("Inside IndexedMap!!!!!!! " + arg0);
		if(map.containsKey(arg0)) {
			return map.put(arg0, arg1);
		}
		else {
			indexes.add(arg0);
			return map.put(arg0, arg1);
		}
	}

	@Override
	public void putAll(Map<? extends E, ? extends T> arg0) {
		Debug.print("Inside IndexedMap!!!!!!! 1");
		for(E thing:arg0.keySet()) {
			if(!indexes.contains(thing)) {
				indexes.add(thing);
			}
		}
		map.putAll(arg0);
	}

	@Override
	public Collection<T> values() {
		Debug.print("Inside IndexedMap!!!!!!! 2");
		return map.values();
	}

	@Override
	public void clear() {
		Debug.print("Inside IndexedMap!!!!!!! 3");
		indexes.clear();
		map.clear();
	}

	@Override
	public boolean isEmpty() {
		Debug.print("Inside IndexedMap!!!!!!! 4");
		return map.isEmpty();
	}

	@Override
	public T remove(Object key) {
		Debug.print("Inside IndexedMap!!!!!!! 5");
		indexes.remove(key);
		return map.remove(key);
	}

	@Override
	public int size() {
		Debug.print("Inside IndexedMap!!!!!!! 6");
		return map.size();
	}
	
	public E getIndex(int index) {
		Debug.print("Inside IndexedMap!!!!!!! 7");
		return indexes.get(index);
	}
	
	public void insertElement(E arg0, T arg1, int index) throws ArrayIndexOutOfBoundsException {
		Debug.print("Inside IndexedMap!!!!!!! 8");
		if(index > indexes.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if(!indexes.contains(arg0)) {
			indexes.insertElementAt(arg0, index);
		}
		else {
			indexes.remove(arg0);
			indexes.insertElementAt(arg0, index);
		}
		map.put(arg0, arg1);
	}
	
	public void remove(int index) {
		Debug.print("Inside IndexedMap!!!!!!! 9");
		map.remove(indexes.get(index));
		indexes.remove(index);
	}

	@Override
	public Iterator<E> iterator() {
		Debug.print("Inside IndexedMap!!!!!!! 10");
		return indexes.iterator();
	}

}
