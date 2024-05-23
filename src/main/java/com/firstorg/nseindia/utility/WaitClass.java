package com.firstorg.nseindia.utility;

import java.time.Duration;
import java.util.Set;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.firstorg.nseindia.test.TestBaseClass;

public class WaitClass extends TestBaseClass{

	/** The Constant logger. */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(WaitClass.class);
	
	/**String contant */
	private static final String IMPLWTSTR="$.driver-config.implicitlyWait";
	
	/**
	 * Until jquery is done.
	 *
	 * @param driver the driver
	 */
	public static void untilJqueryIsDone(WebDriver driver){
		logger.debug("Calling untilJqueryIsDone");
		untilJqueryIsDone(driver, Long.parseLong(TestDataJsonParser.getTestData(IMPLWTSTR)));
	}

	/**
	 * Until jquery is done.
	 *
	 * @param driver the driver
	 * @param timeoutInSeconds the timeout in seconds
	 */
	public static void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds){
		until(driver, d ->
		{
			Boolean isJqueryCallDone = (Boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
			if (Boolean.FALSE.equals(isJqueryCallDone)) logger.info("JQuery call is in Progress");
			return isJqueryCallDone;
		}, timeoutInSeconds);
	}

	/**
	 * Until page load complete.
	 *
	 * @param driver the driver
	 */
	public static void untilPageLoadComplete(WebDriver driver) {
		untilPageLoadComplete(driver, Long.parseLong(TestDataJsonParser.getTestData(IMPLWTSTR)));
	}

	/**
	 * Until page load complete.
	 *
	 * @param driver the driver
	 * @param timeoutInSeconds the timeout in seconds
	 */
	public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds){
		until(driver, d ->
		{
			Boolean isPageLoaded = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			if (Boolean.FALSE.equals(isPageLoaded)) logger.info("Document is loading");
			return isPageLoaded;
		}, timeoutInSeconds);
	}

	/**
	 * Until.
	 *
	 * @param driver the driver
	 * @param waitCondition the wait condition
	 */
	public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition){
		until(driver, waitCondition, Long.parseLong(TestDataJsonParser.getTestData(IMPLWTSTR)));
	}


	/**
	 * Until.
	 *
	 * @param driver the driver
	 * @param waitCondition the wait condition
	 * @param timeoutInSeconds the timeout in seconds
	 */
	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, long timeoutInSeconds){
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){
			logger.error(e);
		}          
	}

	
	
	/**
	 * Explicitly wait.
	 *
	 * @param webElement the web element
	 */
	public static void explicitlyWait(WebElement webElement) {
		logger.info("Waiting for visibility of web elements");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(TestDataJsonParser.getTestData(IMPLWTSTR))));
		wait.until(ExpectedConditions.visibilityOf(webElement));
		logger.info("System waited explicitly for the web element");
	}
	
	/**
	 * Wait for new window.
	 *
	 * @param driver the driver
	 * @param timeout the timeout
	 * @return true, if successful
	 */
	public static boolean waitForNewWindow(WebDriver driver, int timeout){
        boolean flag = false;
        int counter = 0;
        while(!flag){
            try {
                Set<String> winId = driver.getWindowHandles();
                if(winId.size() > 1){
                    flag = true;
                    return flag;
                }
                Thread.sleep(1000);
                counter++;
                if(counter > timeout){
                    return flag;
                }
            } catch (Exception e) {
                logger.error(e);
                Thread.currentThread().interrupt();
                return false;
                
            }
        }
        return flag;
    }
	
}
