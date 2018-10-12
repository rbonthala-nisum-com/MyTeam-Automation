package com.mytime.testsuite;

import java.util.Map;

import com.mytime.database.dto.EmployeeDTO;
import com.mytime.database.dto.ProjectDTO;
import com.mytime.database.dto.TeamMateDTO;
import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageAccountPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.ManageProjectPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamDbUtils;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.util.ExcelUtils;

public class MyTeamAddTeamMate extends WebDriverInitialization{
	
	LoginPage login;
	WelcomePage welcomePage;
	MyTeamDbUtils dbUtils;
	Boolean result = false;
	
	ManageGroupPage manageGroupPage;
	ManageProjectPage manageProjectPage;
	TeamMateDTO tmateDto;
	EmployeeDTO empDto;
	
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
	
	private Boolean addOrUpdateTeamMate(String functionality, Map<String, String> excelAccountData) {

		result = false;
		
		return result;
	}
	
	private Boolean addOrUpdate(String moduleName, String functionality) {
		 
		Map<String, String> excelAccountData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), functionality);
		dbUtils = new MyTeamDbUtils();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		if (moduleName.equals("Account")) {
			manageProjectPage = new ManageProjectPage(driver);
			manageProjectPage.clickOnManageProjectModule();
			if(functionality.equals("addAccount")){
				result = addOrUpdateTeamMate(functionality, excelAccountData);
			}
			else if(functionality.equals("updateAccount")) {
				result = addOrUpdateTeamMate(functionality, excelAccountData);
			}
		}
		return result;
	}
	
  
}
