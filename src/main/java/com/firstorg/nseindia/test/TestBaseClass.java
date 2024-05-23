package com.firstorg.nseindia.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.firstorg.nseindia.utility.Library;
import com.firstorg.nseindia.utility.SetupTestDriver;
import com.firstorg.nseindia.utility.TestDataJsonParser;

public class TestBaseClass {

	/** The driver. */
	public static WebDriver driver;
	/**
	 * Sets the up.
	 *
	 * @param browser the new up
	 * @throws Exception the exception
	 */
	String testEnv = "";
	// Getting Current Working directory
	public static String cDir = System.getProperty("user.dir");

	@BeforeTest
	@Parameters({"browserName"})
	public void setUp(@Optional() String browser) throws Exception {
		if (browser == null) {
			browser = TestDataJsonParser.getTestData("$.driver-config.driver.browser");
		}

		String osName = TestDataJsonParser.getTestData("$.driver-config.driver.os").toLowerCase();

		if (osName != null && ("windows").equals(osName)) {
			driver = SetupTestDriver.getDriver(browser, TestDataJsonParser.getTestData("$.driver-config.driver.os"));

		} else if (osName != null && ("linux").equals(osName)) {

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("window-size=1920,1080");
			driver = new ChromeDriver(chromeOptions);
		}
		String testEnvVar = TestDataJsonParser.getTestData("$.driver-config.testEnvironment").toLowerCase();

		if (testEnvVar.equals("qa")) {
			testEnv = "qa";
		} else if (testEnvVar.equals("uat")) {
			testEnv = "uat";
		} else if (testEnvVar.equals("prod")) {
			testEnv = "prod";
		}
		Library.openUrl(TestDataJsonParser.getTestData("$.environment-config." + testEnv + ".url"));
		Library.maximaizeWindow();
	}

	@AfterTest
	public void tearDown() {
		// Quit or close the WebDriver instance
		if (driver != null) {
			driver.quit();
		}
	}

}
