package com.mytime.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;
import com.nisum.qa.automation.components.TimeOutMethods;

public class ManagePageUtils extends Clicks {

	public ManagePageUtils(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void verifySuccessMessage(String expectedMsg, By addBtnLocator, By successMsgLocator) {
		TextField enterText = new TextField(driver);
		String successMsg = enterText.userGetTextFromWebElement(successMsgLocator, TimeOutMethods.waitTime10Seconds);
		Assert.assertEquals(expectedMsg, successMsg);
		userClick(addBtnLocator, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(2000);
	}
	
}
