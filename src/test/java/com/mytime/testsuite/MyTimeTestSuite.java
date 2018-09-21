package com.mytime.testsuite;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
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

/**
 * This will contain all test cases belongs to myTime
 */
public class MyTimeTestSuite extends WebDriverInitialization {

	LoginPage login;
	WelcomePage welcomePage;

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

	@Test(priority = 2)
	public void addAccount() {
		initLogin();
		AccountDTO dto = new AccountDTO();
		EmployeeDTO empDto = new EmployeeDTO();
		MyTeamDbUtils dbUtils = new MyTeamDbUtils();
		Map<String, String> excelData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), "AddAccount");
		List<String> acctData = excelData.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
		List<String> acctCols = excelData.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
		// AccountData account = new AccountData();
		ManageGroupPage manageGroupPage = new ManageGroupPage(driver);
		ManageAccountPage manageAccountPage = new ManageAccountPage(driver);

		// ExcelUtils excel = new ExcelUtils();
		manageGroupPage.clickOnManageGroupModule();
		String delManagers[] = acctData.get(2).split(",");
		manageAccountPage.addAccount(acctData.get(0), acctData.get(1), acctData.get(3), delManagers);
		Map<String, String> uiAccData = MyTeamUtils.accoutRow(acctData.get(0), driver,
				ManageAccountLocators.accountTable, ManageAccountLocators.accountHeaders);

		Map<String, String> colNameValuePair = new HashedMap<String, String>();
		colNameValuePair.put(acctCols.get(0), acctData.get(0));
		String dbAcctData = getDbRecord(colNameValuePair,"Accounts");
		colNameValuePair.clear();
		String dbEmpData = "";
		String empId = "";
		for(int i=0;i<delManagers.length;i++){
			colNameValuePair.put("EmployeeName", delManagers[i]);
			if(i==delManagers.length-1) {
			dbEmpData = getDbRecord(colNameValuePair,"EmployeeDetails");
			empDto = dbUtils.convertEmpJsonToJavaObject(dbEmpData, empDto);
			empId = empId + empDto.getEmployeeId();
			}else {
				dbEmpData = getDbRecord(colNameValuePair,"EmployeeDetails");
				empDto = dbUtils.convertEmpJsonToJavaObject(dbEmpData, empDto);
				empId = empId + empDto.getEmployeeId() +",";
			}
		}
		dto = dbUtils.convertAccountJsonToJavaObject(dbAcctData, dto);
		dbUtils.compareAccountWithDb(dto, uiAccData, empDto, empId.split(","));
	}

	@AfterMethod
	public void tearDown() {
		getDriver().close();
	}

}
