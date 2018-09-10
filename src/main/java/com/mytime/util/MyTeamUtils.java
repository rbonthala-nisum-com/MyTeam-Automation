package com.mytime.util;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyTeamUtils {

	private static String fileSeperator = System.getProperty("file.separator");
	
	
	public static String getPropertiesFilePath() {
		return System.getProperty("user.dir") + fileSeperator + "src\\main\\resources" + fileSeperator + "application" + ".properties";
	}
	
	public static String getExcelPath()
	{
		return System.getProperty("user.dir") + fileSeperator + "src\\main\\resources" + fileSeperator + "TestData" + ".xlsx";
	}
	
	public static boolean testPresenceOfElement(WebDriver driver, By locator)
	{
		Boolean flag = false;
		try
		{
			driver.findElement(locator);
			flag =  true;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
		return flag;
		
	}
}
