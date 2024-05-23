package com.firstorg.nseindia.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;

import com.firstorg.nseindia.test.TestBaseClass;

public class PropertyConfig {

	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PropertyConfig.class);
	
	/** The properties. */
	public final Properties properties = new Properties();
	
	/** The Constant SINGLETON. */
	//public static final ConfigParams SINGLETON = new ConfigParams("");
	
	/** The buffer reader. */
	BufferedReader bufferReader = null;
	
	/** The file. */
	String file="";
	
	// Getting Current directory
	/**
	 * Instantiates a new config params.
	 */
	//Constructor
	public PropertyConfig(String propertyFileName, String propertFilePath) {
		file = (TestBaseClass.cDir+propertFilePath+propertyFileName+".properties");
	//	System.out.println(file);
		load(file);

	}

	/**
	 * Load.
	 *
	 * @param fileName the file name
	 */
	public void load(String fileName) {
		try {
			bufferReader = new BufferedReader(new FileReader(fileName));
			properties.load(bufferReader);
			
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * Gets the property value.
	 *
	 * @return the value
	 */
	
	//Get property value from property file
	public String getPropertyValue(String propertyPath)
	{
		String testPropValue=null;
		
		try {
			testPropValue=properties.getProperty(propertyPath);
			
		} catch (Exception e) {
			logger.error(e);
		}
		return testPropValue;
		
	}

	
}
