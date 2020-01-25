package com.jan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ErrorMessage {
	private static Properties properties=new Properties();
static {
	try (FileInputStream fileInputStream=new FileInputStream("src/errormessages.properties");){
		properties.load(fileInputStream);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public  static String getErrorMessage(String key) {
	return properties.getProperty(key, "Error");
}
}
