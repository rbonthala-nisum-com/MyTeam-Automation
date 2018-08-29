package com.mytime.applicationpages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.DropFiledValue_Locators;
import com.mytime.locators.ManageAccount_Locators;
import com.mytime.reusablemethods.ReusableMethods;

public class ManageAccount_Page extends ReusableMethods{

	public ManageAccount_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	ReusableMethods rMethods = new ReusableMethods(driver);
//	WebElement element = HomePage.element;

	public void add_Account(WebDriver driver, String accName, String clientAddr, String delManager) {
		try {
			rMethods.userClick(ManageAccount_Locators.manageAccountModule, 10);
			rMethods.userClick(ManageAccount_Locators.btnAddAccount, 10);
			Thread.sleep(2000);
			rMethods.userTypeIntoTextField(ManageAccount_Locators.txtAccountName, accName, 10);
			rMethods.userClick(ManageAccount_Locators.dropIndustryTypeFiled, 5);
			rMethods.userClick(DropFiledValue_Locators.dropIndustryTypeValue, 5);
			Thread.sleep(2000);
			rMethods.userTypeIntoTextField(ManageAccount_Locators.txtClientAddress, clientAddr, 10);
			rMethods.userClick(ManageAccount_Locators.dropDeliveryManagersField, 10);
			rMethods.userTypeIntoTextField(ManageAccount_Locators.txtDeliveryManagerSearch, delManager, 10);
			Thread.sleep(5000);
			rMethods.userClick(ManageAccount_Locators.chkDeliveryManager, 10);
			rMethods.userClick(ManageAccount_Locators.btnSubmitAddAccount, 10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
