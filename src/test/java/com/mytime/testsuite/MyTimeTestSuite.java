package com.mytime.testsuite;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.applicationpages.HomePage;
import com.mytime.applicationpages.WelcomePage;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.components.TimeOutMethods;
import com.nisum.qa.automation.util.PropertiesUtils;

/**
 * This will contain all test cases belongs to myTime
 */
public class MyTimeTestSuite extends WebDriverInitialization {

	Logger log;
	HomePage homePage;
	WelcomePage welcomePage;

	@BeforeMethod
	public void setUp() {
		log = Logger.getLogger(getClass());
		welcomePage = new WelcomePage(getDriver());
		WelcomePage.navigateToMyTimeApplicationURL(PropertiesUtils.getInstance()
				.readPropertyValue(MyTeamUtils.getPropertiesFilePath(), "myTimeApplicationURL"));
		log.info("Before Method");
	}

	@Test(timeOut = 120000)
	public void loginTest() {
		homePage = new HomePage(getDriver());
		String parentWindow = driver.getWindowHandle();
		welcomePage.clickOnSignInWithGoogleButton();
		TimeOutMethods.sleepInSeconds(10);
		welcomePage.enterUserCredentials(driver, parentWindow,
				PropertiesUtils.getInstance().readPropertyValue(MyTeamUtils.getPropertiesFilePath(), "username"),
				PropertiesUtils.getInstance().readPropertyValue(MyTeamUtils.getPropertiesFilePath(), "password"));
		homePage.clickOnLoginAfterCredntialsButton();
		driver.switchTo().window(parentWindow);
		TimeOutMethods.implicitWaitForGivenTime(60);
	}

	@AfterMethod
	public void tearDown() {
		// getWebDriver().close();
	}

}
