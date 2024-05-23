package com.firstorg.nseindia.utility;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.firstorg.nseindia.test.TestBaseClass;

public class Library extends TestBaseClass {

	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Library.class);

	/**
	 * Maximaize window.
	 */
	public static synchronized void maximaizeWindow() {
		logger.info("Maximizing the browser window");
		driver.manage().window().maximize();
	}

	/**
	 * Open browser.
	 *
	 * @param url the url
	 * @throws Exception the exception
	 */
	public static synchronized void openUrl(String url) {
		logger.info("Navigating to URL {}", url);
		try {
			driver.get(url);
			WaitClass.untilJqueryIsDone(driver);
			logger.info("Opened URL");
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 * @throws Exception the exception
	 */
	public static synchronized String getUrl() {
		logger.info("User is getting the current url");
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 * @throws Exception the exception
	 */
	public static synchronized String getTitle() {
		logger.info("Driver is about to get the title information");
		try {
			return driver.getTitle();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Send keys.
	 *
	 * @param element the element
	 * @param text    the text
	 * @throws Exception the exception
	 */
	public static synchronized void SendKeys(WebElement element, String text) {
		logger.info("User is sending text");
		try {
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Click element.
	 *
	 * @param element   the element
	 * @param clickType the click type
	 * @throws Exception the exception
	 */
	public static synchronized void clickElement(WebElement element, String clickType) {

		logger.info("User is about to click on element");
		try {
			if (element != null) {
				if ("click".equalsIgnoreCase(clickType)) {
					element.click();
				} else if ("doubleclick".equalsIgnoreCase(clickType)) {

					Actions actions = new Actions(driver);
					actions.doubleClick(element).perform();

				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Gets the text.
	 *
	 * @param element the element
	 * @return the text
	 * @throws Exception the exception
	 */
	public static synchronized String getText(WebElement element) {
		logger.info("User is about to get the text from the element");
		try {
			return element.getText().trim();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Checks if is web element displayed.
	 *
	 * @param element the element
	 * @return true, if is web element displayed
	 * @throws Exception the exception
	 */
	public static synchronized boolean isWebElementDisplayed(WebElement element) {
		logger.info("User is verifying the visibility of element");
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * Closet window.
	 *
	 * @throws Exception the exception
	 */
	public static synchronized void closetWindow() {
		try {
			driver.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Find Element.
	 */
	public static WebElement findElementByXpath(String path) {
		try {
			logger.info("User is about to identifying the webelement");
			return driver.findElement(By.xpath(path));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public static synchronized void refreshPage() {
		try {
			logger.info("User is about to refresh the Page");
			driver.navigate().refresh();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}
	
	/**
	 * To verify the file from downloads
	 */	
	public static boolean verifyFileFromDownloadLib(String filename) {
		try {			
			logger.info("User is about to verify the file from download- {}", filename);
			String userHome = System.getProperty("user.home");
			String downloadFolderPath = userHome + File.separator + "Downloads";
			File downloadedFile = new File(downloadFolderPath + File.separator + filename); 
			boolean fileExists = downloadedFile.exists();			
			return fileExists;			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			return (Boolean) null;
		}
		
	}
	
	/**
	 * To verify the file from downloads
	 */	
	public static boolean isCSVFileNotEmpty(String filename) {
		try {			
			logger.info("User is about to verify the file is blank or not from download- {}", filename);
			String userHome = System.getProperty("user.home");
			String downloadFolderPath = userHome + File.separator + "Downloads";
			File downloadedFile = new File(downloadFolderPath + File.separator + filename); 
			// Get the size of the file
            long fileSize = Files.size(downloadedFile.toPath());
			return fileSize > 0;			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			return (Boolean) null;
		}
		
	}

}
