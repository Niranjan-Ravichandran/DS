package com.jan.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.jan.bean.DataStore;
public class LoggerUtility {
	private static final Logger LOGGER=Logger.getLogger("confLogger");
    private static final LogManager logManager = LogManager.getLogManager();
    
    static{
        try {
            logManager.readConfiguration(new FileInputStream("src/logging.properties"));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error in loading configuration",exception);
        }
    }
	public static Logger getInstance() {
		return LOGGER;
	}
	
}
