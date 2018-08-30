package com.mytime.applicationpages;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.mytime.locators.WelcomePageLocators;
import com.nisum.automation.components.Clicks;
import com.nisum.automation.components.TimeOutMethods;
import com.nisum.automation.util.ReadProperties;

public class WelcomePage extends Clicks {

	HomePage homePage;

	public WelcomePage(WebDriver driver) {
		super(driver);
	}

	public static void navigateToMyTimeApplicationURL(String URL) {
		navigateToURL(URL);
	}

	// To click on welcome page's sign in button
	public void clickOnSignInWithGoogleButton() {
		userClick(WelcomePageLocators.myTime_Sign_In_With_Google_Button, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(waitTime10Seconds);
		try {
			String winHandleBefore = driver.getWindowHandle();
			// System.out.println(winHandleBefore);
			Collection<String> allWindows = driver.getWindowHandles();
			for (String curWindow : allWindows) {
				driver.switchTo().window(curWindow);
			}
			homePage.enterUserName(ReadProperties.getInstance().toGetGivenProperty("username"));
			homePage.enterPassword(ReadProperties.getInstance().toGetGivenProperty("password"));
			homePage.clickOnLoginAfterCredntialsButton();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.switchTo().window(winHandleBefore);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To enter user credentials
	public void enterUserCredentials(String username, String password) {

	}
}
