package com.mytime.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.mytime.locators.WelcomePageLocators;
import com.mytime.util.MyTeamLogger;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TimeOutMethods;
import com.nisum.qa.automation.components.Window;

public class WelcomePage extends Clicks {

	static Logger log;
	Window window;

	public WelcomePage(WebDriver driver) {
		super(driver);
	}

	public void navigateToMyTimeApplicationURL(String URL) {
		navigateToURL(URL);
	}

	public void clickOnSignInWithGoogleButton() {
		userClick(WelcomePageLocators.myTime_Sign_In_With_Google_Button, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(waitTime10Seconds);
	}

	public void enterUserCredentials(LoginPage login, String parentWindow, String userName, String passWord) {
		window = new Window(driver);
		window.switchToNewWindow(driver, parentWindow, waitTime30Seconds);
		driver.navigate().refresh();
		sleepInSeconds(waitTime30Seconds);
		login.enterUserName(driver, userName);
		login.enterPassword(driver, passWord);
		login.clickOnLoginButton();
		sleepInSeconds(2000);
	}
}
