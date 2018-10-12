package com.mytime.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.mytime.locators.ManageDropDownLocators;
import com.mytime.locators.ManageProjectLocators;
import com.mytime.util.ManagePageUtils;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;
import com.nisum.qa.automation.components.TimeOutMethods;

public class ManageProjectPage extends Clicks {

	TextField enterText = new TextField(driver);
	ManagePageUtils pageUtils = new ManagePageUtils(driver);
	public ManageProjectPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnManageProjectModule()
	{
		userClick(ManageProjectLocators.manageProjectModule, TimeOutMethods.waitTime10Seconds);
	}
	public void addProject(String projectName, String account, String domain, String delLead, String prjStatus, String prjStartDate, String prjEndDate) {
		userClick(ManageProjectLocators.btnAddProject, waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageProjectLocators.txtProjectName, projectName, "sendKeys", waitTime10Seconds);
		userClick(ManageProjectLocators.dropAccount, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(account), waitTime10Seconds);
		userClick(ManageProjectLocators.dropDomain, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(domain), waitTime10Seconds);
		userClick(ManageProjectLocators.dropDelLead, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(delLead), waitTime10Seconds);
		userClick(ManageProjectLocators.dropProjectStatus, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(prjStatus), waitTime10Seconds);
		userClick(ManageProjectLocators.dateProjectStartDate, waitTime10Seconds);
		userClick(ManageDropDownLocators.dateSelection(prjStartDate),waitTime10Seconds);
		userClick(ManageProjectLocators.dateProjectEndDate, waitTime10Seconds);
		userClick(ManageDropDownLocators.dateSelection(prjEndDate), waitTime10Seconds);
		userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
		pageUtils.verifyMessage("Project created successfully", ManageProjectLocators.btnOk, ManageProjectLocators.addProjectSuccessMessage);
		
	}
	
	public void updateProject(String btnUpdateProject, String projectName, String account, String domain, String delLead, String prjStatus, String prjStartdate, String prjEndDate) {
		userClick(By.xpath(btnUpdateProject), waitTime10Seconds);
		enterText.userTypeIntoTextField(ManageProjectLocators.txtProjectName, projectName, "sendKeys", waitTime10Seconds);
		userClick(ManageProjectLocators.dropAccount, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(account), waitTime10Seconds);
		userClick(ManageProjectLocators.dropDomain, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(domain), waitTime10Seconds);
		userClick(ManageProjectLocators.dropDelLead, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(delLead), waitTime10Seconds);
		userClick(ManageProjectLocators.dropProjectStatus, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(prjStatus), waitTime10Seconds);
		userClick(ManageProjectLocators.dateProjectStartDate, waitTime10Seconds);
		userClick(ManageDropDownLocators.dateSelection(prjStartdate),waitTime10Seconds);
		userClick(ManageProjectLocators.dateProjectEndDate, waitTime10Seconds);
		userClick(ManageDropDownLocators.dateSelection(prjEndDate), waitTime10Seconds);
		userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
		pageUtils.verifyMessage("Project updated successfully", ManageProjectLocators.btnOk, ManageProjectLocators.updateProjectSuccessMessage);
		
	}
	
	public Boolean validateProjectNameErrMsg(String expectedMsg) {
		Boolean result = false;
		String actualMsg = "";
		if(expectedMsg.equals("Project Name is mandatory")) {
			userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
			actualMsg = enterText.userGetTextFromWebElement(ManageProjectLocators.addPrjPopUpErrorMessage, waitTime10Seconds);
			if(expectedMsg.equals(actualMsg)) {
				Assert.assertEquals(expectedMsg, actualMsg);
				result = true;
				enterText.userTypeIntoTextField(ManageProjectLocators.txtProjectName, "", "clearText", waitTime10Seconds);
			}
		}else if(expectedMsg.equals("Please enter alphabets only")){
			enterText.userTypeIntoTextField(ManageProjectLocators.txtProjectName, "123", "sendKeys", waitTime10Seconds);
			userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
			actualMsg = enterText.userGetTextFromWebElement(ManageProjectLocators.addPrjPopUpErrorMessage, waitTime10Seconds);
			if(expectedMsg.equals(actualMsg)) {
				Assert.assertEquals(expectedMsg, actualMsg);
				result = true;
				enterText.userTypeIntoTextField(ManageProjectLocators.txtProjectName, "", "clearText", waitTime10Seconds);
			}
		}
		if(expectedMsg.equals(actualMsg)) {
			Assert.assertEquals(expectedMsg, actualMsg);
			result = true;
		}
		return result;
	}
	
