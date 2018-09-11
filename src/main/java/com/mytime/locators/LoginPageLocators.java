package com.mytime.locators;

import org.openqa.selenium.By;

public interface LoginPageLocators {
	
	By myNisum_OKTA_User_Name = By.id("okta-signin-username");
	By myNisum_OKTA_Password = By.id("okta-signin-passowrd");
	By OKTA_Sign_In_Button = By.xpath("//input[@type='submit']");
	By signInWithGoogle = By.className("abcRioButtonContents");
	By clickEmail = By.className("uRhzae");
	By chooseExistingAccount = By.className("M8HEDc cd29Sd bxPAYd W7Aapd znIWoc");
	//By loginConformationPopup = By.className("RveJvd snByac");
//	By loginConformationPopup = By.className("CwaK9");
	By loginConformationPopup = By.xpath("//span[text()='Continue']");
	//span[text()='Continue']
//	By loginConformationPopup = By.className("U26fgb O0WRkf zZhnYe e3Duub C0oVfc nDKKZc DL0QTb M9Bg4d");
	//By loginConformationPopup = By.xpath("//div[@class='U26fgb O0WRkf zZhnYe e3Duub C0oVfc nDKKZc DL0QTb M9Bg4d']/content/span[text()='Continue']");
}
