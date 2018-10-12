package com.mytime.testsuite;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
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
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TimeOutMethods;
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
	public void setUp() {
		//welcomePage = new WelcomePage(driver);
		Set<Cookie> allCookies = driver.manage().getCookies();
		for (Cookie cookie : allCookies) {
		    driver.manage().deleteCookieNamed(cookie.getName());
		}
		login = new LoginPage(getDriver());
		//welcomePage.navigateToMyTimeApplicationURL(getProp().getProperty("myTimeApplicationURL"));
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
	
	/*@Test(priority = 1)
	  public void tc001_AddProjectInManageProjectsModule() {
		  initLogin();
		 result = addOrUpdate("Project", "addProject");
		  if(result) {
			  Assert.assertTrue(result, "Project is successfully added");
		  }else {
			  Assert.assertFalse(result, "Project is not added");
		  }
	  }*/
	
	/*@Test(priority = 2)
	  public void tc001_UpdateProjectInManageProjectsModule() {
		  initLogin();
		 result = addOrUpdate("Project", "updateProject");
		  if(result) {
			  Assert.assertTrue(result, "Project is successfully updated");
		  }else {
			  Assert.assertFalse(result, "Project is not updated");
		  }
	  }*/
	
	@Test(priority = 10)
	public void tc003_Verify_All_Error_Messages() {
		initLogin();
		Clicks click = new Clicks(driver);
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageProjectPage = new ManageProjectPage(driver);
		manageProjectPage.clickOnManageProjectModule();
		Map<String, String> excelData = readDataFromExcel("addProject");
		String errors[] = { "Project Name is mandatory", "Please enter alphabets only", "Account is mandatory",
				"Domain is mandatory", "Please select a Delivery Lead", "Please select a Project Status",
				"Please select project start date", "Please select project end date" };
		click.userClick(ManageProjectLocators.btnAddProject, TimeOutMethods.waitTime10Seconds);
		
		for (String err : errors) {
			
			if (err.equals("Project Name is mandatory")) {
				result = manageProjectPage.validateProjectNameErrMsg("Project Name is mandatory");
			}else if(err.equals("Please enter alphabets only")) {
				result = manageProjectPage.validateProjectNameErrMsg("Please enter alphabets only");
			}else if(err.equals("Account is mandatory")) {
				result = manageProjectPage.validateSelectAccountErrMsg("Account is mandatory");
			}else if(err.equals("Domain is mandatory")) {
				result = manageProjectPage.validateSelectDomainErrMsg("Domain is mandatory", excelData.get("Account"));
			}else if(err.equals("Please select a Delivery Lead")) {
				result = manageProjectPage.validateSelectDelLeadErrMsg("Please select a Delivery Lead", excelData.get("Domain"));
			}else if(err.equals("Please select a Project Status")) {
				result = manageProjectPage.validateSelectPrjStatusErrMsg("Please select a Project Status", excelData.get("Delivery Lead"));
			}else if(err.equalsIgnoreCase("Please select project start date")) {
				result = manageProjectPage.validateSelectPrjStartDateErrMsg("Please select project start date", excelData.get("Project Status"), "2018-10-1","2018-11-1");
			}else if(err.equals("Please select project end date")) {
				result = manageProjectPage.validateSelectPrjEndDateErrMsg("Please select project end date", excelData.get("Project Start Date"));
			}
			
		}
		click.userClick(ManageProjectLocators.btnCancelOnAddProject, TimeOutMethods.waitTime10Seconds);
		click.userClick(ManageProjectLocators.btnOkOnCancelAlertPopUp, TimeOutMethods.waitTime10Seconds);
		
	}
	
}