	public Boolean validateSelectAccountErrMsg(String expectedMsg) {
		Boolean result = false;
		enterText.userTypeIntoTextField(ManageProjectLocators.txtProjectName, "projectName", "sendKeys", waitTime10Seconds);
		userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
		String actualMsg = enterText.userGetTextFromWebElement(ManageProjectLocators.addPrjPopUpErrorMessage, waitTime10Seconds);
		if(expectedMsg.equals(actualMsg)) {
			Assert.assertEquals(expectedMsg, actualMsg);
			result = true;
		}
		return result;
	}
	
	public Boolean validateSelectDomainErrMsg(String expectedMsg, String account) {
		Boolean result = false;
		userClick(ManageProjectLocators.dropAccount, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(account), waitTime10Seconds);
		userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
		String actualMsg = enterText.userGetTextFromWebElement(ManageProjectLocators.addPrjPopUpErrorMessage, waitTime10Seconds);
		if(expectedMsg.equals(actualMsg)) {
			Assert.assertEquals(expectedMsg, actualMsg);
			result = true;
		}
		return result;
	}
	
	public Boolean validateSelectDelLeadErrMsg(String expectedMsg, String domain) {
		Boolean result = false;
		userClick(ManageProjectLocators.dropDomain, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(domain), waitTime10Seconds);
		userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
		String actualMsg = enterText.userGetTextFromWebElement(ManageProjectLocators.addPrjPopUpErrorMessage, waitTime10Seconds);
		if(expectedMsg.equals(actualMsg)) {
			Assert.assertEquals(expectedMsg, actualMsg);
			result = true;
		}
		return result;
	}
	
	public Boolean validateSelectPrjStatusErrMsg(String expectedMsg, String delLead) {
		Boolean result = false;
		userClick(ManageProjectLocators.dropDelLead, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(delLead), waitTime10Seconds);
		userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
		String actualMsg = enterText.userGetTextFromWebElement(ManageProjectLocators.addPrjPopUpErrorMessage, waitTime10Seconds);
		if(expectedMsg.equals(actualMsg)) {
			Assert.assertEquals(expectedMsg, actualMsg);
			result = true;
		}
		return result;
	}
	
	public Boolean validateSelectPrjStartDateErrMsg(String expectedMsg, String prjStatus, String prjStartDate, String prjEndDate) {
		Boolean result = false;
		userClick(ManageProjectLocators.dropProjectStatus, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(prjStatus), waitTime10Seconds);
		if(expectedMsg.equals("Please select project start date")) {
			userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
			String actualMsg = enterText.userGetTextFromWebElement(ManageProjectLocators.addPrjPopUpErrorMessage, waitTime10Seconds);
			if(expectedMsg.equals(actualMsg)) {
				Assert.assertEquals(expectedMsg, actualMsg);
				result = true;
			}
		}else if(expectedMsg.equals("Start date should not be greater than end date")) {
			userClick(ManageProjectLocators.dateProjectStartDate, waitTime10Seconds);
			userClick(ManageDropDownLocators.dateSelection(prjStartDate),waitTime10Seconds);
			userClick(ManageProjectLocators.dateProjectEndDate, waitTime10Seconds);
			userClick(ManageDropDownLocators.dateSelection(prjEndDate), waitTime10Seconds);
			userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
			String actualMsg = enterText.userGetTextFromWebElement(ManageProjectLocators.addPrjPopUpErrorMessage, waitTime10Seconds);
			if(expectedMsg.equals(actualMsg)) {
				Assert.assertEquals(expectedMsg, actualMsg);
				result = true;
			}
		}
		
		return result;
	}
	
	public Boolean validateSelectPrjEndDateErrMsg(String expectedMsg, String prjStartDate) {
		Boolean result = false;
		userClick(ManageProjectLocators.dateProjectStartDate, waitTime10Seconds);
		userClick(ManageDropDownLocators.dateSelection(prjStartDate),waitTime10Seconds);
		userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
		String actualMsg = enterText.userGetTextFromWebElement(ManageProjectLocators.addPrjPopUpErrorMessage, waitTime10Seconds);
		if(expectedMsg.equals(actualMsg)) {
			Assert.assertEquals(expectedMsg, actualMsg);
			result = true;
		}
		return result;
	}

}
