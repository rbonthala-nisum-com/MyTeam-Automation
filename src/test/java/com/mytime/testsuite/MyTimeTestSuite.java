package com.mytime.testsuite;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageAccountPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.util.ExcelUtils;

/**
 * This will contain all test cases belongs to myTime
 */
public class MyTimeTestSuite extends WebDriverInitialization {

	Logger log;
	LoginPage login;
	WelcomePage welcomePage;

	@BeforeMethod
	public void setUp() {
		log = Logger.getLogger(getClass());
		welcomePage = new WelcomePage(driver);
		login = new LoginPage(getDriver());
		welcomePage.navigateToMyTimeApplicationURL(getProperty().getProperty("myTimeApplicationURL"));
		log.info("Before Method");
	}

	/*
	 * @Test(timeOut = 120000, priority = 1) public void loginTest() throws
	 * Exception { initLogin(); }
	 */

	private void initLogin() {
		String parentWindow = driver.getWindowHandle();
		welcomePage.clickOnSignInWithGoogleButton();
		welcomePage.sleepInSeconds(10);
		welcomePage.enterUserCredentials(login, parentWindow, getProperty().getProperty("username"),
				getProperty().getProperty("password"));
		login.clickOnLoginConformationPopup(driver);
		driver.switchTo().window(parentWindow);
	}

	@Test(priority = 2)
	public void addAccount() {
		initLogin();
		ManageGroupPage manageGroupPage = new ManageGroupPage(driver);
		ManageAccountPage manageAccountPage = new ManageAccountPage(driver);
//		ExcelUtils excel = new ExcelUtils();
		manageGroupPage.clickOnManageGroupModule();
		manageAccountPage.addAccount(
				ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), getProperty().getProperty("sheetName"),
						"AccountName"),
				ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), getProperty().getProperty("sheetName"),
						"IndustryType"),
				ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), getProperty().getProperty("sheetName"),
						"ClientAddress"),
				ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), getProperty().getProperty("sheetName"),
						"DeliveryManagers"));

	}

	@AfterMethod
	public void tearDown() {
		getDriver().close();
	}

}
