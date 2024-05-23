package com.firstorg.nseindia.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.firstorg.nseindia.reports.ExtentTestManager;
import com.firstorg.nseindia.utility.Library;
import com.firstorg.nseindia.utility.PropertyConfig;
import com.firstorg.nseindia.utility.TestDataJsonParser;
import com.firstorg.nseindia.utility.WaitClass;

public class HomePage {

	/** The driver. */
	WebDriver driver;

	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(HomePage.class);

	/*
	*//** The PropertConfig Class */

	PropertyConfig propsloc = new PropertyConfig(
			TestDataJsonParser.getTestData("$.driver-config.testProperty.locators.fileName"),
			TestDataJsonParser.getTestData("$.driver-config.testProperty.locators.filePath"));

	/** Declaration of WebElements of Page */
	WebElement homeLstItm;
	WebElement mrktDataLstItm;
	WebElement mrktDataHdng;
	WebElement mrktRprtLstItm;

	/**
	 * Instantiates a new home page.
	 *
	 * @param driver the driver
	 */

	public HomePage(WebDriver driver) {
		try {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		} catch (Exception e) {
			logger.info(e);
		}

	}

	/**
	 * Gets the Page title.
	 *
	 * @return the page title
	 */
	// Get title of Home Page
	public String getPageTitle() {
		logger.info("Get Home page Title text and return.");
		return driver.getTitle();
	}

	public void validateHomePage() {

		try {
			logger.info("Validating home page visiblity");
			logger.info("Initialize web element");
			homeLstItm = Library.findElementByXpath(propsloc.getPropertyValue("HomePage_Home_ListItem"));
			WaitClass.explicitlyWait(homeLstItm);
			Boolean homeVisible = Library.isWebElementDisplayed(homeLstItm);
			if (homeVisible.TRUE) {
				Thread.sleep(2000);
				ExtentTestManager.getTest().log(Status.INFO, "System is able to verify the home page visiblity");
				logger.info("System is able to verify the home page visiblity");
			} else {
				logger.error("System is not on home page.");
				ExtentTestManager.getTest().log(Status.FAIL, "System is not on home page.");
				Assert.fail("System is not able to validate home page.");
			}
		} catch (Exception ex1) {

			ExtentTestManager.getTest().log(Status.FAIL, "Error during validateHomePage Execution");
			Assert.fail("Error during validateHomePage Execution, exception: " + "<br>"
					+ ex1.getMessage().replace(",", "<br>"));
			logger.error(ex1);

		}

	}

	public void navigateToAllReports() {

		try {
			Boolean mrktDataLstVisible = null;
			Boolean mrktDataHdngVisible = null;
			Boolean mrktRprtVisible = null;
			logger.info("Navigating to all reports page");
			logger.info("Initialized web elements");
			mrktDataLstItm = Library.findElementByXpath(propsloc.getPropertyValue("HomePage_MarketData_ListItem"));
			mrktDataHdng = Library.findElementByXpath(propsloc.getPropertyValue("HomePage_GetMarketData_Heading"));
			mrktRprtLstItm = Library
					.findElementByXpath(propsloc.getPropertyValue("HomePage_DailyMarketReports_ListItem"));

			mrktDataLstVisible = Library.isWebElementDisplayed(mrktDataLstItm);

			if (mrktDataLstVisible.TRUE) {
				ExtentTestManager.getTest().log(Status.INFO, "Market Data list is displayed.");
				logger.info("Market Data list is displayed.");
				Library.clickElement(mrktDataLstItm, "click");
				ExtentTestManager.getTest().log(Status.INFO, "System has clicked on Market Data list item.");
				logger.info("System has clicked on Market Data list item.");
				Thread.sleep(3000);
				mrktDataHdngVisible = Library.isWebElementDisplayed(mrktDataHdng);
				if (mrktDataHdngVisible.TRUE) {
					ExtentTestManager.getTest().log(Status.INFO, "Get Daily Market heading is displayed.");
					logger.info("Get Daily Market heading is displayed.");
					Library.clickElement(mrktDataHdng, "click");
					ExtentTestManager.getTest().log(Status.INFO, "System has clicked on Get Daily Market heading.");
					logger.info("System has clicked on Get Daily Market heading.");
				} else {
					logger.error("Get Daily Market heading is not displayed.");
					ExtentTestManager.getTest().log(Status.FAIL, "Get Daily Market heading is not displayed.");
					Assert.fail("System is not able verify if Get Daily Market heading is displayed.");
				}
				
				Thread.sleep(5000);
				mrktRprtVisible = Library.isWebElementDisplayed(mrktRprtLstItm);

				if (mrktRprtVisible.TRUE) {
					ExtentTestManager.getTest().log(Status.INFO, "Daily Market report label is displayed.");
					logger.info("Daily Market report label is displayed.");
					Library.clickElement(mrktRprtLstItm, "click");
					ExtentTestManager.getTest().log(Status.INFO, "System has clicked on Daily Market report label.");
					logger.info("System has clicked on Daily Market report label.");
				} else {
					logger.error("Daily Market report label is not displayed.");
					ExtentTestManager.getTest().log(Status.FAIL, "Daily Market report label is not displayed.");
					Assert.fail("System is not able verify if Daily Market report is displayed.");
				}
			} else {
				logger.error("Market Data list item is not displayed.");
				ExtentTestManager.getTest().log(Status.FAIL, "Market Data list item is not displayed.");
				Assert.fail("Market Data list item is not displayed.");
			}

		} catch (Exception ex2) {

			ExtentTestManager.getTest().log(Status.FAIL, "Error during navigateToAllReports Execution");
			Assert.fail("Error during navigateToAllReports Execution, exception: " + "<br>"
					+ ex2.getMessage().replace(",", "<br>"));
			logger.error(ex2);

		}

	}

}
