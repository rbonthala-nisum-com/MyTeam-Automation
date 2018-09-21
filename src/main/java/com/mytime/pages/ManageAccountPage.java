package com.mytime.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.mytime.locators.ManageAccountLocators;
import com.mytime.locators.ManageDropDownLocators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;
import com.nisum.qa.automation.components.TimeOutMethods;

public class ManageAccountPage extends Clicks {

	TextField enterText = new TextField(driver);
	public ManageAccountPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnManageAccountModule()
	{
		userClick(ManageAccountLocators.manageAccountModule, TimeOutMethods.waitTime10Seconds);
	}
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
			for (int i = 0; i < delManager.length; i++) {
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
			userClick(ManageAccountLocators.btnSubmitAddAccount, TimeOutMethods.waitTime10Seconds);
			String successMsg = enterText.userGetTextFromWebElement(ManageAccountLocators.addAccountSuccessMessage, TimeOutMethods.waitTime10Seconds);
			Assert.assertEquals("Account assigned successfully", successMsg);
			userClick(ManageAccountLocators.btnOk, TimeOutMethods.waitTime10Seconds);
			sleepInSeconds(2000);
			// userClick(ManageAccountLocators.pageRefresh,TimeOutMethods.waitTime10Seconds);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateAccount(String btnUpdateAccount,String accName,String industryType,String clientAddr,String delManager[],String removeDelMgr[])
	{
		userClick(By.xpath(btnUpdateAccount), waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, "","clearText",TimeOutMethods.waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, accName,"sendkeys",TimeOutMethods.waitTime10Seconds);
		userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);

		userClick(ManageDropDownLocators.industryType(industryType), TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(2000);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtClientAddress,"","clearText",TimeOutMethods.waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageAccountLocators.txtClientAddress,clientAddr,"sendKeys",TimeOutMethods.waitTime10Seconds);
		for(int j=0;j<removeDelMgr.length;j++) {
			userClick(ManageDropDownLocators.delManager(removeDelMgr[j]), waitTime10Seconds);
		}
		userClick(ManageAccountLocators.dropDeliveryManagersField, TimeOutMethods.waitTime10Seconds);
		for (int i = 0; i < delManager.length; i++) {
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
		userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
		String successMessage = enterText.userGetTextFromWebElement(ManageAccountLocators.updateAccountSuccessMessage, waitTime10Seconds);
		Assert.assertEquals(successMessage, "Account updated successfully");
		userClick(ManageAccountLocators.btnOk, waitTime10Seconds);
		sleepInSeconds(3000);

	}
}
