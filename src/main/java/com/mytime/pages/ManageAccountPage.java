package com.mytime.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.mytime.locators.ManageAccountLocators;
import com.mytime.locators.MangeDropDownLocators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;
import com.nisum.qa.automation.components.TimeOutMethods;

public class ManageAccountPage extends Clicks {

	public ManageAccountPage(WebDriver driver) {
		super(driver);
	}

	public void addAccount(String accName, String industryType, String clientAddr, String[] delManager) {
		TextField enterText = new TextField(driver);
		try {
			userClick(ManageAccountLocators.manageAccountModule, TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.btnAddAccount, TimeOutMethods.waitTime10Seconds);
			sleepInSeconds(2000);
			enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, accName,
					TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);

			userClick(MangeDropDownLocators.industryType(industryType), TimeOutMethods.waitTime10Seconds);
			sleepInSeconds(2000);
			enterText.userTypeIntoTextField(ManageAccountLocators.txtClientAddress, clientAddr,
					TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.dropDeliveryManagersField, TimeOutMethods.waitTime10Seconds);
			for (int i = 0; i < delManager.length; i++) {
				enterText.userTypeIntoTextField(ManageAccountLocators.txtDeliveryManagerSearch, delManager[i],
						TimeOutMethods.waitTime10Seconds);
				sleepInSeconds(2000);
				userClick(ManageAccountLocators.chkDeliveryManager, TimeOutMethods.waitTime10Seconds);
				sleepInSeconds(2000);
				if (i != delManager.length-1) {
					enterText.userClearTheTextField(ManageAccountLocators.txtDeliveryManagerSearch,
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
}
