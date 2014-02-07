package com.github.InspiredOne.InspiredNations.ToolBox;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
/**
 * My own implementation of a map that maps to a Set. If the key is not present, then
 * the map adds a Set with a single entry. Every subsequent addition to the key
 * is appened to the Set.
 * @author Jedidiah Phillips
 *
 * @param <T>	Key
 * @param <K>	Value
 */
public class MultiMap<T, K> implements Map<T, HashSet<K>>, Serializable, Iterable<K> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5789101682525659411L;
	
	protected HashMap<T, HashSet<K>> set = new HashMap<T, HashSet<K>>();

	@Override
	public void clear() {
		set = new HashMap<T, HashSet<K>>();
		
	}

	@Override
	public boolean containsKey(Object arg0) {
		return set.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		boolean output = false;
		
		for(Iterator<HashSet<K>> iter = set.values().iterator();iter.hasNext();) {
			HashSet<K> searchColl = iter.next();
			for(Iterator<K> iter2 = searchColl.iterator(); iter2.hasNext();) {
				K value = iter2.next();
				if(value == arg0) {
					output = true;
					break;
				}
			}
		}
		
		return output;
	}

	@Override
	public Set<Entry<T, HashSet<K>>> entrySet() {
		
		Set<Entry<T, HashSet<K>>> output = new HashSet<Entry<T,HashSet<K>>>();
		
		for(Iterator<T> iter1 = set.keySet().iterator(); iter1.hasNext();) {
			T key = iter1.next();
			for(Iterator<K> iter2 = set.get(key).iterator(); iter2.hasNext();) {
				K value = iter2.next();
				HashSet<K> input = new HashSet<K>();
				input.add(value);
				output.add(new AbstractMap.SimpleEntry<T,HashSet<K>>(key, input));
			}
		}
		return output;
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public Set<T> keySet() {
		return set.keySet();
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public Collection<HashSet<K>> values() {
		Collection<HashSet<K>> values = new ArrayList<HashSet<K>>();
		for(Iterator<T> iter1 = set.keySet().iterator(); iter1.hasNext();) {
			T key = iter1.next();
			values.add(set.get(key));
		}
		return values;
	}

	@Override
	public HashSet<K> get(Object key) {
		return set.get(key);
	}

	@Override
	public HashSet<K> put(T key, HashSet<K> value) {
		return set.put(key, value);
	}
	
	public void put(T key, K value) {
		if(set.containsKey(key)) {
			set.get(key).add(value);
		}
		else {
			HashSet<K> setval = new HashSet<K>();
			setval.add(value);
			set.put(key, setval);
		}
	}

	@Override
	public HashSet<K> remove(Object key) {
		return set.remove(key);
	}

	@Override
	public void putAll(Map<? extends T, ? extends HashSet<K>> m) {
		for(Iterator<? extends T> iter = m.keySet().iterator(); iter.hasNext();) {
			T key = iter.next();
			set.put(key, m.get(key));
		}
	}

	@Override
	public Iterator<K> iterator() {
		return null;
	}
	
	public List<K> getAllThatPass(Condition<K> condition) {
		List<K> output = new ArrayList<K>();
		for(Iterator<K> iter = this.iterator();iter.hasNext();) {
			K k = iter.next();
			if(condition.rulePass(k)) {
				output.add(k);
			}
		}
		return output;
	}
}
