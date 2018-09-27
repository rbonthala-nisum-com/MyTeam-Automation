package com.mytime.testsuite;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.database.dto.AccountDTO;
import com.mytime.database.dto.DomainDTO;
import com.mytime.database.dto.EmployeeDTO;
import com.mytime.database.dto.ProjectDTO;
import com.mytime.locators.ManageAccountLocators;
import com.mytime.locators.ManageProjectLocators;
import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageAccountPage;
import com.mytime.pages.ManageDomainPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.ManageProjectPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamDbUtils;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.components.TimeOutMethods;
import com.nisum.qa.automation.util.ExcelUtils;

public class MyTimeTestSuite extends WebDriverInitialization{
	
	LoginPage login;
	WelcomePage welcomePage;
	MyTeamDbUtils dbUtils;
	Boolean result = false;
	
	ManageGroupPage manageGroupPage;
	ManageAccountPage manageAccountPage;
	ManageDomainPage manageDomainPage;
	ManageProjectPage manageProjectPage;
	AccountDTO accDto;
	DomainDTO dmnDto;
	EmployeeDTO empDto;
	ProjectDTO prjDto;
	
	@BeforeMethod
	public void setUp() {
		//welcomePage = new WelcomePage(driver);
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

		// colNameValuePair.put(key, value);
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
	private Boolean addOrUpdateAccount(String functionality, Map<String, String> excelAccountData) {
		result = false;
		accDto = new AccountDTO();
		String dbData = null;
		Map<String, String>colNameValuePair = new HashMap<String, String>();
		colNameValuePair.put("Account Name",excelAccountData.get("Account Name"));
		String addDelMgrs[] = excelAccountData.get("Delivery Managers").replaceAll("\n", "").split(",");
		if(functionality.equals("addAccount")) {
			manageAccountPage.addAccount(excelAccountData.get("Account Name"), excelAccountData.get("Industry Type"), excelAccountData.get("Client Address"), addDelMgrs);
			dbData = getDbRecord(colNameValuePair,"Accounts");
			accDto = dbUtils.convertAccountJsonToJavaObject(dbData, accDto);
		}else if(functionality.equals("updateAccount")) {
			String removeDelMgrs[] = excelAccountData.get("Remove Delivery Managers").replaceAll("\n", "").split(",");
			Map<String, String> actionsEditLocator = MyTeamUtils.getEntireRowOrActionsColumn(excelAccountData.get("Account Name"), driver, ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders, "ActionsColumn");
			Entry<String,String> actionsLocator = actionsEditLocator.entrySet().iterator().next();
			manageAccountPage.updateAccount(actionsLocator.getValue(), excelAccountData.get("Account Name"), excelAccountData.get("Industry Type"), excelAccountData.get("Client Address"), addDelMgrs, removeDelMgrs);
			dbData = getDbRecord(colNameValuePair,"Accounts");
			accDto = dbUtils.convertAccountJsonToJavaObject(dbData, accDto);
		}
		Map<String, String>uiData = MyTeamUtils.getEntireRowOrActionsColumn(excelAccountData.get("Account Name"), driver,ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders,"EntireRow");
		String uiDelMgrs[] = uiData.get("Delivery Managers").replaceAll("\n", "").split(",");
		String inputEmpIds = getEmpIds(addDelMgrs, empDto);
		String dbEmpIds = getEmpIds(uiDelMgrs, empDto);
		accDto = dbUtils.convertAccountJsonToJavaObject(dbData, accDto);
		dbUtils.compareAccountWithDb(accDto, uiData, empDto, inputEmpIds,dbEmpIds);
		return result=true;
	}
	private Boolean addOrUpdateDomain(String functionality, Map<String, String> excelAccountData) {
		result = false;
		dmnDto = new DomainDTO();
		Map<String, String>colNameValuePair = new HashMap<String, String>();
		colNameValuePair.put("Domain Name",excelAccountData.get("Domain Name"));
		String dbData = null;
		String addDelMgrs[] = excelAccountData.get("Delivery Managers").replaceAll("\n", "").split(",");
		if(functionality.equals("addDomain")) {
			manageDomainPage.addDomain(excelAccountData.get("Account"), excelAccountData.get("Domain Name"), addDelMgrs);
			 dbData = getDbRecord(colNameValuePair,"Domain");
			dmnDto = dbUtils.convertDomainJsonToJavaObject(dbData, dmnDto);
		}else if(functionality.equals("updateDomain")) {
			String removeDelMgrs[] = excelAccountData.get("Remove Delivery Managers").replaceAll("\n", "").split(",");
			Map<String, String> actionsEditLocator = MyTeamUtils.getEntireRowOrActionsColumn(excelAccountData.get("Account Name"), driver, ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders, "ActionsColumn");
			Entry<String,String> actionsLocator = actionsEditLocator.entrySet().iterator().next();
			manageDomainPage.updateDomain(actionsLocator.getValue(), excelAccountData.get("Domain Name"), addDelMgrs, removeDelMgrs);
			dbData = getDbRecord(colNameValuePair,"Domain");
			dmnDto = dbUtils.convertDomainJsonToJavaObject(dbData, dmnDto);
		}
		Map<String, String>uiData = MyTeamUtils.getEntireRowOrActionsColumn(excelAccountData.get("Domain Name"), driver,ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders,"EntireRow");
		String uiDelMgrs[] = uiData.get("Delivery Managers").replaceAll("\n", "").split(",");
		String inputEmpIds = getEmpIds(addDelMgrs, empDto);
		String dbEmpIds = getEmpIds(uiDelMgrs, empDto);
		dbUtils.compareDomainWithDb(dmnDto, uiData, empDto, inputEmpIds,dbEmpIds);
		return result=true;
	}

	private Boolean addOrUpdate(String moduleName, String functionality) {
	 
		Map<String, String> excelAccountData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), functionality);
		dbUtils = new MyTeamDbUtils();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		if (moduleName.equals("Account")) {
			manageAccountPage = new ManageAccountPage(driver);
			manageAccountPage.clickOnManageAccountModule();
			if(functionality.equals("addAccount")){
				result = addOrUpdateAccount(functionality, excelAccountData);
			}
			else if(functionality.equals("updateAccount")) {
				result = addOrUpdateAccount(functionality, excelAccountData);
			}
		}
		else if(moduleName.equals("Domain")) {
			manageDomainPage = new ManageDomainPage(driver);
			manageDomainPage.clickOnManageDomainModule();
			if(functionality.equals("addAccount")){
				result = addOrUpdateDomain(functionality, excelAccountData);
			}
			else if(functionality.equals("updateAccount")) {
				result = addOrUpdateDomain(functionality, excelAccountData);
			}
		}
		else if(moduleName.equals("Project")) {
			manageProjectPage = new ManageProjectPage(driver);
			manageProjectPage.clickOnManageProjectModule();
			if(functionality.equals("addProject")) {
				result = addOrUpdateProject(functionality, excelAccountData);
			}
		}
		return result;
	}
	
	private Boolean addOrUpdateProject(String functionality, Map<String, String> excelAccountData) {
		result = false;
		prjDto = new ProjectDTO();
		Map<String, String>colNameValuePair = new HashMap<String, String>();
		colNameValuePair.put("Project Name",excelAccountData.get("Project Name"));
		String dbData = null;
		String addDelLeads[] = excelAccountData.get("Delivery Lead").replaceAll("\n", "").split(",");
		if(functionality.equals("addProject")) {
			manageProjectPage.addProject(excelAccountData.get("Project Name"), excelAccountData.get("Account"), excelAccountData.get("Domain"), excelAccountData.get("Delivery Lead"), excelAccountData.get("Project Status"), excelAccountData.get("Project Start Date"), excelAccountData.get("Project End Date"));
			 dbData = getDbRecord(colNameValuePair,"Teams");
			prjDto = dbUtils.convertProjectJsonToJavaObject(dbData, prjDto);
		}else if(functionality.equals("updateProject")) {
			Map<String, String> actionsEditLocator = MyTeamUtils.getEntireRowOrActionsColumn(excelAccountData.get("Account Name"), driver, ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders, "ActionsColumn");
			Entry<String,String> actionsLocator = actionsEditLocator.entrySet().iterator().next();
			manageProjectPage.updateProject(actionsLocator.getValue(), excelAccountData.get("Project Name"), excelAccountData.get("Account"), excelAccountData.get("Domain"), excelAccountData.get("Delivery Lead"), excelAccountData.get("Project Status"), excelAccountData.get("Project Start Date"), excelAccountData.get("Project End Date"));
			dbData = getDbRecord(colNameValuePair,"Teams");
			prjDto = dbUtils.convertProjectJsonToJavaObject(dbData, prjDto);
		}
		Map<String, String>uiData = MyTeamUtils.getEntireRowOrActionsColumn(excelAccountData.get("Project Name"), driver,ManageProjectLocators.projectTable, ManageProjectLocators.projectHeaders,"EntireRow");
		String uiDelLeads[] = uiData.get("Delivery Lead / Lead").replaceAll("\n", "").split(",");
		String inputEmpIds = getEmpIds(addDelLeads, empDto);
		String dbEmpIds = getEmpIds(uiDelLeads, empDto);
		dbUtils.compareProjectWithDb(prjDto, uiData, empDto, inputEmpIds,dbEmpIds);
		return result=true;
	}
	
	@Test(priority = 1)
	public void addProject(){
		 initLogin();
		 result = addOrUpdate("Project", "addProject");
		  if(result) {
			  Assert.assertTrue(result, "Project is successfully added");
		  }else {
			  Assert.assertFalse(result, "Project is not added");
		  }
	}
	/*@Test(priority = 1)
	public void firstTestCase(){
		initLogin();
		System.out.println("first test case");
	}
	
	@Test(priority = 2)
	public void secondTestCase(){
		initLogin();
		System.out.println("first test case");
	}*/
	
 /* @Test(priority = 3)
  public void addAccount() {
	  initLogin();
	 result = addOrUpdate("Account", "addAccount");
	  if(result) {
		  Assert.assertTrue(result, "Account is successfully added");
	  }else {
		  Assert.assertFalse(result, "Account is not added");
	  }
  }*/
  
	/*@Test(priority = 4)
	  public void updateAccount() {
		  initLogin();
		 result = addOrUpdate("Account", "updateAccount");
		  if(result) {
			  Assert.assertTrue(result, "Account is successfully updated");
		  }else {
			  Assert.assertFalse(result, "Account is not updated");
		  }
	  }*/
  @AfterMethod
	public void tearDown() throws InterruptedException {
		login.logout(driver);
		driver.manage().deleteAllCookies();
		Thread.sleep(5000);
	}
}
