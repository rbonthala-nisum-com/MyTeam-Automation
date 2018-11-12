package com.mytime.testsuite;

import java.util.HashMap;
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

import com.mytime.database.dto.DomainDTO;
import com.mytime.database.dto.EmployeeDTO;
import com.mytime.locators.ManageAccountLocators;
import com.mytime.locators.ManageDomainLocators;
import com.mytime.locators.MyTeamCommonLocators;
import com.mytime.pages.LoginPage;
import com.mytime.pages.ManageDomainPage;
import com.mytime.pages.ManageGroupPage;
import com.mytime.pages.WelcomePage;
import com.mytime.util.MyTeamDbUtils;
import com.mytime.util.MyTeamUtils;
import com.mytime.util.WebDriverInitialization;
import com.nisum.qa.automation.components.Browsers;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TimeOutMethods;
import com.nisum.qa.automation.util.ExcelUtils;

public class MyTeamManageDomains extends WebDriverInitialization {
	LoginPage login;
	WelcomePage welcomePage;
	MyTeamDbUtils dbUtils;

	ManageGroupPage manageGroupPage;
	ManageDomainPage manageDomainPage;
	DomainDTO dmnDto;
	EmployeeDTO empDto;

	@BeforeMethod
	public void setUp(ITestContext context) {
		log = Logger.getLogger(getClass());
		Browsers browser = new Browsers();
		driver = browser.launchSpecifiedBrowser(getProp().getProperty("browserName"), context);
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
		String dbRecord = getDbRecord(getProp().getProperty("hostName"), getProp().getProperty("dbName"), tableName,
				colNameValuePair);
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

	private Boolean addOrUpdateDomain(String functionality, Map<String, String> excelAccountData) {
		Boolean result = false;
		dmnDto = new DomainDTO();
		Map<String, String> colNameValuePair = new HashMap<String, String>();
		colNameValuePair.put("Domain Name", excelAccountData.get("Domain Name"));
		String dbData = null;
		String addDelMgrs[] = excelAccountData.get("Delivery Managers").replaceAll("\n", "").split(",");
		if (functionality.equals("addDomain")) {
			manageDomainPage.addDomain(excelAccountData.get("Account"), excelAccountData.get("Domain Name"),
					addDelMgrs);
			dbData = getDbRecord(colNameValuePair, "Domain");
			dmnDto = dbUtils.convertDomainJsonToJavaObject(dbData, dmnDto);
		} else if (functionality.equals("updateDomain")) {
			String removeDelMgrs[] = excelAccountData.get("Remove Delivery Managers").replaceAll("\n", "").split(",");
			Map<String, String> actionsEditLocator = MyTeamUtils.getEntireRowOrActionsColumn(
					excelAccountData.get("Account Name"), driver, ManageAccountLocators.accountTable,
					ManageAccountLocators.accountHeaders, "ActionsColumn", "Edit");
			Entry<String, String> actionsLocator = actionsEditLocator.entrySet().iterator().next();
			manageDomainPage.updateDomain(actionsLocator.getValue(), excelAccountData.get("Domain Name"), addDelMgrs,
					removeDelMgrs);
			dbData = getDbRecord(colNameValuePair, "Domain");
			dmnDto = dbUtils.convertDomainJsonToJavaObject(dbData, dmnDto);
		}
		Map<String, String> uiData = MyTeamUtils.getEntireRowOrActionsColumn(excelAccountData.get("Domain Name"),
				driver, ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders, "EntireRow", "");
		String uiDelMgrs[] = uiData.get("Delivery Managers").replaceAll("\n", "").split(",");
		String inputEmpIds = getEmpIds(addDelMgrs, empDto);
		String dbEmpIds = getEmpIds(uiDelMgrs, empDto);
		dbUtils.compareDomainWithDb(dmnDto, uiData, empDto, inputEmpIds, dbEmpIds);
		return result = true;
	}

	private Boolean addOrUpdate(String moduleName, String functionality) {

		Boolean result = false;
		Map<String, String> excelAccountData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), functionality);
		dbUtils = new MyTeamDbUtils();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		if (moduleName.equals("Domain")) {
			manageDomainPage = new ManageDomainPage(driver);
			manageDomainPage.clickOnManageDomainModule();
			if (functionality.equals("addDomain")) {
				result = addOrUpdateDomain(functionality, excelAccountData);
			} else if (functionality.equals("updateDomain")) {
				result = addOrUpdateDomain(functionality, excelAccountData);
			}
		}
		return result;
	}

	private Map<String, String> readDataFromExcel(String functionality) {
		Map<String, String> excelData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), functionality);
		return excelData;
	}

	
	@Test(priority = 1)
	public void tc001_AddDomainInManageDomainsModule() {
		Boolean result = false;
		initLogin();
		result = addOrUpdate("Domain", "addDomain");
		if (result) {
			Assert.assertTrue(result, "Domain is successfully added");
		} else {
			Assert.assertFalse(result, "Domain is not added");
		}
	}
	 

	@Test(priority = 2)
	public void tc002_UpdateDomainInManageDomainsModule() {
		Boolean result = false;
		initLogin();
		result = addOrUpdate("Domain", "updateDomain");
		if (result) {
			Assert.assertTrue(result, "Domain is successfully updated");
		} else {
			Assert.assertFalse(result, "Domain is not updated");
		}
	}

	@Test(priority = 3)
	public void tc003_VerifyErrorMessage_Please_Select_A_Account() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageDomainPage = new ManageDomainPage(driver);
		manageDomainPage.clickOnManageDomainModule();
		result = manageDomainPage.validateSelectAccountErrMsg("Please select a Account");
		if (result) {
			Assert.assertTrue(result, "'Please select a Account' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Please select a Account' error message is not displayed");
		}

	}

	@Test(priority = 4)
	public void tc004_VerifyErrorMessage_Please_Enter_A_DomainName() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageDomainPage = new ManageDomainPage(driver);
		manageDomainPage.clickOnManageDomainModule();
		Map<String, String> excelData = readDataFromExcel("addProject");
		result = manageDomainPage.validateDomainNameErrMsg(excelData.get("Account"), "Please enter a Domain Name");
		if (result) {
			Assert.assertTrue(result, "'Please enter a Domain Name' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Please enter a Domain Name' error message is not displayed");
		}

	}

	@Test(priority = 5)
	public void tc004_VerifyErrorMessage_Please_Select_A_DeliveryManagers() {
		Boolean result = false;
		initLogin();
		manageGroupPage = new ManageGroupPage(driver);
		manageGroupPage.clickOnManageGroupModule();
		manageDomainPage = new ManageDomainPage(driver);
		manageDomainPage.clickOnManageDomainModule();
		Map<String, String> excelData = readDataFromExcel("addProject");
		result = manageDomainPage.validateDelLeadsErrMsg(excelData.get("Account"), "Please select a deliveryManagers");
		if (result) {
			Assert.assertTrue(result, "'Please select a deliveryManagers' error message displayed as expected");
		} else {
			Assert.assertFalse(result, "Please select a deliveryManagers' error message is not displayed");
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
