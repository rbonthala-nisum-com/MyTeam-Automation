package com.mytime.testsuite;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.database.dto.DomainDTO;
import com.mytime.database.dto.EmployeeDTO;
import com.mytime.locators.ManageAccountLocators;
import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageAccountPage;
import com.mytime.pages.ManageDomainPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.ManageProjectPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamDbUtils;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.util.ExcelUtils;

public class MyTeamManageDomains extends WebDriverInitialization{
	LoginPage login;
	WelcomePage welcomePage;
	MyTeamDbUtils dbUtils;
	Boolean result = false;
	
	ManageGroupPage manageGroupPage;
	ManageDomainPage manageDomainPage;
	DomainDTO dmnDto;
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
	}private Boolean addOrUpdateDomain(String functionality, Map<String, String> excelAccountData) {
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
		if(moduleName.equals("Domain")) {
			manageDomainPage = new ManageDomainPage(driver);
			manageDomainPage.clickOnManageDomainModule();
			if(functionality.equals("addDomain")){
				result = addOrUpdateDomain(functionality, excelAccountData);
			}
			else if(functionality.equals("updateDomain")) {
				result = addOrUpdateDomain(functionality, excelAccountData);
			}
		}
		return result;
	}
	
	 /*@Test(priority = 1)
	  public void tc001_AddDomainInManageDomainsModule() {
		  initLogin();
		 result = addOrUpdate("Domain", "addDomain");
		  if(result) {
			  Assert.assertTrue(result, "Domain is successfully added");
		  }else {
			  Assert.assertFalse(result, "Domain is not added");
		  }
	  }*/
	
	/* @Test(priority = 1)
	  public void tc001_UpdateDomainInManageDomainsModule() {
		  initLogin();
		 result = addOrUpdate("Domain", "updateDomain");
		  if(result) {
			  Assert.assertTrue(result, "Domain is successfully updated");
		  }else {
			  Assert.assertFalse(result, "Domain is not updated");
		  }
	  }*/
	
	/*@Test(priority = 3)
	  public void tc003_VerifyAccountError_Please_Select_Account() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageDomainPage = new ManageDomainPage(driver);
		  manageDomainPage.clickOnManageDomainModule();
		 result = manageDomainPage.validateSelectAccountErrMsg("Please select a Account");
		  if(result) {
			  Assert.assertTrue(result, "'Please select a Account' error message displayed as expected if user clicks on add button without selecting the AccountName");
		  }else {
			  Assert.assertFalse(result, "Please select a Account' error message is not displayed");
		  }
	  }*/
	/*@Test(priority = 4)
	  public void tc003_Verify_DomainNameError_Please_Enter_A_Domain() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageDomainPage = new ManageDomainPage(driver);
		  manageDomainPage.clickOnManageDomainModule();
		 result = manageDomainPage.validateDomainNameErrMsg("Macys", "Please enter a Domain Name");
		  if(result) {
			  Assert.assertTrue(result, "'Please enter a Domain Name' error message displayed as expected if user clicks on add button without entering the DomainName");
		  }else {
			  Assert.assertFalse(result, "Please enter a Domain Name' error message is not displayed");
		  }
	  }*/
	
	@Test(priority = 5)
	  public void tc003_VerifyDelLeadsError_Please_Select_a_Delivery_Managers() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageDomainPage = new ManageDomainPage(driver);
		  manageDomainPage.clickOnManageDomainModule();
		 result = manageDomainPage.validateDomainNameErrMsg("Macys", "Please select a deliveryManagers");
		  if(result) {
			  Assert.assertTrue(result, "'Please select a deliveryManagers' error message displayed as expected if user clicks on add button without selecting delivery managers");
		  }else {
			  Assert.assertFalse(result, "Please select a deliveryManagers' error message is not displayed");
		  }
	  }
	
}
