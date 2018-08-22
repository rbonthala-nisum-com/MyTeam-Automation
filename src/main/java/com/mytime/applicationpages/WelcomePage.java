package com.mytime.applicationpages;

import org.openqa.selenium.WebDriver;
import com.mytime.locators.WelcomePageLocators;
import com.mytime.reusablemethods.ReusableMethods;
import com.mytime.util.TimingAndVerificationFunctions;

public class WelcomePage extends ReusableMethods {

	public WelcomePage(WebDriver driver) {
		super(driver);
	}

	public static void navigateToMyTimeApplicationURL(String URL)
	{
		navigateToURL(URL);
	}
	
	// To click on welcome page's sign in button 
	public void clickOnSignInWithGoogleButton(){
		userClick(WelcomePageLocators.myTime_Sign_In_With_Google_Button,TimingAndVerificationFunctions.waitTime10Seconds);
		sleepInSeconds(waitTime10Seconds);
	}
	
	//To enter user credentials
	public void enterUserCredentials(String username, String password)
	{
		
	}
}
