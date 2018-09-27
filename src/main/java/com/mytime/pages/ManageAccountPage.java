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
			pageUtils.verifySuccessMessage("Account assigned successfully",ManageAccountLocators.btnOk,ManageAccountLocators.addAccountSuccessMessage);

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
		userClick(ManageAccountLocators.dropDeliveryManagersField, TimeOutMethods.waitTime10Seconds);
		addOrRemoveDeliveryManagers(removeDelMgr);
		addOrRemoveDeliveryManagers(delManager);
		userClick(ManageAccountLocators.btnSubmitAddAccount, waitTime10Seconds);
		pageUtils.verifySuccessMessage("Account updated successfully",ManageAccountLocators.btnOk,ManageAccountLocators.updateAccountSuccessMessage);
	}
}
