package com.jan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;

import org.json.simple.JSONObject;

import com.jan.exception.DataStoreException;

public class Util {
	/**
	 * This method is used to read the data store and return as json
	 * 
	 * @param file
	 * @return
	 */
	private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public static Properties readDataStore(String file) throws DataStoreException {
		Properties properties = new Properties();
		try (FileReader fileReader = new FileReader(file);) {
			properties.load(fileReader);
		} catch (Exception ex) {
			LoggerUtility.getInstance().log(Level.SEVERE, "Error", ex);
			throw new DataStoreException(ErrorMessage.getErrorMessage("E111"));
		}
		return properties;
	}

	public static boolean checkIfKeyNotAlreadyExists(String filePath, String key, JSONObject json)
			throws DataStoreException, IOException {
		// TODO Auto-generated method stub
		boolean isExistsAlready = false;
		Properties properties = new Properties();
		readWriteLock.readLock().lock();
		try (FileInputStream fileInputStream = new FileInputStream(filePath);) {
			properties.load(fileInputStream);
		} finally {
			readWriteLock.readLock().unlock();
		}
		if (null != properties.get(key)) {
			throw new DataStoreException(ErrorMessage.getErrorMessage("E107").replace("{{1}}", key));
		} else {
			isExistsAlready = true;
		}
		return isExistsAlready;
	}

	public static Properties delete(String filePath, String key) throws DataStoreException {
		// TODO Auto-generated method stub
		String message = "";
		Properties properties = new Properties();
		try {
			readWriteLock.readLock().lock();
			properties.load(new FileInputStream(filePath));
			// remove
			message = (String) properties.remove(key);
			if (null == message) {
				throw new DataStoreException(ErrorMessage.getErrorMessage("E106"));
			}
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			LoggerUtility.getInstance().log(Level.SEVERE, "Error", e);
			throw new DataStoreException(ErrorMessage.getErrorMessage("E108"));

		}finally {
			readWriteLock.readLock().unlock();
		}
		return properties;
	}

	public static boolean write(String filePath, Properties properties, boolean isAppend)
			throws DataStoreException, IOException {
		boolean isWriteSuccess = false;
		OutputStream accessFile = null;
		try {
			readWriteLock.writeLock().lock();
			accessFile = new FileOutputStream(filePath, isAppend);
			properties.store(accessFile, "");
			isWriteSuccess = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			LoggerUtility.getInstance().log(Level.SEVERE, "Error", e);
			throw new DataStoreException(ErrorMessage.getErrorMessage("E108"));
		} finally {
			if (accessFile != null) {
				accessFile.close();
			}
			readWriteLock.writeLock().unlock();
		}
		return isWriteSuccess;
	}

	public static boolean createFile(String filePath) throws DataStoreException {
		boolean isFileCreated = false;
		try {
			File f = new File(filePath);
			if (f.isDirectory() && f.exists()) {
				new FileOutputStream(filePath + "/default.properties", true).close();
				isFileCreated = true;
			} else {
				throw new DataStoreException(ErrorMessage.getErrorMessage("E109"));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			LoggerUtility.getInstance().log(Level.SEVERE, "Error", e);
			throw new DataStoreException(ErrorMessage.getErrorMessage("E101"));
		}
		return isFileCreated;
	}

	public static String formatFilePath(String filePath) {
		return filePath + "\\default.properties";
	}
}
