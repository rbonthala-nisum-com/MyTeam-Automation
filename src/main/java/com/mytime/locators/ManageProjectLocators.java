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
	By btnSubmitAddProject = By.xpath("//button/span[text()='Add']");
	By projectTable = By.className("ui-grid-canvas");
	By projectHeaders = By.xpath("//span[@class='ui-grid-header-cell-label ng-binding']");
	By btnOk = By.xpath("//button[text()='Ok']");
	By addProjectSuccessMessage = By.xpath("//*[contains(@class , 'ng-binding')and text()='Project created successfully']");
	By updateProjectSuccessMessage = By.xpath("//*[contains(@class , 'ng-binding')and text()='Project updated successfully']");
	
}
