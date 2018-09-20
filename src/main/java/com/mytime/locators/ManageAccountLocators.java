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
	By txtAccountName = By.xpath("//input[@id='accountName']");
	By dropIndustryTypeFiled = By.xpath("//md-select[@id='industryType']");
	By txtClientAddress = By.xpath("//textarea[@id='clientAddress']");
	By dropDeliveryManagersField = By.name("deliveryManagers");
	By dropDeliveryManagersValue = By.xpath(
			"//md-option[@class='ng-scope md-checkbox-enabled md-ink-ripple']/div[contains(text(),'Madhuri Bayyagari')]");
	By txtDeliveryManagerSearch = By.xpath("//input[@id='search']");
	By chkDeliveryManager = By.xpath("//div[@class='md-container']");
	By btnSubmitAddAccount = By.id("addButton");
	By accountTable = By.xpath("//div[@class='ui-grid-viewport ng-isolate-scope']");
	By accountRows = By.xpath("//div[@ui-grid-row='row']");
	By accountCols = By.xpath("//div[contains(@class,'ng-binding ng-scope')]");
	By accountColText = By.xpath("//div[@class='ui-grid-canvas']/div/div/div");
	By accountHeaders = By.xpath("//span[@class='ui-grid-header-cell-label ng-binding']");
	By txtSearchAccountName = By.className("ui-grid-filter-input ui-grid-filter-input-0 ng-touched");
	By btnOk = By.xpath("//button[text()='Ok']");
	By pageRefresh = By.className("fa fa-refresh");
	By addAccountSuccessMessage = By.xpath("//*[contains(@class , 'ng-binding')and text()='Account assigned successfully']");
//	By addAccountSuccessMessage = By.xpath("//div[@class='md-dialog-content-body ng-scope']/p");
//	By deliveryManagersCloseIcon = By.className("glyphicon glyphicon-remove close-mdselect");
//	By dropIndustryTypeValue = By.xpath("//*[@class='md-select-menu-container md-active md-clickable']//md-option[@value='"+ MyTeamUtils.dropValue("industryType","AddAccount") +"']");

//	class ManageAccountDropdowns {
//
//		public static By dropIndustryTypeValue = By.xpath("//*[@class='md-select-menu-container md-active md-clickable']//md-option[@value='"+ MyTeamUtils.dropValue("industryType","IndustryType") +"']");
//	}

}

