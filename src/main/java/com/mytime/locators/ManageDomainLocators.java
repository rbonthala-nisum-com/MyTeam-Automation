package com.mytime.locators;

import org.openqa.selenium.By;

public interface ManageDomainLocators {

	By manageDomainModule = By.xpath("//span[text()='Manage Domains ']");
	By btnAddDomain = By.xpath("//div[@class='col-lg-8']/button");
	By dropAccountFiled = By.id("AccountInfo");
	By txtDomainName = By.id("domainName");
	By dropDeliveryLeadField = By.id("selectDeliveryLeads");
	By txtDeliveryLead = By.xpath("//md-select-header[@class='selectHeaderChild header-spacing layout-column']/input");
	By chkDeliveryLead = By.className("md-container");
	By btnSubmitAdddomain = By.xpath("//md-dialog-actions/button[1]");
	By addDomainSuccessMessage = By.xpath("//*[contains(@class , 'ng-binding')and text()='Domain created successfully']");
	By updateDomainSuccessMessage = By.xpath("//*[contains(@class , 'ng-binding')and text()='Domain updated successfully']");
	By btnOk = By.xpath("//button[text()='Ok']");
	By domainTable = By.className("ui-grid-canvas");
	By domainRows = By.xpath("//div[@ui-grid-row='row']");
	By domainCols = By.xpath("//div[contains(@class,'ng-binding ng-scope')]");
	By domainColText = By.xpath("//div[@class='ui-grid-canvas']/div/div/div");
	By domaintHeaders = By.xpath("//span[@class='ui-grid-header-cell-label ng-binding']");
	By accoutErrMsg = By.xpath("//*[contains(@class , 'ng-binding')and text()='Please select a Account']");
	By domainNameErrMsg = By.xpath("//*[contains(@class , 'ng-binding')and text()='Please enter a Domain Name']");
	By delLeadErrMsg = By.xpath("//*[contains(@class , 'ng-binding')and text()='Please select a deliveryManagers']");
	
}
