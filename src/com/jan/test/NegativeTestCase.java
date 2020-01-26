package com.jan.test;

import org.json.simple.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jan.bean.DataStore;
import com.jan.constants.GenericConstants;
import com.jan.exception.DataStoreException;
import com.jan.util.ErrorMessage;
import com.jan.util.SuccessMessage;

import junit.framework.Assert;

public class NegativeTestCase {
	public DataStore dataStore;
	public JSONObject  jsonObject=new JSONObject();
	public String key="";
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
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

		exceptionRule.expect(DataStoreException.class);
		String message=dataStore.create(key, jsonObject);
	}
	@Test
	public void read() throws DataStoreException {
		setUp();
		exceptionRule.expect(DataStoreException.class);
		JSONObject json=dataStore.read("key not present");
		org.junit.Assert.assertNotNull(json);
	}
	@Test
	public void delete() throws DataStoreException {
		setUp();
		exceptionRule.expect(DataStoreException.class);
		String message=dataStore.delete("key not present");
	}

}
