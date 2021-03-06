package com.mytime.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	
	public static By dropDownValue(String dropValueToSelect) {
		By dropAccountInfoValue = By.xpath("//div[@class='md-text ng-binding'][text()='"+dropValueToSelect+"']");
		return dropAccountInfoValue;
	}
	
	public static By dropDownValue2(String dropValueToSelect) {
		By dropSelectValue = By.xpath("//div[@class='md-select-menu-container md-active md-clickable']/md-select-menu/md-content/md-optgroup/md-option/div[text()='"+dropValueToSelect+"']");
		return dropSelectValue;
	}
	
	public static By dateSelection(String date) {
		/*String rDt = "";
		String d[] = date.split("-");
		for(String dt:d) {
			rDt += dt.replaceFirst("^0+(?!$)", "");
		}*/
		By selectDate = By.xpath("//td[contains(@id,'"+date+"')]");
		return selectDate;
	}
	
	public static By removeDelManager(String removeDelMgr) {
		By removeDelManager = By.xpath("//p[contains(@class, 'ng-binding') and text()='"+" "+removeDelMgr+" "+"']");
		return removeDelManager;
	}

}

