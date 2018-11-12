package com.mytime.locators;

import org.openqa.selenium.By;

public interface ManageProjectLocators {

	By manageProjectModule = By.xpath("//span[text()='Manage Projects ']");
	By btnAddProject = By.xpath("//div[@class='col-lg-8']/button");
	By txtProjectName = By.id("projectName");
	By dropAccount = By.id("account");
	By dropDomain = By.id("domain");
	By dropDelLead = By.id("deliveryLead");
	By dropProjectStatus = By.id("projectStatus");
	By dateProjectStartDate = By.xpath("//md-datepicker[@id='projectStartDate']/button");
	By dateProjectEndDate = By.xpath("//md-datepicker[@id='projectEndDate']/button");
	By btnSubmitAddProject = By.xpath("//md-dialog-actions/button[1]");
	By projectTable = By.xpath("//div[@class='ui-grid-viewport ng-isolate-scope']/div");
	By projectHeaders = By.xpath("//span[@class='ui-grid-header-cell-label ng-binding']");
	By btnOk = By.xpath("//button[text()='Ok']");
	By addProjectSuccessMessage = By.xpath("//*[contains(@class , 'ng-binding')and text()='Project created successfully']");
	By updateProjectSuccessMessage = By.xpath("//*[contains(@class , 'ng-binding')and text()='Project updated successfully']");
	By projectNameErrMsg = By.xpath("//*[contains(@class , 'ng-binding')and text()='Project Name is mandatory']");
	By addPrjPopUpErrorMessage = By.xpath("//span[@class='error ng-binding']");
	By btnCancelOnAddProject = By.xpath("//md-dialog-actions[@class='layout-row']/button[2]");
	By btnOkOnCancelAlertPopUp = By.xpath("//button[contains(@class , 'md-button')and text()='ok']");
	By txtPrjEndDate = By.xpath("//md-datepicker[@id='projectEndDate']/div[1]/input");
	By txtPrjStartDate = By.xpath("//md-datepicker[@id='projectStartDate']/div[1]/input");
	
}
