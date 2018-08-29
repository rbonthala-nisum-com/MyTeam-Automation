package com.mytime.applicationpages;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.mytime.applicationdata.ApplicationDataCarrier;
import com.mytime.locators.WelcomePageLocators;
import com.mytime.reusablemethods.ReusableMethods;
import com.mytime.util.TimingAndVerificationFunctions;

public class WelcomePage extends ReusableMethods {

	HomePage homePage;
	public WelcomePage(WebDriver driver) {
		super(driver);
	}

	public static void navigateToMyTimeApplicationURL(String URL) {
		navigateToURL(URL);
	}

	// To click on welcome page's sign in button
	public void clickOnSignInWithGoogleButton() {
		userClick(WelcomePageLocators.myTime_Sign_In_With_Google_Button,
				TimingAndVerificationFunctions.waitTime10Seconds);
		sleepInSeconds(waitTime10Seconds);
		try
		{
		String winHandleBefore = driver.getWindowHandle();
		// System.out.println(winHandleBefore);
		Collection<String> allWindows = driver.getWindowHandles();
		for (String curWindow : allWindows) {
			driver.switchTo().window(curWindow);
		}
		homePage.enterUserName(ApplicationDataCarrier.getInstance().toGetGivenProperty("username"));
		homePage.enterPassword(ApplicationDataCarrier.getInstance().toGetGivenProperty("password"));
		homePage.clickOnLoginAfterCredntialsButton();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().window(winHandleBefore);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	// To enter user credentials
	public void enterUserCredentials(String username, String password) {

	}
}
