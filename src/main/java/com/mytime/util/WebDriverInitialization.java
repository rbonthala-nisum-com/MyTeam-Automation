package com.mytime.util;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.mytime.util.MyTeamUtils;
import com.nisum.qa.automation.components.Browsers;
import com.nisum.qa.automation.util.PropertiesUtils;

public class WebDriverInitialization {

	protected WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@BeforeClass(alwaysRun = true)
	public void launchBrowser(ITestContext context) {
		Browsers browser = new Browsers();
		driver = browser.launchSpecifiedBrowser(PropertiesUtils.getInstance().readPropertyValue(MyTeamUtils.getPropertiesFilePath(), "browserName"),
				context);
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
