package com.mytime.applicationpages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.ManageGroupLocators;
import com.nisum.qa.automation.components.Clicks;

public class ManageGroup_Page extends Clicks{

	
	public ManageGroup_Page(WebDriver driver)
	{
		super(driver);
	}
	public void clickOnManageGroupModule(WebDriver driver)
	{
		userClick(ManageGroupLocators.manageGroupIcon, 10);
	}
}
