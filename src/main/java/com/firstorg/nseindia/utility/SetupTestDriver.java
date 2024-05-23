package com.firstorg.nseindia.utility;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SetupTestDriver {
	
	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SetupTestDriver.class);

	/**
	 * Gets the driver.
	 *
	 * @param browser         the browser
	 * @param os              the os
	 * @return the driver
	 * @throws Exception the exception
	 */
	public static synchronized WebDriver getDriver(String browser, String os) throws Exception {

		WebDriver driver = null;

		Platform platform = Platform.fromString(os.toUpperCase());
		logger.info("Browser in Test- {}",browser);
		if ("Firefox".equalsIgnoreCase(browser) || "FF".equalsIgnoreCase(browser)) {
			return driver = new FirefoxDriver();  // Firefox Window should open
		} else if ("Chrome".equalsIgnoreCase(browser) || "CH".equalsIgnoreCase(browser)) {
			return driver = new ChromeDriver(); // Chrome Window should open
		} else if ("Edge".equalsIgnoreCase(browser) || "ED".equalsIgnoreCase(browser)) {
	        return driver = new EdgeDriver();  // Edge Window should open
	    }

		return driver;
	}

}
