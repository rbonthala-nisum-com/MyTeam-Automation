package com.mytime.pages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.ManageAccountLocators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.DropDown;
import com.nisum.qa.automation.components.TextField;
import com.nisum.qa.automation.components.TimeOutMethods;
import com.mytime.locators.MangeDropDownLocators;

public class ManageAccountPage extends Clicks {

	public ManageAccountPage(WebDriver driver) {
		super(driver);
	}

	public void addAccount(String accName, String industryType, String clientAddr, String delManager) {
		TextField enterText = new TextField(driver);
		try {
			userClick(ManageAccountLocators.manageAccountModule, TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.btnAddAccount, TimeOutMethods.waitTime10Seconds);
			Thread.sleep(2000);
			enterText.userTypeIntoTextField(ManageAccountLocators.txtAccountName, accName,
					TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.dropIndustryTypeFiled, TimeOutMethods.waitTime10Seconds);
			
			userClick(MangeDropDownLocators.industryType(industryType), TimeOutMethods.waitTime10Seconds);
			//userClick(ManageDropDowns.dropIndustryTypeValue,TimeOutMethods.waitTime10Seconds);
			enterText.userTypeIntoTextField(ManageAccountLocators.txtClientAddress, clientAddr,
					TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.dropDeliveryManagersField, TimeOutMethods.waitTime10Seconds);
			enterText.userTypeIntoTextField(ManageAccountLocators.txtDeliveryManagerSearch, delManager,
					TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.chkDeliveryManager, TimeOutMethods.waitTime10Seconds);
			userClick(ManageAccountLocators.btnSubmitAddAccount, TimeOutMethods.waitTime10Seconds);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
