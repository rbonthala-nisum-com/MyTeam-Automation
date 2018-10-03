package com.mytime.testsuite;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.database.dto.AccountDTO;
import com.mytime.database.dto.EmployeeDTO;
import com.mytime.locators.ManageAccountLocators;
import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageAccountPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamDbUtils;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.util.ExcelUtils;

public class MyTeamManageAccounts extends WebDriverInitialization{
	
	LoginPage login;
	WelcomePage welcomePage;
	MyTeamDbUtils dbUtils;
	Boolean result = false;
	
	ManageGroupPage manageGroupPage;
	ManageAccountPage manageAccountPage;
	AccountDTO accDto;
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
		return result;
	}
	
  @Test(priority = 1)
  public void tc001_AddAccountInManageAccountsModule() {
	  initLogin();
	 result = addOrUpdate("Account", "addAccount");
	  if(result) {
		  Assert.assertTrue(result, "Account is successfully added");
	  }else {
		  Assert.assertFalse(result, "Account is not added");
	  }
  }
	
	/*@Test(priority = 2)
	  public void tc002_UpdateAccountInManageAccountsModule() {
		  initLogin();
		 result = addOrUpdate("Account", "updateAccount");
		  if(result) {
			  Assert.assertTrue(result, "Account is successfully updated");
		  }else {
			  Assert.assertFalse(result, "Account is not updated");
		  }
	  }*/
	
	/*@Test(priority = 3)
	  public void tc003_VerifyAccountNameError_Please_enter_the_account_Name() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageAccountPage = new ManageAccountPage(driver);
		  manageAccountPage.clickOnManageAccountModule();
		 result = manageAccountPage.validateAccountNameErrMsg("Please enter the account Name");
		  if(result) {
			  Assert.assertTrue(result, "'Please enter the account Name' error message displayed as expected if user clicks on add button without entering the AccountName");
		  }else {
			  Assert.assertFalse(result, "Please enter the account Name' error message is not displayed");
		  }
	  }*/
	
	/*@Test(priority = 4)
	  public void tc003_VerifyAccountNameError_Please_enter_alphabets_only() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageAccountPage = new ManageAccountPage(driver);
		  manageAccountPage.clickOnManageAccountModule();
		 result = manageAccountPage.validateAccountNameErrMsg("Please enter alphabets only");
		  if(result) {
			  Assert.assertTrue(result, "'Please enter alphabets only' error message displayed as expected if user clicks on add button without entering the AccountName");
		  }else {
			  Assert.assertFalse(result, "Please enter alphabets only' error message is not displayed");
		  }
	  }*/
	
	/*@Test(priority = 5)
	  public void tc003_VerifyIndTypeError_Please_enter_the_industry_type() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageAccountPage = new ManageAccountPage(driver);
		  manageAccountPage.clickOnManageAccountModule();
		 result = manageAccountPage.validateAccountNameErrMsg("Please enter the industry type");
		  if(result) {
			  Assert.assertTrue(result, "'Please enter the industry type' error message displayed as expected if user clicks on add button without entering the AccountName");
		  }else {
			  Assert.assertFalse(result, "Please enter the industry type' error message is not displayed");
		  }
	  }*/
	
	/*@Test(priority = 6)
	  public void tc003_VerifyClientAddrError_Please_enter_the_client_address() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageAccountPage = new ManageAccountPage(driver);
		  manageAccountPage.clickOnManageAccountModule();
		 result = manageAccountPage.validateClientAddrErrMsg("Please enter the client address");
		  if(result) {
			  Assert.assertTrue(result, "'Please enter the client address' error message displayed as expected if user clicks on add button without entering the AccountName");
		  }else {
			  Assert.assertFalse(result, "Please enter the client address' error message is not displayed");
		  }
	  }*/
	
	/*@Test(priority = 7)
	  public void tc003_VerifyDeliveryManagerError_Please_select_a_delivery_Manager() {
		  initLogin();
		  manageGroupPage = new ManageGroupPage(driver);
		  manageGroupPage.clickOnManageGroupModule();
		  manageAccountPage = new ManageAccountPage(driver);
		  manageAccountPage.clickOnManageAccountModule();
		 result = manageAccountPage.validateDelMgrsErrMsg("Please select a delivery Manager");
		  if(result) {
			  Assert.assertTrue(result, "'Please select a delivery Manager' error message displayed as expected if user clicks on add button without entering the AccountName");
		  }else {
			  Assert.assertFalse(result, "Please select a delivery Manager' error message is not displayed");
		  }
	  }*/
	
  @AfterMethod
	public void tearDown() throws InterruptedException {
		login.logout(driver);
		driver.manage().deleteAllCookies();
		Thread.sleep(5000);
	}
}
