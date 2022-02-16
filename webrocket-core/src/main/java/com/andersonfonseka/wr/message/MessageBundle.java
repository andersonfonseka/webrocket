package com.andersonfonseka.wr.message;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MessageBundle {
	
	private Map<String, Message> bundleMap = new HashMap<String, Message>();

	public void add(String key, Message message) {
		this.bundleMap.put(key, message);
	}
	
	public Message getBundle(String key) {
		return this.bundleMap.get(key);
	}
	
	public Set<String> getKeys(){
		return this.bundleMap.keySet();
	}
}
