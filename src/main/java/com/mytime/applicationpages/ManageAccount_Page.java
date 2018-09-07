package com.mytime.applicationpages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.ManageAccount_Locators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;

public class ManageAccount_Page extends Clicks{

	public ManageAccount_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	public void add_Account(WebDriver driver, String accName, String clientAddr, String delManager) {
		try {
			Clicks.userClick(ManageAccount_Locators.manageAccountModule, 10);
			Clicks.userClick(ManageAccount_Locators.btnAddAccount, 10);
			Thread.sleep(2000);
			new TextField(driver).userTypeIntoTextField(ManageAccount_Locators.txtAccountName, accName, 10);
			Clicks.userClick(ManageAccount_Locators.dropIndustryTypeFiled, 5);
			Thread.sleep(2000);
			new TextField(driver).userTypeIntoTextField(ManageAccount_Locators.txtClientAddress, clientAddr, 10);
			Clicks.userClick(ManageAccount_Locators.dropDeliveryManagersField, 10);
			new TextField(driver).userTypeIntoTextField(ManageAccount_Locators.txtDeliveryManagerSearch, delManager, 10);
			Thread.sleep(5000);
			Clicks.userClick(ManageAccount_Locators.chkDeliveryManager, 10);
			Clicks.userClick(ManageAccount_Locators.btnSubmitAddAccount, 10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
