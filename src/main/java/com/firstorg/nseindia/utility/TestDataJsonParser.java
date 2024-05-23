package com.firstorg.nseindia.utility;

import java.io.File;

import org.apache.logging.log4j.LogManager;

import com.jayway.jsonpath.JsonPath;

public class TestDataJsonParser {

	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestDataJsonParser.class);

	/** The json file. */
	public static File jsonFile = new File(
			"../nseindiaWebAutomation/src/main/resources/config/nseindia-config-data.json");

	/**
	 * Gets the test data.
	 *
	 * @param jsonPath the json path
	 * @return the test data
	 */
	public static String getTestData(String jsonPath) {
		String testDataProperty = null;
		try {
			testDataProperty = JsonPath.read(jsonFile, jsonPath).toString();

		} catch (Exception e) {
			logger.error(e);
		}

		return testDataProperty;
	}

}
