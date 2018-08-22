package com.mytime.locators;

import org.openqa.selenium.By;

public interface LoginPageLocators {
	
	By myNisum_OKTA_User_Name = By.id("okta-signin-username");
	By myNisum_OKTA_Password = By.id("okta-signin-passowrd");
	By OKTA_Sign_In_Button = By.xpath("//input[@type='submit']");
}
