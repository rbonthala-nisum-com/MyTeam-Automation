package com.mytime.pages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.ManageGroupLocators;
import com.nisum.qa.automation.components.Clicks;

public class ManageGroupPage extends Clicks{

	
	public ManageGroupPage(WebDriver driver)
	{
		super(driver);
	}
	public void clickOnManageGroupModule(WebDriver driver)
	{
		userClick(ManageGroupLocators.manageGroupIcon, 10);
	}
}
