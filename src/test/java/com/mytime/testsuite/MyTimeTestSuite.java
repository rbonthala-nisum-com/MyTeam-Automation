package com.mytime.testsuite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mytime.database.dto.AccountDTO;
import com.mytime.database.dto.DomainDTO;
import com.mytime.database.dto.EmployeeDTO;
import com.mytime.locators.ManageAccountLocators;
import com.mytime.locators.ManageDomainLocators;
import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageAccountPage;
import com.mytime.pages.ManageDomainPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamDbUtils;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.util.ExcelUtils;

/**
 * This will contain all test cases belongs to myTime
 */
public class MyTimeTestSuite extends WebDriverInitialization {

	LoginPage login;
	WelcomePage welcomePage;
	MyTeamDbUtils dbUtils;
	
	ManageGroupPage manageGroupPage;
	ManageAccountPage manageAccountPage;
	ManageDomainPage manageDomainPage;

	@BeforeMethod
	public void setUp() {
		welcomePage = new WelcomePage(driver);
		login = new LoginPage(getDriver());
		welcomePage.navigateToMyTimeApplicationURL(getProp().getProperty("myTimeApplicationURL"));
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

	/*@Test(priority = 2)
	public void addAccount() {
		initLogin();
		EmployeeDTO empDto = new EmployeeDTO();
		Map<String, String> excelAccountData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), "AddAccount");
		AccountDTO dto = new AccountDTO();
		dbUtils = new MyTeamDbUtils();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageAccountPage = new ManageAccountPage(driver);
		manageAccountPage.clickOnManageAccountModule();
		List<String> acctData = excelAccountData.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
		List<String> acctCols = excelAccountData.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
		String uiDelManagers[] = acctData.get(2).split(",");
		manageAccountPage.addAccount(acctData.get(0), acctData.get(1), acctData.get(3), uiDelManagers);
		Map<String, String> uiAccData = MyTeamUtils.getEntireRowOrActionsColumn(acctData.get(0), driver,ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders,"EntireRow");

		Map<String, String> colNameValuePair = new HashedMap<String, String>();
		colNameValuePair.put(acctCols.get(0), acctData.get(0));
		String uiDelMgrs[] = uiAccData.get("Delivery Managers").replaceAll("\n", "").split(",");
		String dbAcctData = getDbRecord(colNameValuePair,"Accounts");
		colNameValuePair.clear();
		String inputEmpIds = getEmpIds(uiDelManagers, empDto);
		String dbEmpIds = getEmpIds(uiDelMgrs, empDto);
		dto = dbUtils.convertAccountJsonToJavaObject(dbAcctData, dto);
		dbUtils.compareAccountWithDb(dto, uiAccData, empDto, inputEmpIds,dbEmpIds);
	}*/
	/*@Test(priority = 3)
	public void updateAccount() {
		initLogin();
		Map<String, String> excelAccountData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), "UpdateAccount");
		EmployeeDTO empDto = new EmployeeDTO();
		AccountDTO dto = new AccountDTO();
		dbUtils = new MyTeamDbUtils();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageAccountPage = new ManageAccountPage(driver);
		manageAccountPage.clickOnManageAccountModule();
		List<String> acctData = excelAccountData.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
		List<String> acctCols = excelAccountData.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
		String delManagers[] = acctData.get(3).split(",");
		String inputDelMgr[] = acctData.get(2).split(",");
		Map<String, String> actionsEditLocator = MyTeamUtils.getEntireRowOrActionsColumn(acctData.get(0), driver, ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders, "ActionsColumn");
		Entry<String,String> actionsLocator = actionsEditLocator.entrySet().iterator().next();
		manageAccountPage.updateAccount(actionsLocator.getValue(), acctData.get(0), acctData.get(1), acctData.get(4), delManagers, inputDelMgr);
		Map<String, String> uiAccData = MyTeamUtils.getEntireRowOrActionsColumn(acctData.get(0), driver,ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders,"EntireRow");
		Map<String, String> colNameValuePair = new HashedMap<String, String>();
		colNameValuePair.put(acctCols.get(0), acctData.get(0));
		String uiDelMgrs[] = uiAccData.get("Delivery Managers").replaceAll("\n", "").split(",");
		String dbAcctData = getDbRecord(colNameValuePair,"Accounts");
		colNameValuePair.clear();
		String inputEmpIds = getEmpIds(inputDelMgr, empDto);
		String dbEmpIds = getEmpIds(uiDelMgrs, empDto);
		dto = dbUtils.convertAccountJsonToJavaObject(dbAcctData, dto);
		dbUtils.compareAccountWithDb(dto, uiAccData, empDto, inputEmpIds,dbEmpIds);
	}
	
	@Test(priority = 4)
	public void addDomain() {
		initLogin();
		dbUtils = new MyTeamDbUtils();
		EmployeeDTO empDto = new EmployeeDTO();
		Map<String, String> excelDomainData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), "AddDomain");
		DomainDTO dto = new DomainDTO();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageDomainPage = new ManageDomainPage(driver);
		manageDomainPage.clickOnManageDomainModule();
		List<String> acctData = excelDomainData.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
		List<String> acctCols = excelDomainData.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
		String inputDelLeads[] = acctData.get(0).split(",");
		manageDomainPage.addDomain(acctData.get(1), acctData.get(2), inputDelLeads);
		Map<String, String> uiDomainData = MyTeamUtils.getEntireRowOrActionsColumn(acctData.get(2), driver,ManageDomainLocators.domainTable, ManageDomainLocators.domaintHeaders,"EntireRow");

		Map<String, String> colNameValuePair = new HashedMap<String, String>();
		colNameValuePair.put(acctCols.get(2), acctData.get(2));
		String uiDelLeads[] = uiDomainData.get("Delivery Leads").replaceAll("\n", "").split(",");
		String dbDomainData = getDbRecord(colNameValuePair,"Domains");
		colNameValuePair.clear();
		String inputEmpIds = getEmpIds(inputDelLeads, empDto);
		String dbEmpIds = getEmpIds(uiDelLeads, empDto);
		dto = dbUtils.convertDomainJsonToJavaObject(dbDomainData, dto);
		dbUtils.compareDomainWithDb(dto, uiDomainData, empDto, inputEmpIds,dbEmpIds);
		
	}
	*/
	@Test(priority = 3)
	public void updateDomain() {
		initLogin();
		dbUtils = new MyTeamDbUtils();
		Map<String, String> excelDomainData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), "UpdateDomain");
		EmployeeDTO empDto = new EmployeeDTO();
		DomainDTO dto = new DomainDTO();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageDomainPage = new ManageDomainPage(driver);
		manageDomainPage.clickOnManageDomainModule();
		List<String> acctData = excelDomainData.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
		List<String> acctCols = excelDomainData.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
		String delLeads[] = acctData.get(2).split(",");
		String inputDelLeads[] = acctData.get(0).split(",");
		Map<String, String> actionsEditLocator = MyTeamUtils.getEntireRowOrActionsColumn(acctData.get(3), driver, ManageDomainLocators.domainTable, ManageDomainLocators.domaintHeaders, "ActionsColumn");
		Entry<String,String> actionsLocator = actionsEditLocator.entrySet().iterator().next();
		manageDomainPage.updateDomain(actionsLocator.getValue(), acctData.get(3), inputDelLeads, delLeads);
		Map<String, String> uiDomainData = MyTeamUtils.getEntireRowOrActionsColumn(acctData.get(3), driver,ManageDomainLocators.domainTable, ManageDomainLocators.domaintHeaders,"EntireRow");
		Map<String, String> colNameValuePair = new HashedMap<String, String>();
		colNameValuePair.put(acctCols.get(0), acctData.get(0));
		String uiDelMgrs[] = uiDomainData.get("Delivery Managers").replaceAll("\n", "").split(",");
		String dbAcctData = getDbRecord(colNameValuePair,"Accounts");
		colNameValuePair.clear();
		String inputEmpIds = getEmpIds(inputDelLeads, empDto);
		String dbEmpIds = getEmpIds(uiDelMgrs, empDto);
		dto = dbUtils.convertDomainJsonToJavaObject(dbAcctData, dto);
		dbUtils.compareDomainWithDb(dto, uiDomainData, empDto, inputEmpIds,dbEmpIds);
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
	@AfterMethod
	public void tearDown() {
		login.logout(driver);
		driver.manage().deleteAllCookies();
	}

}
