package com.commons.cache;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 */
public class CacheKeysPool {
	private Map<String, Set<String>> pool = new ConcurrentHashMap<String, Set<String>>();

	public Set<String> get(String key) {
		if (pool.get(key) == null) {
			pool.put(key, new HashSet<String>());
		}
		return pool.get(key);
	}

	public Set<String> put(String key, Set<String> value) {
		return pool.put(key, value);
	}

	public void putElement(String key, String element) {
		if (pool.get(key) == null) {
			pool.put(key, new HashSet<String>());
		}
		pool.get(key).add(element);
	}

	public Set<String> remove(String key) {
		return pool.remove(key);
	}

	public void clear() {
		pool.clear();
	}

	public Set<String> keySet() {
		return pool.keySet();
	}

	public Map<String, Set<String>> getOriginalPool() {
		return pool;
	}

	public Set<Entry<String, Set<String>>> entrySet() {
		return this.pool.entrySet();
	}

	public void putAll(CacheKeysPool pool) {
		for (Entry<String, Set<String>> entry : pool.entrySet()) {
			for (String item : entry.getValue()) {
				this.putElement(entry.getKey(), item);
			}
		}
	}
}