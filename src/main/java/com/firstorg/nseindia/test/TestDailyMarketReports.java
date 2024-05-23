package com.firstorg.nseindia.test;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.firstorg.nseindia.pageobjects.AllReportsPage;
import com.firstorg.nseindia.pageobjects.HomePage;
import com.firstorg.nseindia.pageobjects.PageObjectManager;
import com.firstorg.nseindia.reports.ExtentTestManager;

public class TestDailyMarketReports extends TestBaseClass{
	
	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestDailyMarketReports.class);

	/** The page object manager. */
	PageObjectManager pageObjectManager;
	
	/** The login page. */
	HomePage homePage;
	
	/** The all reports page. */
	AllReportsPage allReportsPage;
	
	/**
	 * Initialization.
	 */
	@BeforeClass
	public void initialization() {
		try {
			
			pageObjectManager = new PageObjectManager(driver);
			homePage = pageObjectManager.getHomePage();
			allReportsPage = pageObjectManager.getAllReportsPage();
			
		} catch (Exception e) {
			logger.error("Error during initialization");
			ExtentTestManager.getTest().log(Status.FAIL, "Error during Initialization");
			Assert.fail("Error during Initialization, exception: " + "<br>" + e.getMessage().replace(",", "<br>"));
			logger.error(e);
		}
	}
	
	/**
	 * Daily market reports validation through application.
	 *
	 * @throws Exception
	 */
	@Test
	public void dailyMarketReportsValidation() {
		
		try {
			//dailyMarketReportsValidation test started
			ExtentTestManager.getTest().log(Status.INFO, "Daily Market Reports Validation Test has been Started");
			logger.info("Daily Market Reports Validation Test has been Started");
			
			//Validate that system has navigated to nseindia home page
			ExtentTestManager.getTest().log(Status.INFO, "Validating that system has successfully navigated to home page");
			logger.info("Validating that system has successfully naigated to home page");
			homePage.validateHomePage();
			
			//Navigate to Daily Market Reports/All Reports page
			ExtentTestManager.getTest().log(Status.INFO, "System is navigating to Daily Market Reports/All Reports page");
			logger.info("System is navigating to Daily Market Reports/All Reports page");
			homePage.navigateToAllReports();
			
			//Verify Equities & SME label is displayed on All Reports page
			ExtentTestManager.getTest().log(Status.INFO, "System is verifying that Equities & SME label is displayed");
			logger.info("System is verifying that Equities & SME label is displayed");
			allReportsPage.equitiesLabelIsDisplayed();
			
			//Download Daily Volatility (CSV) and Short Selling (csv)
			ExtentTestManager.getTest().log(Status.INFO, "System is about to download both Daily Volatility and Short Selling reports");
			logger.info("System is about to download both Daily Volatility and Short Selling reports");
			allReportsPage.downloadBothReports();
			
			//Verify if the reports are blank or not
			ExtentTestManager.getTest().log(Status.INFO, "System is about to verify if downloaded reports are blank or not");
			logger.info("System is about to verify if downloaded reports are blank or not");
			allReportsPage.validateReports();
			
			
		}catch(Exception ex1) {
			
			ExtentTestManager.getTest().log(Status.FAIL, "Error during Execution");
			Assert.fail("Error during Execution, exception: " + "<br>" + ex1.getMessage().replace(",", "<br>"));
			logger.error(ex1);
			
		}
		
		
	}

}
