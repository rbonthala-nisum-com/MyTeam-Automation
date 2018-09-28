package com.mytime.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.mytime.locators.ManageAccountLocators;
import com.mytime.locators.ManageDropDownLocators;
import com.mytime.util.ManagePageUtils;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;
import com.nisum.qa.automation.components.TimeOutMethods;

public class ManageAccountPage extends Clicks {

	TextField enterText = new TextField(driver);
	String successMsg = "";
	Boolean result = false;
	ManagePageUtils pageUtils = new ManagePageUtils(driver);
	public ManageAccountPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnManageAccountModule()
	{
		userClick(ManageAccountLocators.manageAccountModule, TimeOutMethods.waitTime10Seconds);
	}
	private void addOrRemoveDeliveryManagers(String delManager[]) {
		for (int i = 0; i < delManager.length; i++) {
			enterText.userTypeIntoTextField(ManageAccountLocators.txtDeliveryManagerSearch,"","clearText",
					TimeOutMethods.waitTime10Seconds);
			enterText.userTypeIntoTextField(ManageAccountLocators.txtDeliveryManagerSearch,delManager[i],"sendKeys", 
					TimeOutMethods.waitTime10Seconds);
			sleepInSeconds(2000);
			userClick(ManageAccountLocators.chkDeliveryManager, TimeOutMethods.waitTime10Seconds);
			sleepInSeconds(2000);
			if (i != delManager.length-1) {
				enterText.userTypeIntoTextField(ManageAccountLocators.txtDeliveryManagerSearch,"","clearText",
						TimeOutMethods.waitTime10Seconds);
				sleepInSeconds(1000);
			}
		}
	}
	/*private void removeDeliveryMgr(String[] removeDelMgr) {
		for(int i=0;i<removeDelMgr.length;i++) {
			userClick(ManageDropDownLocators.removeDelManager(removeDelMgr[i]), waitTime10Seconds);
		}
	}*/
	public void addAccount(String accName, String industryType, String clientAddr, String[] delManager) {
		try {
			userClick(ManageAccountLocators.btnAddAccount, TimeOutMethods.waitTime10Seconds);
			sleepInSeconds(2000);
			enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, accName,"sendKeys",TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);

			userClick(ManageDropDownLocators.industryType(industryType), TimeOutMethods.waitTime10Seconds);
			sleepInSeconds(2000);
			enterText.userTypeIntoTextField(ManageAccountLocators.txtClientAddress,clientAddr,"sendKeys",TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.dropDeliveryManagersField, TimeOutMethods.waitTime10Seconds);
			addOrRemoveDeliveryManagers(delManager);
			userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
			pageUtils.verifyMessage("Account assigned successfully",ManageAccountLocators.btnOk,ManageAccountLocators.addAccountSuccessMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateAccount(String btnUpdateAccount,String accName,String industryType,String clientAddr,String delManager[],String removeDelMgr[])
	{
		userClick(By.xpath(btnUpdateAccount), waitTime10Seconds);
		sleepInSeconds(2000);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, "","clearText",TimeOutMethods.waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, accName,"sendkeys",TimeOutMethods.waitTime10Seconds);
		userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);
		userClick(ManageDropDownLocators.industryType(industryType), TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(2000);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtClientAddress,"","clearText",TimeOutMethods.waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtClientAddress,clientAddr,"sendKeys",TimeOutMethods.waitTime10Seconds);
		userClick(ManageAccountLocators.dropDeliveryManagersField, TimeOutMethods.waitTime10Seconds);
		addOrRemoveDeliveryManagers(delManager);
		userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
		pageUtils.verifyMessage("Account updated successfully",ManageAccountLocators.btnOk,ManageAccountLocators.updateAccountSuccessMessage);
	}

