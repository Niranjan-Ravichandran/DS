package com.jan.test;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.jan.bean.DataStore;
import com.jan.constants.GenericConstants;
import com.jan.exception.DataStoreException;
import com.jan.util.SuccessMessage;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DSTest {
	public DataStore dataStore;
	public JSONObject  jsonObject=new JSONObject();
	public String key="";
	
	protected void setUp() throws DataStoreException {
		dataStore=new DataStore();
		key="key11";
		String json_key="k1";
		String json_value="v1";
		jsonObject.put(json_key, json_value);
	}
	@Test
	public void create() throws DataStoreException {
		setUp();
		String message=dataStore.create(key, jsonObject);
		org.junit.Assert.assertEquals(message, SuccessMessage.getSuccessMessage("S102").replace(GenericConstants.REPLACE_CHAR_1, key));
	}
	@Test
	public void read() throws DataStoreException {
		setUp();
		JSONObject json=dataStore.read(key);
		org.junit.Assert.assertNotNull(json);
	}
	@Test
	public void delete() throws DataStoreException {
		setUp();
		String message=dataStore.delete(key);
		org.junit.Assert.assertEquals(message, SuccessMessage.getSuccessMessage("S103"));
	}
}
