package com.mytime.locators;

import org.openqa.selenium.By;

public interface AddTeamMateLocators {

	By addTeamMateButton = By.xpath("//*[@id='projectTeamDetails']/form/section/button");
	By btnCancel = By.xpath("//md-dialog-actions/button[2]");
	By addTeamMateErrorMsg = By.xpath("//div[@class='md-dialog-content-body ng-scope']/p");
	By getProjectName = By.xpath("//div[@class='col-xs-6 text-left ']/p");
	By getManagerName = By.xpath("//div[@class='col-xs-6 text-right ']/p/span");
	By selectAEmployeeDrop = By.id("selectEmp");
	By empSearch = By.xpath("//md-select-header[@class='selectHeaderChild header-spacing layout-column']/input");
	By allEmpFields = By.xpath("//td[@class='Employee']");
	By allEmpData = By.xpath("//td[@class='ng-binding']");
	By assignedRole = By.id("empRole");
	By empShift = By.id("empShift");
	By billabilityStatus = By.id("empBillableStatus");
	By dynamicStartDateLbl = By.xpath("//td[@class='Employee ng-binding']");
	By currentBillabilityDate = By.xpath("(//md-datepicker[@id='newBillingStartDate ']/div/button)[1]");
	By btnAddOnAddTeamMatePopUp = By.xpath("//*[@class=\"_md ng-scope md-no-scroll md-active\"]/div/md-content/div/md-dialog-actions/button[1]");
	By addTeamMateSuccessMsg = By.xpath("//div[@class='md-dialog-content-body ng-scope']/p");
	By teamDetailsHeaders = By.xpath("(//div[@class='ui-grid-header-cell-row'])[2]/div/div/div/span[1]");
}
