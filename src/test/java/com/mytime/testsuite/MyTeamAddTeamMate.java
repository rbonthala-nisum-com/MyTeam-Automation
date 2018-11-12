package com.mytime.testsuite;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.myteam.exceptionhandler.MyTeamException;
import com.mytime.database.dto.EmployeeDTO;
import com.mytime.database.dto.TeamMateDTO;
import com.mytime.locators.ManageProjectLocators;
import com.mytime.pages.AddTeamMatePage;
import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.ManageProjectPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamDbUtils;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.components.Browsers;
import com.nisum.qa.automation.util.ExcelUtils;

public class MyTeamAddTeamMate extends WebDriverInitialization{
	
	LoginPage login;
	WelcomePage welcomePage;
	MyTeamDbUtils dbUtils;
	Boolean result = false;
	
	ManageGroupPage manageGroupPage;
	ManageProjectPage manageProjectPage;
	AddTeamMatePage addTeamMatePage;
	TeamMateDTO tmateDto;
	EmployeeDTO empDto;
	
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
	
	private Map<String, String> readDataFromExcel(String functionality) {
		Map<String, String> excelData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), functionality);
		return excelData;
	}
	
	private Boolean addOrUpdateTeamMate(String functionality, Map<String, String> excelAccountData) throws MyTeamException {

		addTeamMatePage = new AddTeamMatePage(driver);
		result = false;
		if(functionality.equals("AddTeamMate")) {
			Map<String, String> viewPrj = MyTeamUtils.getEntireRowOrActionsColumn(excelAccountData.get("Project Name"), driver, ManageProjectLocators.projectTable, ManageProjectLocators.projectHeaders, "Actions" , "TeamMate");
			Entry<String,String> btnViewProject = viewPrj.entrySet().iterator().next();
			Map<String, String> editPrj = MyTeamUtils.getEntireRowOrActionsColumn(excelAccountData.get("Project Name"), driver, ManageProjectLocators.projectTable, ManageProjectLocators.projectHeaders, "Actions", "Project");
			Entry<String,String> btnEditProject = editPrj.entrySet().iterator().next();
			result = addTeamMatePage.addTeamMate(btnViewProject.getValue(), btnEditProject.getValue(), excelAccountData.get("Select a Employee"), excelAccountData.get("Assigned Role"), excelAccountData.get("Shift"), excelAccountData.get("Billability Status"), excelAccountData.get("Billable Date"));
		}
		return result;
	}
	
	private Boolean addOrUpdate(String moduleName, String functionality) throws MyTeamException {
		 
		Map<String, String> excelAccountData = readDataFromExcel(functionality);
		dbUtils = new MyTeamDbUtils();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		if (moduleName.equals("Project")) {
			manageProjectPage = new ManageProjectPage(driver);
			manageProjectPage.clickOnManageProjectModule();
			if(functionality.equals("AddTeamMate")){
				result = addOrUpdateTeamMate(functionality, excelAccountData);
			}
			else if(functionality.equals("UpdateTeamMate")) {
				result = addOrUpdateTeamMate(functionality, excelAccountData);
			}
		}
		return result;
	}
	
	 @Test(priority = 1)
	  public void tc001_AddTeamMate() throws MyTeamException {
		  initLogin();
		 result = addOrUpdate("Project", "AddTeamMate");
		  if(result) {
			  Assert.assertTrue(result, "Account is successfully added");
		  }else {
			  Assert.assertFalse(result, "Account is not added");
		  }
	  }
	
	 @AfterMethod
		public void tearDown() throws InterruptedException {
			login.logout(driver);
			driver.manage().deleteAllCookies();
			Thread.sleep(5000);
		}
  
}
