package com.jan.service;

import java.util.Properties;
import java.util.logging.Level;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jan.constants.GenericConstants;
import com.jan.exception.DataStoreException;
import com.jan.util.ErrorMessage;
import com.jan.util.LoggerUtility;
import com.jan.util.SuccessMessage;
import com.jan.util.Util;

public class DsService {

	public String create(String filePath,String key,JSONObject json) throws DataStoreException {
		String message="";
		try {
			if(Util.checkIfKeyNotAlreadyExists(filePath, key, json)) {
				Properties properties=new Properties();
				properties.put(key, json.toJSONString());
				Util.write(filePath, properties, true);
			}
			message=SuccessMessage.getSuccessMessage("S102").replace(GenericConstants.REPLACE_CHAR_1, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LoggerUtility.getInstance().log(Level.SEVERE, "Error", e);
			System.out.println(e.getMessage());
			message=e.getMessage();
			throw new DataStoreException(ErrorMessage.getErrorMessage("E110"));
		}
		return message;
	}
	
	public JSONObject read(String filePath,String key) throws  DataStoreException {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		try {
			Properties js=Util.readDataStore(filePath);
			String jsonValue=(String)js.getProperty(key);
			if(null==jsonValue) {
				throw new DataStoreException(ErrorMessage.getErrorMessage("E106").replace("{{1}}", key));
			}
			json=(JSONObject)new JSONParser().parse(jsonValue);
		}
		catch(Exception e) {
			LoggerUtility.getInstance().log(Level.SEVERE, "Error", e);
			System.out.println(e.getMessage());
			throw new DataStoreException(ErrorMessage.getErrorMessage("E113"));
		}
		return json;
	}

	
	public String delete(String filePath, String key) throws DataStoreException {
		// TODO Auto-generated method stub
		String message="";	
			try {
				Properties properties=	Util.delete(filePath, key);
				Util.write(filePath, properties, false);
				message=SuccessMessage.getSuccessMessage("S103");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LoggerUtility.getInstance().log(Level.SEVERE, "Error", e);
				message=e.getMessage();
				throw new DataStoreException(ErrorMessage.getErrorMessage("E112"));
			}
			return message;
		}

}
