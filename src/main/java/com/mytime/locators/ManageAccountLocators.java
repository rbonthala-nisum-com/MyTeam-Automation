package com.mytime.locators;

import org.openqa.selenium.By;

import com.mytime.util.MyTeamUtils;

public interface ManageAccountLocators {

	By manageAccountModule = By.xpath("//span[text()='Manage Accounts ']");
	By colAccountID = By.xpath("//span[text()='Account ID']");
	By colAccountName = By.xpath("//span[text()='Account Name']");
	By colIndustryType = By.xpath("//span[text()='Industry Type']");
	By colStatus = By.xpath("//span[text()='Status']");
	By colDeliveryManagers = By.xpath("//span[text()='Delivery Managers']");
	By colActions = By.xpath("//span[text()='Actions']");
	By btnAddAccount = By.xpath("//div[@class='col-lg-8']/button");
	By txtAccountName = By.id("accountName");
	By dropIndustryTypeFiled = By.id("industryType");
	By txtClientAddress = By.xpath("//textarea[@id='clientAddress']");
	By dropDeliveryManagersField = By.name("deliveryManagers");
	By dropDeliveryManagersValue = By.xpath("//md-option[@class='ng-scope md-checkbox-enabled md-ink-ripple']/div[contains(text(),'Madhuri Bayyagari')]");
	By txtDeliveryManagerSearch = By.id("search");
	By chkDeliveryManager = By.className("md-container");
	By btnSubmitAddAccount = By.id("addButton");
	By accountTable = By.className("ui-grid-canvas");
	By accountRows = By.xpath("//div[@ui-grid-row='row']");
	By accountCols = By.xpath("//div[contains(@class,'ng-binding ng-scope')]");
	By accountColText = By.xpath("//div[@class='ui-grid-canvas']/div/div/div");
	By accountHeaders = By.xpath("//span[@class='ui-grid-header-cell-label ng-binding']");
	By txtSearchAccountName = By.className("ui-grid-filter-input ui-grid-filter-input-0 ng-touched");
	By btnOk = By.xpath("//button[text()='Ok']");
	By pageRefresh = By.className("fa fa-refresh");
	By addAccountSuccessMessage = By.xpath("//*[contains(@class , 'ng-binding')and text()='Account assigned successfully']");
	By updateAccountSuccessMessage = By.xpath("//*[contains(@class , 'ng-binding')and text()='Account updated successfully']");
	By accountNameErrorMsg = By.xpath("//*[contains(@class , 'ng-binding')and text()='Please enter the account Name']");
	By accountNameAlphabateErrorMsg = By.xpath("//*[contains(@class , 'ng-binding')and text()='Please enter alphabets only']");
	By industryTypeErrorMsg = By.xpath("//*[contains(@class , 'ng-binding')and text()='Please enter the industry type']");
	By clientAddressErrorMsg = By.xpath("//*[contains(@class , 'ng-binding')and text()='Please enter the client address']");
	By deliveryMgrsErrorMsg = By.xpath("//*[contains(@class , 'ng-binding')and text()='Please select a delivery Manager']");
	By btnCancel = By.xpath("//md-dialog-actions/button[2]");
	By btnOkOnCancelPopUp = By.xpath("//md-dialog[@class='_md md-default-theme md-transition-in']/md-dialog-actions/button[2]");

}
