package com.mytime.applicationpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mytime.locators.HomePageLocators;
import com.mytime.locators.LoginPageLocators;
import com.nisum.automation.components.Clicks;
import com.nisum.automation.components.TextField;

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
	public void enterUserName(String uName) {
		new TextField(driver).userTypeIntoTextField(HomePageLocators.loginLocator, uName, waitTime30Seconds);
	}

	/**
	 * To enter user name text field.
	 **/
	public void enterPassword(String password) {
		new TextField(driver).userTypeIntoTextField(HomePageLocators.loginLocator, password, waitTime30Seconds);
	}

	/**
	 * To click on Login button
	 */
	public void clickOnLoginAfterCredntialsButton() {
		userClick(HomePageLocators.loginLocator, waitTime30Seconds);

	}

	public static void lnk_ClickEmail(WebDriver driver) {

		element = driver.findElement(LoginPageLocators.clickEmail);
	}

	public static void lnk_ChooseEmail(WebDriver driver) {
		element = driver.findElement(LoginPageLocators.chooseEmail);
	}
}
