package com.mytime.applicationpages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.mytime.locators.WelcomePageLocators;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TimeOutMethods;
import com.nisum.qa.automation.components.Window;

public class WelcomePage extends Clicks {

	static Logger log;
	HomePage homePage;
	Window window;

	public WelcomePage(WebDriver driver) {
		super(driver);
	}

	public static void navigateToMyTimeApplicationURL(String URL) {
		navigateToURL(URL);
	}

	public void clickOnSignInWithGoogleButton() {
		userClick(WelcomePageLocators.myTime_Sign_In_With_Google_Button, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(waitTime10Seconds);
	}

	public void enterUserCredentials(WebDriver driver, String parentWindow, String userName, String passWord) {

		homePage = new HomePage(driver);
		window = new Window(driver);
		window.switchToNewWindow(driver,parentWindow, waitTime30Seconds);
		sleepInSeconds(waitTime30Seconds);
		homePage.enterUserName(driver, userName);
		homePage.enterPassword(driver, passWord);
	}
}
