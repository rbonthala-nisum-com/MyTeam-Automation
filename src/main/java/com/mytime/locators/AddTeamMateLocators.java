package com.mytime.locators;

import org.openqa.selenium.By;

public interface AddTeamMateLocators {

	By addTeamMateButton = By.xpath("//button[@class='md-raised  md-button md-ink-ripple']/div");
	By btnCancel = By.xpath("//md-dialog-actions/button[2]");
	By addTeamMateErrorMsg = By.xpath("//div[@class='md-dialog-content-body ng-scope']/p");
	By getProjectName = By.xpath("//div[@class='col-xs-6 text-left ']/p");
	By getManagerName = By.xpath("//div[@class='col-xs-6 text-right ']/p/span");
}
