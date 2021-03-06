/*
 * File Name: KeyValue.java
 * Copyright: Copyright 2014-2014 CETC52 CETITI All Rights Reserved.
 * Description: 
 * Author: Wuwuhao
 * Create Date: 2016-11-15

 * Modifier: Wuwuhao
 * Modify Date: 2016-11-15
 * Bugzilla Id: 
 * Modify Content: 
 */
package com.cetiti.dsp.model;

/**
 * 〈一句话功能简述〉
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-11-15
 * @see       
 * @since     DSPlite V0.2.0
 */

public class KeyValue<K, V> {
	
	private K key;
	private V value;
	
	
	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}
	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}
	
	/**
	 * 〈一句话功能简述〉
	 * 
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "KeyValue [key=" + key + ", value=" + value + "]";
	}

}
