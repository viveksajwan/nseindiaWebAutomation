package com.firstorg.nseindia.reports;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.firstorg.nseindia.utility.TestDataJsonParser;

public class ExtentManager {

	/** The extent. */
	private static ExtentReports extent;

	/** The folder name. */
	public static String folderName;

	/**
	 * Creates the instance.
	 *
	 * @return the extent reports
	 */
	public static ExtentReports createInstance() {
		String fileName = getReportName();
		folderName = getFolderName();
		String directory = System.getProperty("user.dir") + "/test-reports/" + folderName + "/HTMLReports/";
		new File(directory).mkdirs();
		String path = directory + fileName;
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("NSEINDIA Automation Reports");
		htmlReporter.config().setReportName("NSEINDIA Automation Test Reports");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "NSEINDIA");
		extent.setSystemInfo("Browser", TestDataJsonParser.getTestData("$.driver-config.driver.browser"));
		extent.attachReporter(htmlReporter);

		return extent;
	}

	/**
	 * Gets the report name.
	 *
	 * @return the report name
	 */
	public static String getReportName() {
		Date date = new Date();
		String fileName = "Report_" + date.toString().replace(":", "_").replace(",", "_") + ".html";
		return fileName;
	}

	/**
	 * Gets the folder name.
	 *
	 * @return the folder name
	 */
	public static String getFolderName() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String folderName = "Report_" + formatter.format(date);
		return folderName;

	}

}
