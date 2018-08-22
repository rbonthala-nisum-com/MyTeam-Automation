package com.mytime.initialization;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.mytime.applicationdata.ApplicationDataCarrier;

public class WebDriverInitialization {
	
	protected WebDriver driver;
	
	public void setWebDriver(WebDriver driver){
		this.driver= driver;
	}
	
	public WebDriver getWebDriver(){
		return driver;
	}
	
	@BeforeClass(alwaysRun = true)
	public void launchBrowser(ITestContext context)  {
		BrowserSelectionAndCapabilities browser = new BrowserSelectionAndCapabilities();
		driver = browser.launchSpecifiedBrowser(ApplicationDataCarrier.getInstance().toGetGivenProperty("browserName"), context);
	}
	
	@AfterClass( alwaysRun = true)
	public void closeBrowser()  {
		if (driver != null) {
			driver.quit();
		}
	}

}
