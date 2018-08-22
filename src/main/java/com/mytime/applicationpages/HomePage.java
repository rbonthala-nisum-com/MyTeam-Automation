package com.mytime.applicationpages;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.HomePageLocators;
import com.mytime.reusablemethods.ReusableMethods;

/**
 * This is used to create page functions for Home page.
 */
public class HomePage extends ReusableMethods{

	/**
	 * Constructor to get driver object from parent class.
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * To click on Login button
	 */
	public void clickOnLoginButton()
	{
		userClick(HomePageLocators.loginLocator, waitTime30Seconds);
	}
	
	/**
	 * To enter user name text field.
	 **/
	public void enterUserName(String uName)
	{
		userTypeIntoTextField(HomePageLocators.loginLocator,uName, waitTime30Seconds);
	}
	
	/**
	 * To enter user name text field.
	 **/
	public void enterPassword(String password)
	{
		userTypeIntoTextField(HomePageLocators.loginLocator,password, waitTime30Seconds);
	}

	/**
	 * To click on Login button
	 */
	public void clickOnLoginAfterCredntialsButton()
	{
		userClick(HomePageLocators.loginLocator, waitTime30Seconds);
		
	}
}
