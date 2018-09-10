package com.mytime.util;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.mytime.util.MyTeamUtils;
import com.nisum.qa.automation.components.Browsers;
import com.nisum.qa.automation.util.PropertiesUtils;

public class WebDriverInitialization {

	protected WebDriver driver;
	
	private Properties properties = PropertiesUtils.getInstance().readPropertyValue(MyTeamUtils.getPropertiesFilePath());

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@BeforeClass(alwaysRun = true)
	public void launchBrowser(ITestContext context) {
		Browsers browser = new Browsers();
		driver = browser.launchSpecifiedBrowser(getProperty().getProperty("browserName"),context);
		driver.manage().deleteAllCookies();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	public Properties getProperty() {
		return properties;
	}

	public void setProperty(Properties prop) {
		this.properties = prop;
	}
}
