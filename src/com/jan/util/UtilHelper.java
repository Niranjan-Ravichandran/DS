package com.jan.util;

import com.jan.constants.GenericConstants;
import com.jan.exception.DataStoreException;

public class UtilHelper {
	public static String optimiseKey(String key) throws DataStoreException {
		if(key==null || GenericConstants.EMPTY_STRING.equalsIgnoreCase(key)) {
			throw new DataStoreException(ErrorMessage.getErrorMessage("E114"));
		}
		return key.substring(0, Math.min(key.length(), GenericConstants.KEY_CAP_LIMIT));
	}
}


