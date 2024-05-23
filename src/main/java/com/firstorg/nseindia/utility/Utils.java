package com.firstorg.nseindia.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.firstorg.nseindia.pageobjects.AllReportsPage;
import com.firstorg.nseindia.reports.ExtentTestManager;

public class Utils {
	
	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Utils.class);
	
	/**
	 * Append date time.
	 *
	 * @param str1 the str 1
	 * @return the string
	 */
	public String appendDateTime(String str1) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String currentDT = dtf.format(now);
		ExtentTestManager.getTest().log(Status.INFO, "Current date and time.- " + currentDT);
		str1 = str1 + " " + currentDT;
		ExtentTestManager.getTest().log(Status.INFO, "String after appending current Date & Time.- " + str1);
		return str1;
	}
	
	/**
	 * @param driver
	 * @param element
	 */
	public static void scrollToElement(WebDriver driver, WebElement element) {
		try {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center', inline: 'center' });", element);
		}catch(Exception e) {
			
			logger.error(e);
		}
    }

}
