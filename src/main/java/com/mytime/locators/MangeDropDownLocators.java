package com.mytime.locators;

import org.openqa.selenium.By;

public class MangeDropDownLocators {

	public static By industryType(String fieldName)
	{
		By dropIndustryTypeValue = By.xpath("//*[@class='md-select-menu-container md-active md-clickable']//md-option[@value='"+fieldName+"']");
		return dropIndustryTypeValue;
	}
}
