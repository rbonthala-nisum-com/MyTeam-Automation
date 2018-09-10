package com.mytime.testsuite;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.applicationpages.HomePage;
import com.mytime.applicationpages.LoginPage;
import com.mytime.applicationpages.ManageAccount_Page;
import com.mytime.applicationpages.ManageGroup_Page;
import com.mytime.applicationpages.WelcomePage;
import com.mytime.locators.LoginPageLocators;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.components.TimeOutMethods;
import com.nisum.qa.automation.util.ExcelUtils;

/**
 * This will contain all test cases belongs to myTime
 */
public class MyTimeTestSuite extends WebDriverInitialization {

	Logger log;
	HomePage homePage = new HomePage(getDriver());
	LoginPage login = new LoginPage(getDriver());
	WelcomePage welcomePage;
	ManageGroup_Page manageGroupPage = new ManageGroup_Page(getDriver());
	ManageAccount_Page manageAccountPage = new ManageAccount_Page(getDriver());
	ExcelUtils excelUtils = new ExcelUtils();

	@BeforeMethod
	public void setUp() {
		log = Logger.getLogger(getClass());
		welcomePage = new WelcomePage(getDriver());
		WelcomePage.navigateToMyTimeApplicationURL(getProp().getProperty("myTimeApplicationURL"));
		log.info("Before Method");
	}

	@Test(timeOut = 120000 ,priority=1)
	public void loginTest() throws Exception {
		String parentWindow = driver.getWindowHandle();
		welcomePage.clickOnSignInWithGoogleButton();
		TimeOutMethods.sleepInSeconds(10);
			welcomePage.enterUserCredentials(driver, parentWindow, getProp().getProperty("username"),
					getProp().getProperty("password"));
			homePage.clickOnLoginAfterCredntialsButton();
		driver.switchTo().window(parentWindow);
		TimeOutMethods.implicitWaitForGivenTime(60);
		Thread.sleep(10000);
	}

	@Test(timeOut = 120000, priority=2)
	public void addAccount() {
		manageGroupPage.clickOnManageGroupModule(driver);
		manageAccountPage.add_Account(driver,
				excelUtils.getCellData(MyTeamUtils.getExcelPath(), getProp().getProperty("sheetName"), "AccountName"),
				excelUtils.getCellData(MyTeamUtils.getExcelPath(), getProp().getProperty("sheetName"), "ClientAddress"),
				excelUtils.getCellData(MyTeamUtils.getExcelPath(), getProp().getProperty("sheetName"),
						"DeliveryManagers"));
	}

	@AfterMethod
	public void tearDown() {
		// getWebDriver().close();
	}

}
