package com.mytime.locators;

import org.openqa.selenium.By;

public interface MyTeamCommonLocators {

	By btnCancelOnPopup = By.xpath("//md-dialog-actions[@class='layout-row']/button[2]");
	By btnOkOnAlertPopUp = By.xpath("//button[contains(@class , 'md-button')and text()='ok']");
	By lblErrorMessage = By.xpath("//span[@class='error ng-binding']");
}
