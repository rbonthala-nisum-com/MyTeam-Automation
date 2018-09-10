package com.mytime.testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.pages.HomePage;
import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageAccountPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamLogger;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.components.TimeOutMethods;
import com.nisum.qa.automation.util.ExcelUtils;

/**
 * This will contain all test cases belongs to myTime
 */
public class MyTimeTestSuite extends WebDriverInitialization {

	HomePage homePage;
	LoginPage loginPage;
	WelcomePage welcomePage;
	ManageGroupPage manageGroupPage;
	ManageAccountPage manageAccountPage;
	ExcelUtils excelUtils;

	@BeforeMethod
	public void setUp() {
		welcomePage = new WelcomePage(getDriver());
		WelcomePage.navigateToMyTimeApplicationURL(getProperty().getProperty("myTimeApplicationURL"));
		MyTeamLogger.getInstance().info("Successfully opened the website MyTeam");
	}

	@Test(timeOut = 120000, priority = 1)
	public void loginTest() throws Exception {
		String parentWindow = driver.getWindowHandle();
		welcomePage.clickOnSignInWithGoogleButton();
		TimeOutMethods.sleepInSeconds(10);
		welcomePage.enterUserCredentials(driver, parentWindow, getProperty().getProperty("username"),
				getProperty().getProperty("password"));
		homePage.clickOnLoginAfterCredntialsButton();
		driver.switchTo().window(parentWindow);
		TimeOutMethods.implicitWaitForGivenTime(60);
		Thread.sleep(10000);
	}

	@Test(timeOut = 120000, priority = 2)
	public void addAccount() {
		manageGroupPage.clickOnManageGroupModule(driver);
		manageAccountPage.add_Account(driver,
				excelUtils.getCellData(MyTeamUtils.getExcelPath(), getProperty().getProperty("sheetName"),
						"AccountName"),
				excelUtils.getCellData(MyTeamUtils.getExcelPath(), getProperty().getProperty("sheetName"),
						"ClientAddress"),
				excelUtils.getCellData(MyTeamUtils.getExcelPath(), getProperty().getProperty("sheetName"),
						"DeliveryManagers"));
	}

	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}

}
