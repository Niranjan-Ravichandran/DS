package com.jan.repository;

import org.json.simple.JSONObject;

import com.jan.exception.DataStoreException;

public interface DsRepository {

String create(String key, JSONObject json) throws DataStoreException;
JSONObject read(String key)throws DataStoreException;
String delete(String key)throws DataStoreException;
}
