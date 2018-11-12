package com.mytime.testsuite;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.database.dto.EmployeeDTO;
import com.mytime.database.dto.ProjectDTO;
import com.mytime.locators.ManageProjectLocators;
import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.ManageProjectPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamDbUtils;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.components.Browsers;
import com.nisum.qa.automation.util.ExcelUtils;

public class MyTeamManageProjects extends WebDriverInitialization{
	
	LoginPage login;
	WelcomePage welcomePage;
	MyTeamDbUtils dbUtils;
	Boolean result = false;
	
	ManageGroupPage manageGroupPage;
	ManageProjectPage manageProjectPage;
	ProjectDTO prjDto;
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

	private String getDbRecord(Map<String, String> colNameValuePair, String tableName) {
		String dbRecord = getDbRecord(getProp().getProperty("hostName"), getProp().getProperty("dbName"),
				tableName, colNameValuePair);
		return dbRecord;
	}
	private String getEmpIds(String empNames[], EmployeeDTO empDto) {
		String dbEmpData = "";
		String empId = "";
		Map<String, String> colNameValuePair = new HashMap<String, String>();
		for (int i = 0; i < empNames.length; i++) {
			colNameValuePair.put("EmployeeName", empNames[i]);
			if (i == empNames.length - 1) {
				dbEmpData = getDbRecord(colNameValuePair, "EmployeeDetails");
				empDto = dbUtils.convertEmpJsonToJavaObject(dbEmpData, empDto);
				empId = empId + empDto.getEmployeeId();
			} else {
				dbEmpData = getDbRecord(colNameValuePair, "EmployeeDetails");
				empDto = dbUtils.convertEmpJsonToJavaObject(dbEmpData, empDto);
				empId = empId + empDto.getEmployeeId() + ",";
			}
		}
		return empId;
	}
	private Boolean addOrUpdateProject(String functionality, Map<String, String> excelData) {
		result = false;
		prjDto = new ProjectDTO();
		Map<String, String>colNameValuePair = new HashMap<String, String>();
		colNameValuePair.put("Project Name",excelData.get("Project Name"));
		String dbData = null;
		String addDelLeads[] = excelData.get("Delivery Lead").replaceAll("\n", "").split(",");
		if(functionality.equals("addProject")) {
			manageProjectPage.addProject(excelData.get("Project Name"), excelData.get("Account"), excelData.get("Domain"), excelData.get("Delivery Lead"), excelData.get("Project Status"), excelData.get("Project Start Date"), excelData.get("Project End Date"));
			 dbData = getDbRecord(colNameValuePair,"Teams");
			prjDto = dbUtils.convertProjectJsonToJavaObject(dbData, prjDto);
		}else if(functionality.equals("updateProject")) {
			Map<String, String> actionsEditLocator = MyTeamUtils.getEntireRowOrActionsColumn(excelData.get("Project Name"), driver, ManageProjectLocators.projectTable, ManageProjectLocators.projectHeaders, "ActionsColumn","Edit");
			Entry<String,String> actionsLocator = actionsEditLocator.entrySet().iterator().next();
			manageProjectPage.updateProject(actionsLocator.getValue(), excelData.get("Project Name"), excelData.get("Account"), excelData.get("Domain"), excelData.get("Delivery Lead"), excelData.get("Project Status"), excelData.get("Project Start Date"), excelData.get("Project End Date"));
			dbData = getDbRecord(colNameValuePair,"Teams");
			prjDto = dbUtils.convertProjectJsonToJavaObject(dbData, prjDto);
		}
		Map<String, String>uiData = MyTeamUtils.getEntireRowOrActionsColumn(excelData.get("Project Name"), driver,ManageProjectLocators.projectTable, ManageProjectLocators.projectHeaders,"EntireRow","");
		String uiDelLeads[] = uiData.get("Delivery Lead / Lead").replaceAll("\n", "").split(",");
		String inputEmpIds = getEmpIds(addDelLeads, empDto);
		String dbEmpIds = getEmpIds(uiDelLeads, empDto);
		dbUtils.compareProjectWithDb(prjDto, uiData, empDto, inputEmpIds,dbEmpIds);
		return result=true;
	}
	
