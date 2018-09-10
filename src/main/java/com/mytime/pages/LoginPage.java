package com.mytime.pages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.LoginPageLocators;
import com.nisum.qa.automation.components.Clicks;

public class LoginPage extends Clicks {

	/**
	 * Constructor to get driver object.
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnExistingEmail(WebDriver driver)
	{
		userClick(LoginPageLocators.chooseExistingAccount, 10);
	}

}
