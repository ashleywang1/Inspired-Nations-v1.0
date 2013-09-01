package com.github.InspiredOne.InspiredNations.ToolBox;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class IndexedMap<E, T> implements Map<E, T> {

	private Vector<E> indexes = new Vector<E>(); 
	//private ArrayList<E> indexes = new ArrayList<E>();
	private HashMap<E, T> map = new HashMap<E, T>();

	@Override
	public boolean containsKey(Object arg0) {
		return map.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return map.containsValue(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<E, T>> entrySet() {
		return map.entrySet();
	}

	@Override
	public T get(Object arg0) {
		return map.get(arg0);
	}

	@Override
	public Set<E> keySet() {
		return map.keySet();
	}

	@Override
	public T put(E arg0, T arg1) {
		indexes.add(arg0);
		return map.put(arg0, arg1);
	}

	@Override
	public void putAll(Map<? extends E, ? extends T> arg0) {
		indexes.addAll(arg0.keySet());
		map.putAll(arg0);
	}

	@Override
	public Collection<T> values() {
		return map.values();
	}

	@Override
	public void clear() {
		indexes.clear();
		map.clear();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public T remove(Object key) {
		indexes.remove(key);
		return map.remove(key);
	}

	@Override
	public int size() {
		return map.size();
	}
	
	public E getIndex(int index) {
		return indexes.get(index);
	}
	
	public void insertElement(E arg0, T arg1, int index) throws ArrayIndexOutOfBoundsException {
		if(index > indexes.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		indexes.insertElementAt(arg0, index);
		map.put(arg0, arg1);
	}
	
	public void remove(int index) {
		map.remove(indexes.get(index));
		indexes.remove(index);
	}

}
