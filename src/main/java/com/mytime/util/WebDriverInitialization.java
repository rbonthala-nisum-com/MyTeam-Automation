package com.mytime.util;

import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.mytime.pages.WelcomePage;
import com.nisum.qa.automation.components.Browsers;
import com.nisum.qa.automation.db.MongoDbConnection;
import com.nisum.qa.automation.util.PropertiesUtils;

public class WebDriverInitialization {

	protected WebDriver driver;
	protected Logger log;
	WelcomePage welcomePage;
	
	private Properties properties = PropertiesUtils.getInstance().readPropertyValue(MyTeamUtils.getPropertiesFilePath());
	MongoDbConnection db = new MongoDbConnection();

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@BeforeClass(alwaysRun = true)
	public void launchBrowser(ITestContext context) {
		log = Logger.getLogger(getClass());	
		Browsers browser = new Browsers();
		driver = browser.launchSpecifiedBrowser(getProp().getProperty("browserName"),context);
		driver.manage().deleteAllCookies();
		welcomePage = new WelcomePage(driver);
		welcomePage.navigateToMyTimeApplicationURL(getProp().getProperty("myTimeApplicationURL"));
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	public Properties getProp() {
		return properties;
	}

	public void setProperty(Properties prop) {
		this.properties = prop;
	}
	
	public String getDbRecord(String hostName, String dbName, String tableName, Map<String, String> colNameValuePair)
	{
		String dbRecord = db.findRecord(hostName, dbName, tableName, colNameValuePair);
		//(getProp().getProperty("hostName"), getProp().getProperty("dbName"), getProp().getProperty("tableName"), getProp().getProperty("columnName"), getProp().getProperty("columnValue"));
		return dbRecord;
	}
}
