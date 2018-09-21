package com.mytime.pages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.HomePageLocators;
import com.mytime.locators.LoginPageLocators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;
import com.nisum.qa.automation.components.TimeOutMethods;

public class LoginPage extends Clicks {

	/**
	 * Constructor to get driver object.
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnExistingEmail(WebDriver driver) {
		userClick(LoginPageLocators.chooseExistingAccount, TimeOutMethods.waitTime10Seconds);
	}

//	public void clickOnLoginConformationPopup(WebDriver driver) {
//		userClick(LoginPageLocators.loginConformationPopup, TimeOutMethods.waitTime30Seconds);
//	}
	
	public void clickOnLoginConformationPopup(WebDriver driver) {
		userClick(LoginPageLocators.loginConformationPopup);
	}

	/**
	 * To click on Login button
	 */
	public void clickOnLoginButton() {
		userClick(HomePageLocators.loginLocator, waitTime30Seconds);
	}

	/**
	 * To enter user name text field.
	 **/
	public void enterUserName(WebDriver driver,String uName) {
		new TextField(driver).userTypeIntoTextField(HomePageLocators.userNameLocator, uName, "sendKeys", waitTime30Seconds);
	}

	/**
	 * To enter user name text field.
	 **/
	public void enterPassword(WebDriver driver,String password) {
		new TextField(driver).userTypeIntoTextField(HomePageLocators.passWordLocator, password,"sendKeys",  waitTime30Seconds);
	}
}
