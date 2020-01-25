package com.jan.bean;

import java.util.logging.Level;

import org.json.simple.JSONObject;

import com.jan.exception.DataStoreException;
import com.jan.repository.DsRepository;
import com.jan.service.DsService;
import com.jan.util.LoggerUtility;
import com.jan.util.SuccessMessage;
import com.jan.util.Util;
import com.jan.util.UtilHelper;

public class DataStore implements DsRepository{
private String filePath;

DsService dsService=new DsService();
public  DataStore() throws DataStoreException{
	this.setFilePath("resources");
	createFile();
	
}
public DataStore(String filePath) throws DataStoreException{
	this.setFilePath(filePath);
	createFile();
}
public String getFilePath() {
	return filePath;
}
public void setFilePath(String filePath) {
	this.filePath = filePath;
}

public void createFile() throws DataStoreException {
	if(Util.createFile(filePath)) {
		this.filePath=Util.formatFilePath(filePath);
		System.out.println(SuccessMessage.getSuccessMessage("S100").replace("{{1}}", getFilePath()));
		LoggerUtility.getInstance().log(Level.INFO, SuccessMessage.getSuccessMessage("S100").replace("{{1}}", getFilePath()));
	}
}
@Override
public String create(String key, JSONObject json) throws DataStoreException  {
	// TODO Auto-generated method stub
	key=UtilHelper.optimiseKey(key);
	return dsService.create(this.getFilePath(),key,json);
}
@Override
public JSONObject read(String key) throws DataStoreException {
	// TODO Auto-generated method stub
	key=UtilHelper.optimiseKey(key);	
	return dsService.read(getFilePath(), key);

}
@Override
public String delete(String key) throws DataStoreException {
	// TODO Auto-generated method stub
	key=UtilHelper.optimiseKey(key);
	return dsService.delete(getFilePath(), key);
}

}
