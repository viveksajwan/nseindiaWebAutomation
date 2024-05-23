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
import com.firstorg.nseindia.utility.Utils;
import com.firstorg.nseindia.utility.WaitClass;

public class AllReportsPage {
	
	/** The driver. */
	WebDriver driver;

	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AllReportsPage.class);
	
	/**The utils class*/
	Utils utils;
	
	/*
	*//** The PropertConfig Class */
	
	 PropertyConfig propsloc=new PropertyConfig(TestDataJsonParser.getTestData("$.driver-config.testProperty.locators.fileName"),
	 TestDataJsonParser.getTestData("$.driver-config.testProperty.locators.filePath"));
	
	 /** Declaration of WebElements of Page */
	 WebElement allRprtHdng;
	 WebElement eqtySmeLstItm;
	  
	 String dataVltltyReport="";
	 String shrtSlngreport="";
	
	 
	/**
	 * Instantiates a new all reports page.
	 *
	 * @param driver the driver
	 */
	 
	public AllReportsPage(WebDriver driver) {		
		try {
			this.driver = driver;
			PageFactory.initElements(driver, this);			
		} 
		catch (Exception e) {
			logger.info(e);
		}
		
	}
	
	/**
	 * Gets the Page title.
	 *
	 * @return the page title
	 */
	// Get title of all reports Page
	public String getPageTitle() {
		logger.info("Get all reports page Title text and return.");
		return driver.getTitle();
	}
	
	public void equitiesLabelIsDisplayed() {

		try {
			Boolean allRprtsLblVisible=null;
			Boolean eqtySmeLstVisible=null;
			logger.info("Validating all report page visiblity");
			logger.info("Initialized web elements");
			allRprtHdng=Library.findElementByXpath(propsloc.getPropertyValue("AllReportsPage_AllReports_Heading"));
			eqtySmeLstItm=Library.findElementByXpath(propsloc.getPropertyValue("AllReportsPage_EquitiesAndSME_ListItem"));
			//Validate All Reports page
			WaitClass.explicitlyWait(allRprtHdng);
			allRprtsLblVisible = Library.isWebElementDisplayed(allRprtHdng);
			if (allRprtsLblVisible.TRUE) {
				Thread.sleep(1000);
				ExtentTestManager.getTest().log(Status.INFO, "System is able to verify that the All Reports page is visible");
				logger.info("System is able to verify that the All Reports page is visible");
			} else {
				logger.error("System is not on all reports page.");
				ExtentTestManager.getTest().log(Status.FAIL, "System is not on all reports page.");
				Assert.fail("System is not able to validate all reports page.");
			}
			//Validate Equities & SME label
			WaitClass.explicitlyWait(eqtySmeLstItm);
			eqtySmeLstVisible = Library.isWebElementDisplayed(eqtySmeLstItm);
			if (eqtySmeLstVisible.TRUE) {
				Thread.sleep(1000);
				ExtentTestManager.getTest().log(Status.INFO, "System is able to verify that the Equity & SME label is visible.");
				logger.info("System is able to verify that the Equity & SME label is visible.");
			} else {
				logger.error("System is not able to verify Equity & SME label on page.");
				ExtentTestManager.getTest().log(Status.FAIL, "System is not able to verify Equity & SME label on page.");
				Assert.fail("System is not able to verify Equity & SME label on page.");
			}
		} catch (Exception ex1) {

			ExtentTestManager.getTest().log(Status.FAIL, "Error during equitiesLabelIsDisplayed Execution");
			Assert.fail("Error during equitiesLabelIsDisplayed Execution, exception: " + "<br>"
					+ ex1.getMessage().replace(",", "<br>"));
			logger.error(ex1);
		}

	}
	
	public void downloadBothReports() {

		try {
			int reportCount=2;
			Boolean reportElementVisible=null;
			WebElement reportElement=null;
			WebElement reportDownloadElement=null;
			WebElement reportNameElement=null;
			String reportName="";
			utils=new Utils();
			logger.info("About to download both reports");
			logger.info("Initialized web elements");
			logger.info("Initializing Report download loop for two iterations");
			ExtentTestManager.getTest().log(Status.INFO, "Initializing Report download loop for two iterations.");
			for(int i=1;i<=reportCount;i++) {
				
				logger.info("Iteration for downloading report- {}", i);
				ExtentTestManager.getTest().log(Status.INFO, "Iteration for downloading report- "+ i);
				if(i==1){
					//Data Volatility Report
					reportElement=Library.findElementByXpath(propsloc.getPropertyValue("AllReportsPage_DailyVolatility_LabelItem"));
					reportDownloadElement=Library.findElementByXpath(propsloc.getPropertyValue("AllReportsPage_DailyVolatilityDownload_Button"));
					reportNameElement=Library.findElementByXpath(propsloc.getPropertyValue("AllReportsPage_DailyVolatilityReport_Label"));
					
					logger.info("Initialized Daily Volatility Report for this iteration");
					ExtentTestManager.getTest().log(Status.INFO, "Initialized Daily Volatility Report for this iteration");
				}else {
					//Short Selling Report
					reportElement=Library.findElementByXpath(propsloc.getPropertyValue("AllReportsPage_ShortSelling_LabelItem"));
					reportDownloadElement=Library.findElementByXpath(propsloc.getPropertyValue("AllReportsPage_ShortSellingDownload_Button"));
					reportNameElement=Library.findElementByXpath(propsloc.getPropertyValue("AllReportsPage_ShortSellingReport_Label"));
					
					logger.info("Initialized Short Selling Report for this iteration");
					ExtentTestManager.getTest().log(Status.INFO, "Initialized Short Selling Report for this iteration");
				}
				
				//scroll to element
				Thread.sleep(5000);
				logger.info("Scrolling to  Report element");
				ExtentTestManager.getTest().log(Status.INFO, "Scrolling to  Report element");
				WaitClass.explicitlyWait(reportElement);
				utils.scrollToElement(driver, reportElement);
				Thread.sleep(2000);
				
				//verify if the element is displayed & click on it
				reportElementVisible = Library.isWebElementDisplayed(reportElement);
				if (reportElementVisible.TRUE) {
					Thread.sleep(1000);
					Library.clickElement(reportElement, "click");
					ExtentTestManager.getTest().log(Status.INFO, "System is able to verify that the report element is visible");
					logger.info("System is able to verify that the report element is visible");
				} else {
					logger.error("System is not able to verify report element visibility.");
					ExtentTestManager.getTest().log(Status.FAIL, "System is not able to verify report element visibility.");
					Assert.fail("System is not able to verify report element visibility.");
				}
				
				//get name of report files to be downloaded
				reportName=Library.getText(reportNameElement);
				logger.info("Report Name- {}", reportName);
				ExtentTestManager.getTest().log(Status.INFO, "Report Name-"+ reportName);
				if(i==1) {
					
					dataVltltyReport=reportName;
				}else {
					
					shrtSlngreport=reportName;
				}
				
				
				//click on download report button
				Thread.sleep(2000);
				Library.clickElement(reportDownloadElement, "click");
				logger.info("Clicked on download report button for iteration- {}", i);
				ExtentTestManager.getTest().log(Status.INFO, "Clicked on download report button for iteration- "+ i);
				Thread.sleep(4000);
				Library.clickElement(reportElement, "click");
			}
			
			logger.info("Both the reports should have been downloaded locally now.");
			ExtentTestManager.getTest().log(Status.INFO, "Both the reports should have been downloaded locally now.");
		} catch (Exception ex2) {
			
			ExtentTestManager.getTest().log(Status.FAIL, "Error during downloadBothReports Execution");
			Assert.fail("Error during downloadBothReports Execution, exception: " + "<br>"
					+ ex2.getMessage().replace(",", "<br>"));
			logger.error(ex2);

		}

	}
	
	public void validateReports() {

		try {
			Boolean fileExists=null;
			Boolean fileNotBlank=null;
			logger.info("About to validate both reports exists & blank or not");
				
			//Downloaded files exist & if blank or not
			
			
			////Data Volatility Report
			fileExists=Library.verifyFileFromDownloadLib(dataVltltyReport);
			fileNotBlank=Library.isCSVFileNotEmpty(dataVltltyReport);
			Assert.assertTrue(fileExists, dataVltltyReport+" Report File do not exists in Downloads folder");
			Assert.assertTrue(fileNotBlank, dataVltltyReport+" Report File is blank");
			logger.info("Report File exists in Downloads folder and is not blank- {}",dataVltltyReport);
			ExtentTestManager.getTest().log(Status.INFO, "Report File exists in Downloads folder and is not blank- "+dataVltltyReport);
			
			////Short Selling Report
			fileExists=Library.verifyFileFromDownloadLib(shrtSlngreport);
			fileNotBlank=Library.isCSVFileNotEmpty(shrtSlngreport);
			Assert.assertTrue(fileExists, shrtSlngreport+" Report File do not exists in Downloads folder");
			Assert.assertTrue(fileNotBlank, shrtSlngreport+" Report File is blank");
			logger.info("Report File exists in Downloads folder and is not blank- {}",shrtSlngreport);
			ExtentTestManager.getTest().log(Status.INFO, "Report File exists in Downloads folder and is not blank- "+shrtSlngreport);
			

		} catch (Exception ex3) {

		}

	}

}
