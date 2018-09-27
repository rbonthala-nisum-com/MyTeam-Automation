package com.mytime.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.mytime.locators.ManageDomainLocators;
import com.mytime.locators.ManageDropDownLocators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;
import com.nisum.qa.automation.components.TimeOutMethods;

/**
 * @author Nisum
 *
 */
public class ManageDomainPage extends Clicks{

	TextField enterText = new TextField(driver);
	public ManageDomainPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickOnManageDomainModule() {
		userClick(ManageDomainLocators.manageDomainModule, waitTime10Seconds);
	}
	
	public void addDomain(String account, String domainName, String dropDelLead[]) {
		userClick(ManageDomainLocators.btnAddDomain, waitTime10Seconds);
		userClick(ManageDomainLocators.dropAccountFiled, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(account), waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageDomainLocators.txtDomainName, domainName, "sendKeys", waitTime10Seconds);
		userClick(ManageDomainLocators.dropDeliveryLeadField, waitTime10Seconds);
		for(int i=0;i<dropDelLead.length;i++) {
			enterText.userTypeIntoTextField(ManageDomainLocators.txtDeliveryLead, dropDelLead[i], "sendKeys", waitTime10Seconds);
			sleepInSeconds(2000);
			userClick(ManageDomainLocators.chkDeliveryLead, waitTime10Seconds);
			sleepInSeconds(2000);
			if(i!=dropDelLead.length-1) {
				enterText.userTypeIntoTextField(ManageDomainLocators.txtDeliveryLead, "", "clearText", waitTime10Seconds);
			}
		}
		userClick(ManageDomainLocators.btnSubmitAdddomain, waitTime10Seconds);
		String successMsg = enterText.userGetTextFromWebElement(ManageDomainLocators.addDomainSuccessMessage, TimeOutMethods.waitTime10Seconds);
		Assert.assertEquals("Domain created successfully", successMsg);
		userClick(ManageDomainLocators.btnOk, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(2000);	
	}
	
	public void updateDomain(String btnUpdateDomain, String domainName, String dropDelLead[],String removeDelLead[]) {
		userClick(By.xpath(btnUpdateDomain), waitTime10Seconds);
		userClick(ManageDomainLocators.dropAccountFiled, waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageDomainLocators.txtDomainName, domainName, "clearText", waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageDomainLocators.txtDomainName, domainName, "sendKeys", waitTime10Seconds);
		for(int j=0;j<removeDelLead.length;j++) {
			userClick(ManageDropDownLocators.delManager(removeDelLead[j]), waitTime10Seconds);
		}
		userClick(ManageDomainLocators.dropDeliveryLeadField, waitTime10Seconds);
		for(int i=0;i<dropDelLead.length;i++) {
			enterText.userTypeIntoTextField(ManageDomainLocators.txtDeliveryLead, dropDelLead[i], "sendKeys", waitTime10Seconds);
			sleepInSeconds(2000);
			userClick(ManageDomainLocators.chkDeliveryLead, waitTime10Seconds);
			sleepInSeconds(2000);
			if(i!=dropDelLead.length-1) {
				enterText.userTypeIntoTextField(ManageDomainLocators.txtDeliveryLead, "", "clearText", waitTime10Seconds);
			}
		}
		userClick(ManageDomainLocators.btnSubmitAdddomain, waitTime10Seconds);
		String successMsg = enterText.userGetTextFromWebElement(ManageDomainLocators.addDomainSuccessMessage, TimeOutMethods.waitTime10Seconds);
		Assert.assertEquals("Domain updated successfully", successMsg);
		userClick(ManageDomainLocators.btnOk, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(2000);	
	}
	
}
