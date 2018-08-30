package com.mytime.locators;

import org.openqa.selenium.By;

public interface ManageAccount_Locators {

	public static By manageAccountModule = By.xpath("//span[text()='Manage Accounts']");
	public static By colAccountID = By.xpath("//span[text()='Account ID']");
	public static By colAccountName = By.xpath("//span[text()='Account Name']");
	public static By colIndustryType = By.xpath("//span[text()='Industry Type']");
	public static By colStatus = By.xpath("//span[text()='Status']");
	public static By colDeliveryManagers = By.xpath("//span[text()='Delivery Managers']");
	public static By colActions = By.xpath("//span[text()='Actions']");
	public static By btnAddAccount = By.xpath("//div[@class='col-lg-8']/button");
	public static By txtAccountName = By.xpath("//input[@id='accountName']");
	public static By dropIndustryTypeFiled = By.xpath("//md-select[@id='industryType']");
	public static By txtClientAddress = By.xpath("//textarea[@id='clientAddress']");
	public static By dropDeliveryManagersField = By.xpath("//md-select[@id='selectManager']");
	public static By dropDeliveryManagersValue = By.xpath("//md-option[@class='ng-scope md-checkbox-enabled md-ink-ripple']/div[contains(text(),'Madhuri Bayyagari')]");
	public static By txtDeliveryManagerSearch = By.xpath("//input[@id='search']");
	public static By chkDeliveryManager = By.xpath("//div[@class='md-container']");
	public static By btnSubmitAddAccount = By.id("addButton");
	public static By accountTable = By.xpath("//div[@class='ui-grid-viewport ng-isolate-scope']");
	public static By accountRows = By.xpath("//div[@ui-grid-row='row']");
	public static By accountCols = By.xpath("//div[contains(@class,'ng-binding ng-scope')]");
	public static By accountColText = By.xpath("//div[@class='ui-grid-canvas']/div/div/div");
	public static By accountHeaders = By.xpath("//span[@class='ui-grid-header-cell-label ng-binding']");
	
	}