	/*public Boolean validateAddAccountErrorMessage(String expectedMsg) {
		userClick(ManageAccountLocators.btnAddAccount, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(2000);
		if(expectedMsg.equals("Please enter the account Name")) {
			userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
			successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.accountNameErrorMsg, waitTime10Seconds);
			Assert.assertEquals(expectedMsg, successMsg);
			result = true;
		}else if(expectedMsg.equals("Please enter the industry type")) {
			enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, "TestAccount", "clearText",TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
			successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.industryTypeErrorMsg, waitTime10Seconds);
			Assert.assertEquals(expectedMsg, successMsg);
			result = true;
		}else if(expectedMsg.equals("Please enter the client address")) {
			enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, "TestAccount", "clearText",TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);
			userClick(ManageDropDownLocators.industryType("Retail"), TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
			successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.industryTypeErrorMsg, waitTime10Seconds);
			Assert.assertEquals(expectedMsg, successMsg);
			result = true;
		}else if(expectedMsg.equals("Please select a delivery Manager")) {
			enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, "TestAccount", "clearText",TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);
			userClick(ManageDropDownLocators.industryType("Retail"), TimeOutMethods.waitTime10Seconds);
			enterText.userTypeIntoTextField(ManageAccountLocators.txtClientAddress,"Address","sendKeys",TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
			successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.industryTypeErrorMsg, waitTime10Seconds);
			Assert.assertEquals(expectedMsg, successMsg);
			result = true;
		}
		
		return result;
	}
	*/
	public Boolean validateAccountNameErrMsg(String expectedMsg) {
		userClick(ManageAccountLocators.btnAddAccount, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(1000);
		if(expectedMsg.equals("Please enter the account Name")) {
			userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
			successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.accountNameErrorMsg, waitTime10Seconds);
			Assert.assertEquals(expectedMsg, successMsg);
			result = true;
		}else if(expectedMsg.equals("Please enter alphabets only")) {
			enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, "123", "clearText",TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
			successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.accountNameErrorMsg, waitTime10Seconds);
			Assert.assertEquals(expectedMsg, successMsg);
			result = true;
		}
		userClick(ManageAccountLocators.btnCancel, waitTime10Seconds);
		return result;
	}
	
	public Boolean validateIndTypeErrMsg(String expectedMsg) {
		userClick(ManageAccountLocators.btnAddAccount, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(1000);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, "TestAccount", "clearText",TimeOutMethods.waitTime10Seconds);
		userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
		successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.industryTypeErrorMsg, waitTime10Seconds);
		if(expectedMsg.equals(successMsg)) {
			Assert.assertEquals(expectedMsg, successMsg);
			result = true;
		}
		userClick(ManageAccountLocators.btnCancel, waitTime10Seconds);
		userClick(ManageAccountLocators.btnOkOnCancelPopUp, waitTime10Seconds);
		return result;
	}
	
	public Boolean validateClientAddrErrMsg(String expectedMsg) {
		userClick(ManageAccountLocators.btnAddAccount, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(1000);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, "TestAccount", "clearText",TimeOutMethods.waitTime10Seconds);
		userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);
		userClick(ManageDropDownLocators.industryType("Retail"), TimeOutMethods.waitTime10Seconds);
		userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
		successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.industryTypeErrorMsg, waitTime10Seconds);
		if(expectedMsg.equals(successMsg)) {
			Assert.assertEquals(expectedMsg, successMsg);
			result = true;
		}
		userClick(ManageAccountLocators.btnCancel, waitTime10Seconds);
		userClick(ManageAccountLocators.btnOkOnCancelPopUp, waitTime10Seconds);
		return result;
	}
	public Boolean validateDelMgrsErrMsg(String expectedMsg) {
		userClick(ManageAccountLocators.btnAddAccount, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(1000);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, "TestAccount", "clearText",TimeOutMethods.waitTime10Seconds);
		userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);
		userClick(ManageDropDownLocators.industryType("Retail"), TimeOutMethods.waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtClientAddress,"Address","sendKeys",TimeOutMethods.waitTime10Seconds);
		userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
		successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.industryTypeErrorMsg, waitTime10Seconds);
		if(expectedMsg.equals(successMsg)) {
			Assert.assertEquals(expectedMsg, successMsg);
			result = true;
		}
		userClick(ManageAccountLocators.btnCancel, waitTime10Seconds);
		userClick(ManageAccountLocators.btnOkOnCancelPopUp, waitTime10Seconds);
		return result;
	}
}
