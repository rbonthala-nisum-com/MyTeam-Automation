package com.mytime.locators;

import org.openqa.selenium.By;

public class ManageDropDownLocators {

	public static By industryType(String fieldName) {
		By dropIndustryTypeValue = By.xpath(
				"//*[@class='md-select-menu-container md-active md-clickable']//md-option[@value='" + fieldName + "']");
		return dropIndustryTypeValue;
	}

	public static By delManager(String delManager) {

		By dropDeliveryManager = By.xpath("//p[contains(@class,'ng-binding')and(text()='" +" "+delManager+" "+ "')]/span");
		return dropDeliveryManager;
	}
	
	public static By accountInfo(String accountInfo) {
		By dropAccountInfoValue = By.xpath("//div[@class='md-text ng-binding'][text()='" +accountInfo+ "']");
		return dropAccountInfoValue;
	}
}
