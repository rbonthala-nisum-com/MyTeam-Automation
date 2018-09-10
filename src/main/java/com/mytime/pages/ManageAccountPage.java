package com.mytime.pages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.ManageAccountLocators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;
import com.nisum.qa.automation.components.TimeOutMethods;

public class ManageAccountPage extends Clicks {

	public ManageAccountPage(WebDriver driver) {
		super(driver);
	}

	public void add_Account(WebDriver driver, String accName, String clientAddr, String delManager) {
		try {
			Clicks.userClick(ManageAccountLocators.manageAccountModule, TimeOutMethods.waitTime10Seconds);
			Clicks.userClick(ManageAccountLocators.btnAddAccount, TimeOutMethods.waitTime10Seconds);
			Thread.sleep(2000);
			new TextField(driver).userTypeIntoTextField(ManageAccountLocators.txtAccountName, accName,
					TimeOutMethods.waitTime10Seconds);
			Clicks.userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);
			new TextField(driver).userTypeIntoTextField(ManageAccountLocators.txtClientAddress, clientAddr,
					TimeOutMethods.waitTime10Seconds);
			Clicks.userClick(ManageAccountLocators.dropDeliveryManagersField, TimeOutMethods.waitTime10Seconds);
			new TextField(driver).userTypeIntoTextField(ManageAccountLocators.txtDeliveryManagerSearch, delManager,
					TimeOutMethods.waitTime10Seconds);
			Clicks.userClick(ManageAccountLocators.chkDeliveryManager, TimeOutMethods.waitTime10Seconds);
			Clicks.userClick(ManageAccountLocators.btnSubmitAddAccount, TimeOutMethods.waitTime10Seconds);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
