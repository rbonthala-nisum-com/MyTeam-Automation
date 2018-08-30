package com.mytime.testsuite;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.applicationpages.HomePage;
import com.mytime.applicationpages.WelcomePage;
import com.nisum.automation.components.WebDriverInitialization;
import com.nisum.automation.util.ReadProperties;

/**
 * This will contain all test cases belongs to myTime
 */
public class MyTimeTestSuite extends WebDriverInitialization{
	
	Logger log;
	HomePage homePage;
	WelcomePage welcomePage;

	@BeforeMethod
	public void setUp(){
		log = Logger.getLogger(getClass());
		welcomePage = new WelcomePage(getDriver());
		WelcomePage.navigateToMyTimeApplicationURL(ReadProperties.getInstance().toGetGivenProperty("myTimeApplicationURL"));
		log.info("Before Method");
	}
	
	//Test case 001 - To login into myTime application.
	@Test(timeOut=120000)
	public void testcase_001_myTimeApplication_LoginTest()
	{
		welcomePage.clickOnSignInWithGoogleButton();
	}
	
	@AfterMethod
	public void tearDown(){
//		getWebDriver().close();
		
	}	

}
