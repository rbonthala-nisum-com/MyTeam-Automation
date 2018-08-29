package com.mytime.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mytime.reusablemethods.ApplicationReusableFunctions;

public class DropFiledValue_Locators {

	public static WebDriver driver;
	public static By dropIndustryTypeValue = By.xpath("//*[@class='md-select-menu-container md-active md-clickable']//md-option[@value='"+ ApplicationReusableFunctions.dropValue(driver, "IndustryType") +"']");
}