	private Map<String, String> readDataFromExcel(String functionality){
		Map<String, String> excelData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), functionality);
		return excelData;
	}
	
	private Boolean addOrUpdate(String moduleName, String functionality) {
		 
		Map<String, String> excelData = readDataFromExcel(functionality);
		dbUtils = new MyTeamDbUtils();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		if(moduleName.equals("Project")) {
			manageProjectPage = new ManageProjectPage(driver);
			manageProjectPage.clickOnManageProjectModule();
			if(functionality.equals("addProject")) {
				result = addOrUpdateProject(functionality, excelData);
			}else if(functionality.equals("updateProject")) {
				result = addOrUpdateProject(functionality, excelData);
			}
		}
		return result;
	}
	
	@Test(priority = 1)
	  public void tc001_AddProjectInManageProjectsModule() {
		  initLogin();
		 result = addOrUpdate("Project", "addProject");
		  if(result) {
			  Assert.assertTrue(result, "Project is successfully added");
		  }else {
			  Assert.assertFalse(result, "Project is not added");
		  }
	  }
	
	@Test(priority = 2)
	  public void tc002_UpdateProjectInManageProjectsModule() {
		  initLogin();
		 result = addOrUpdate("Project", "updateProject");
		  if(result) {
			  Assert.assertTrue(result, "Project is successfully updated");
		  }else {
			  Assert.assertFalse(result, "Project is not updated");
		  }
	  }
	
	@Test(priority = 3)
	  public void tc003_VerifyProjectNameError_Please_enter_the_account_Name() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageProjectPage = new ManageProjectPage(driver);
		  manageProjectPage.clickOnManageProjectModule();
		 result = manageProjectPage.validateProjectNameErrMsg("Project Name is mandatory");
		  if(result) {
			  Assert.assertTrue(result, "'Please enter the account Name' error message displayed as expected if user clicks on add button without entering the AccountName");
		  }else {
			  Assert.assertFalse(result, "Please enter the account Name' error message is not displayed");
		  }
	  }
	
	@Test(priority = 4)
	  public void tc004_VerifyProjectNameError_Please_enter_alphabets_only() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageProjectPage = new ManageProjectPage(driver);
		  manageProjectPage.clickOnManageProjectModule();
		 result = manageProjectPage.validateProjectNameErrMsg("Please enter alphabets only");
		  if(result) {
			  Assert.assertTrue(result, "'Please enter alphabets only' error message displayed as expected if user clicks on add button without entering the AccountName");
		  }else {
			  Assert.assertFalse(result, "Please enter alphabets only' error message is not displayed");
		  }
	  }
	
	@Test(priority = 5)
	public void tc005_VerifyErrorMessage_Account_is_mandatory() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageProjectPage = new ManageProjectPage(driver);
		manageProjectPage.clickOnManageProjectModule();
		result = manageProjectPage.validateSelectAccountErrMsg("Account is mandatory");
		if (result) {
			Assert.assertTrue(result, "'Account is mandatory' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Account is mandatory' error message is not displayed");
		}

	}

	@Test(priority = 6)
	public void tc006_VerifyErrorMessage_Domain_is_mandatory() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageProjectPage = new ManageProjectPage(driver);
		manageProjectPage.clickOnManageProjectModule();
		Map<String, String> excelData = readDataFromExcel("addProject");
		result = manageProjectPage.validateSelectDomainErrMsg("Domain is mandatory", excelData.get("Account"));
		if (result) {
			Assert.assertTrue(result, "'Domain is mandatory' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Domain is mandatory' error message is not displayed");
		}

	}
	
	@Test(priority = 7)
	public void tc007_VerifyErrorMessage_Please_select_a_Delivery_Lead() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageProjectPage = new ManageProjectPage(driver);
		manageProjectPage.clickOnManageProjectModule();
		Map<String, String> excelData = readDataFromExcel("addProject");
		result = manageProjectPage.validateSelectDelLeadErrMsg("Please select a Delivery Lead", excelData.get("Account"), excelData.get("Domain"));
		if (result) {
			Assert.assertTrue(result, "'Please select a Delivery Lead' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Please select a Delivery Lead' error message is not displayed");
		}

	}
	
	@Test(priority = 8)
	public void tc008_VerifyErrorMessage_Please_select_a_Project_Status() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageProjectPage = new ManageProjectPage(driver);
		manageProjectPage.clickOnManageProjectModule();
		Map<String, String> excelData = readDataFromExcel("addProject");
		result = manageProjectPage.validateSelectPrjStatusErrMsg("Please select a Project Status", excelData.get("Account"), excelData.get("Domain"), excelData.get("Delivery Lead"));
		if (result) {
			Assert.assertTrue(result, "'Please select a Project Status' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Please select a Project Status' error message is not displayed");
		}

	}
	
	@Test(priority = 9)
	public void tc009_VerifyErrorMessage_Please_select_project_start_date() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageProjectPage = new ManageProjectPage(driver);
		manageProjectPage.clickOnManageProjectModule();
		Map<String, String> excelData = readDataFromExcel("addProject");
		result = manageProjectPage.validateSelectPrjStartDateErrMsg("Please select project start date", excelData.get("Account"), excelData.get("Domain"), excelData.get("Delivery Lead"), excelData.get("Project Status"), MyTeamUtils.getCurrentDate(), MyTeamUtils.getCurrentDate()+1);
		if (result) {
			Assert.assertTrue(result, "'Please select project start date' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Please select project start date' error message is not displayed");
		}

	}
	
	@Test(priority = 10)
	public void tc010_VerifyErrorMessage_Start_date_should_not_Be_greater_than_end_date() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageProjectPage = new ManageProjectPage(driver);
		manageProjectPage.clickOnManageProjectModule();
		Map<String, String> excelData = readDataFromExcel("addProject");
		result = manageProjectPage.validateSelectPrjStartDateErrMsg("Start date should not be greater than end date", excelData.get("Account"), excelData.get("Domain"), excelData.get("Delivery Lead"), excelData.get("Project Status"), MyTeamUtils.getFutureDate(), MyTeamUtils.getCurrentDate());
		if (result) {
			Assert.assertTrue(result, "'Start date should not be greater than end date' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Start date should not be greater than end date' error message is not displayed");
		}

	}
	
	@Test(priority = 11)
	public void tc011_VerifyErrorMessage_Please_select_project_end_date() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageProjectPage = new ManageProjectPage(driver);
		manageProjectPage.clickOnManageProjectModule();
		Map<String, String> excelData = readDataFromExcel("addProject");
		result = manageProjectPage.validateSelectPrjEndDateErrMsg("Please select project end date", excelData.get("Account"), excelData.get("Domain"), excelData.get("Delivery Lead"), excelData.get("Project Status"), MyTeamUtils.getFutureDate());
		if (result) {
			Assert.assertTrue(result, "'Please select project end date' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Please select project end date' error message is not displayed");
		}

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
