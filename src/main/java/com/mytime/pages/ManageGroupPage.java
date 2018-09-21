package com.mytime.pages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.ManageAccountLocators;
import com.mytime.locators.ManageGroupLocators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TimeOutMethods;

public class ManageGroupPage extends Clicks{

	
	public ManageGroupPage(WebDriver driver)
	{
		super(driver);
	}
	public void clickOnManageGroupModule()
	{
		userClick(ManageGroupLocators.manageGroupIcon, TimeOutMethods.waitTime30Seconds);
	}
}
