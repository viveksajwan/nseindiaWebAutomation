package com.firstorg.nseindia.pageobjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	/** The driver. */
	private WebDriver driver;
	
	/** The home page. */
	private HomePage homePage;
	
	/** The all reports page. */
	private AllReportsPage allReportsPage;
	
	/**
	 * Instantiates a new page object manager.
	 *
	 * @param driver the driver
	 */
	public PageObjectManager(WebDriver driver) {
		this.driver=driver;
	}
	
	/**
	 * Gets the home page.
	 *
	 * @return the home page
	 */
	public HomePage getHomePage() {
		
		if(homePage==null) {
			homePage=new HomePage(driver);
		}
		return homePage;
	}
	
	/**
	 * Gets the all reports page.
	 *
	 * @return the all reports page
	 */
	public AllReportsPage getAllReportsPage() {
		
		if(allReportsPage==null) {
			allReportsPage=new AllReportsPage(driver);
		}
		return allReportsPage;
	}
	
	

}
