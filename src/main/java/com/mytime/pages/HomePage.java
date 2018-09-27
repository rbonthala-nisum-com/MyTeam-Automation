package com.mytime.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mytime.locators.HomePageLocators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;

/**
 * This is used to create page functions for Home page.
 */
public class HomePage extends Clicks {

	public static WebElement element = null;

	/**
	 * Constructor to get driver object from parent class.
	 * 
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		super(driver);
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
		new TextField(driver).userTypeIntoTextField(HomePageLocators.userNameLocator, uName,"sendKeys", waitTime30Seconds);
	}

	/**
	 * To enter user name text field.
	 **/
	public void enterPassword(WebDriver driver,String password) {
		new TextField(driver).userTypeIntoTextField(HomePageLocators.passWordLocator, password,"sendKeys", waitTime30Seconds);
	}

	/**
	 * To click on Login button
	 */
	public void clickOnLoginAfterCredntialsButton() {
		userClick(HomePageLocators.loginLocator, waitTime30Seconds);

	}

}
