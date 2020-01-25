package com.jan.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jan.bean.DataStore;
import com.jan.constants.GenericConstants;
import com.jan.exception.DataStoreException;
import com.jan.util.ErrorMessage;
import com.jan.util.LoggerUtility;
 

public class DsApplication {
	static void print(String s) {
		System.out.println(s);
	}
	static void getKeyValuePairFromUser() throws DataStoreException  {
		Map<String,JSONObject> map=new HashMap<>();
		System.out.println("Enter number of key value");
		int noOfInputs=0;
		String input=GenericConstants.EMPTY_STRING;
		JSONParser jsonParser=new JSONParser();
		try(Scanner scanner=new Scanner(System.in);){
			noOfInputs=Integer.parseInt(scanner.nextLine());
			System.out.println("Enter key value pairs seperated by hyphen '-'");
			for(int i=0;i<noOfInputs;i++) {
				input=scanner.nextLine();
				try {
					//input=Validations.optimizeInput(input);
					if(map.containsKey(input.split("-")[0])) {
						throw new DataStoreException(ErrorMessage.getErrorMessage("E103"));
					}else {
						map.put(input.split("-")[0], (JSONObject) jsonParser.parse(input.split("-")[1]));
					}
				}catch(DataStoreException ds) {
					print(ds.getMessage());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new DataStoreException(ErrorMessage.getErrorMessage("E102"));
				}
				System.out.println("input string==>"+input);
			}
			
			System.out.println(map);
			
		}
	}
	static void create(DataStore dataStore) throws DataStoreException {
		JSONObject json1=new JSONObject();
		json1.put("key1", "value1");
		JSONObject json2=new JSONObject();
		json2.put("key1", "value1");
		dataStore.create("key4", json1 );
		dataStore.create("key2", json2);
	}
	static void read(DataStore dataStore) throws DataStoreException {
		JSONObject jsonObject=dataStore.read("key6");
		System.out.println(jsonObject);
	}
	static void delete(DataStore dataStore) throws DataStoreException {
		dataStore.delete("key4");
	}
public static void main(String[] args) throws DataStoreException, ParseException {
	try {
	DataStore  dataStore=new DataStore();
	//create(dataStore);
	//read(dataStore);
	//delete(dataStore);
	LoggerUtility.getInstance().log(Level.SEVERE, "msg");
	
	}catch(Exception e) {
		LoggerUtility.getInstance().log(Level.SEVERE, "Error", e);
		System.out.println(e.getMessage());
	}
}
}
