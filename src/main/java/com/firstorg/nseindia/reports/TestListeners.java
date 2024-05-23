package com.firstorg.nseindia.reports;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.firstorg.nseindia.test.TestBaseClass;

public class TestListeners implements ITestListener{
	
	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestListeners.class);

	/*
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	public void onTestStart(ITestResult result) {
		String className = result.getTestClass().getName();
		String[] classArray = className.split("\\.");

		ExtentTestManager.startTest(classArray[classArray.length - 1] + " :: " + result.getMethod().getMethodName());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	public void onTestSuccess(ITestResult result) {
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Successful</b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		ExtentTestManager.getTest().log(Status.PASS, markup);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	public void onTestFailure(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String exceptionHeading = result.getThrowable().getMessage();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());

		ExtentTestManager.getTest()
				.fail("<details><summary><b><font color=red>" + "Execption Occured, click to see the details:"
						+ "</font></b></summary>" + exceptionHeading + "<br>" + exceptionMessage.replace(",", "<br>")
						+ "</details> \n");
		
		WebDriver driver = ((TestBaseClass) result.getInstance()).driver;
		String path = takeScreenshot(driver, result.getMethod().getMethodName());
		try {

			ExtentTestManager.getTest().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		} catch (Exception e) {
			ExtentTestManager.getTest().fail("Test Failed, Can not attach the Screenshot");
			logger.error(e);
		}

		String logText = "<b>Test Method " + methodName + " Failed</b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
		ExtentTestManager.getTest().log(Status.FAIL, markup);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
		ExtentTestManager.getTest().log(Status.SKIP, markup);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.
	 * ITestResult)
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("Test Failed But Within Success Percentage");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	public void onStart(ITestContext context) {

		logger.info("*** Test Suite " + context.getName() + " started ***");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	public void onFinish(ITestContext context) {
		logger.info(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();

	}

	/**
	 * Take screenshot.
	 *
	 * @param driver     the driver
	 * @param methodName the method name
	 * @return the string
	 */
	public String takeScreenshot(WebDriver driver, String methodName) {
		String fileName = getScreenshotName(methodName);
		String folderName = ExtentManager.folderName;
		String directory = System.getProperty("user.dir") + "/test-reports/" + folderName + "/Screenshots/";

		new File(directory).mkdirs();
		String path = directory + fileName;
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			logger.info("***********************");
			logger.info("Screenshot stored at : " + path);
			logger.info("***********************");
		} catch (Exception e) {
			logger.error(e);
		}

		return path;
	}

	/**
	 * Gets the screenshot name.
	 *
	 * @param methodName the method name
	 * @return the screenshot name
	 */
	public static String getScreenshotName(String methodName) {
		Date date = new Date();
		String fileName = methodName + "_" + date.toString().replace(":", "_").replace(",", "_") + ".png";
		return fileName;
	}

}
