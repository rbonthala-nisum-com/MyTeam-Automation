package com.mytime.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mytime.locators.ManageAccountLocators;
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
	public void addProject(String projectName, String account, String domain, String delLead, String prjStatus, String prjStartdate, String prjEndDate) {
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
		userClick(ManageDropDownLocators.dateSelection(prjStartdate),waitTime10Seconds);
		userClick(ManageProjectLocators.dateProjectEndDate, waitTime10Seconds);
		userClick(ManageDropDownLocators.dateSelection(prjEndDate), waitTime10Seconds);
		userClick(ManageProjectLocators.btnSubmitAddProject, waitTime10Seconds);
		pageUtils.verifySuccessMessage("Project created successfully", ManageProjectLocators.btnOk, ManageProjectLocators.addProjectSuccessMessage);
		
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
		pageUtils.verifySuccessMessage("Project updated successfully", ManageProjectLocators.btnOk, ManageProjectLocators.updateProjectSuccessMessage);
		
	}
}
