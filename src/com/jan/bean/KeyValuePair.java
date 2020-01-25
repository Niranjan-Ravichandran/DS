package com.jan.bean;

import org.json.simple.JSONObject;

public class KeyValuePair {

	private String key;
	private JSONObject value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public JSONObject getValue() {
		return value;
	}
	public void setValue(JSONObject value) {
		this.value = value;
	}
}
