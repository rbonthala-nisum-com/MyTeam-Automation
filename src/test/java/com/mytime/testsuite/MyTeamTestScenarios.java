package com.mytime.testsuite;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.pages.LoginPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamDbUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.components.Browsers;

public class MyTeamTestScenarios extends WebDriverInitialization {

	LoginPage login;
	WelcomePage welcomePage;
	MyTeamDbUtils dbUtils;
	Boolean result = false;

	@BeforeMethod
	public void setUp(ITestContext context) {
		log = Logger.getLogger(getClass());	
		Browsers browser = new Browsers();
		driver = browser.launchSpecifiedBrowser(getProp().getProperty("browserName"),context);
		driver.manage().deleteAllCookies();
		welcomePage = new WelcomePage(driver);
		welcomePage.navigateToMyTimeApplicationURL(getProp().getProperty("myTimeApplicationURL"));
		login = new LoginPage(getDriver());
		log.info("Before Method");
	}

	private void initLogin() {
		welcomePage = new WelcomePage(driver);
		String parentWindow = driver.getWindowHandle();
		welcomePage.clickOnSignInWithGoogleButton();
		welcomePage.sleepInSeconds(10);
		welcomePage.enterUserCredentials(login, parentWindow, getProp().getProperty("username"),
				getProp().getProperty("password"));
		// login.clickOnLoginConformationPopup(driver);
		driver.switchTo().window(parentWindow);
	}

	@Test(priority = 1)
	public void scenario1() {
		initLogin();
		System.out.println("frist scenario");
	}

	@Test(priority = 2)
	public void scenario2() {
		initLogin();
		System.out.println("second scenario");
	}
	

	@AfterMethod
	public void tearDown() {
		login.logout(driver);
		driver.manage().deleteAllCookies();
		if (driver != null) {
			driver.quit();
		}
	}

	
}
