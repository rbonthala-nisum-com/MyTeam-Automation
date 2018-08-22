package com.mytime.reusablemethods;

import java.util.NoSuchElementException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.mytime.util.TimingAndVerificationFunctions;


public class ReusableMethods extends TimingAndVerificationFunctions{
	
	
	static Logger log;
	
	//Constructor to get driver from super class and initializing objects.
	public ReusableMethods(WebDriver driver) {
		super(driver);
		log = Logger.getLogger(getClass());
	}
	
	//To navigate to given http application URL.
	public static void navigateToURL(String url)
	{
		try{
			driver.navigate().to(url);
			log.info("Navigated to the given application URL: "+url);
		}
		catch(Exception e)
		{
			log.error("unable to navigate to the given appication URL: "+url);
			e.getStackTrace();
		}
	}
	
	//This function is to click on given web element.
	public static void userClick(By locator, int... timeInSeconds)
	{
		int givenWaitTime = 0;
		try
		{
			givenWaitTime =  toGetGivenAmountOfTime(timeInSeconds);
			if(whetherElementClickable(locator, givenWaitTime))
			{
				WebElement webelement = driver.findElement(locator);
				webelement.click();		
				log.info("Clicked on the given web element: "+locator);
			}
			else
			{
				log.error("Given web element: "+ locator + " is not clickable in time - "+givenWaitTime+" Seconds");
				Assert.fail( "Given web elemnt:" + locator + " is not clickable in time - "+givenWaitTime+" Seconds");
			}
		}
		catch (NoSuchElementException e)
		{
			log.error("Given web element: "+locator + " was not found in DOM in time - "+givenWaitTime+" Seconds"+" - NoSuchElementException");	
			Assert.fail("Given web element: "+locator + " was not found in DOM in time - "+givenWaitTime+" Seconds"+" - NoSuchElementException");
		}
		catch(Exception e)
		{
			log.error( "Given web element: "+locator + " was not clickable" + e);		
			Assert.fail("Given web element: "+locator + " was not found on the web page");
		}
	}
	
	//To enter text into given web element.
	public void userTypeIntoTextField(By locator,String provideText, int... timeInSeconds)
	{
		int givenWaitTime = 0;
		try
		{
			givenWaitTime =  toGetGivenAmountOfTime(timeInSeconds);
			if(whetherElementClickable(locator, givenWaitTime))
			{
				WebElement webelement = driver.findElement(locator);
				webelement.sendKeys(provideText);		
				log.info("Entered value: "+provideText+"in to the given web element: "+locator);
			}
			else
			{
				log.error("Not able to type"+provideText+" in to web element: "+ locator + " in time - "+givenWaitTime+" Seconds");
				Assert.fail("Not able to type"+provideText+" in to web element: "+ locator + " in time - "+givenWaitTime+" Seconds");
			}
		}
		catch (NoSuchElementException e)
		{
			log.error("Given web element: "+locator + " was not found in DOM in time - "+givenWaitTime+" Seconds"+" - NoSuchElementException");	
			Assert.fail("Given web element: "+locator + " was not found in DOM in time - "+givenWaitTime+" Seconds"+" - NoSuchElementException");
		}
		catch(Exception e)
		{
			log.error( "Given web element: "+locator + " was not clickable" + e);		
			Assert.fail("Given web element: "+locator + " was not found on the web page");
		}
	}
	
	
	//To select a value from given dropdown.
	public void selectAValueFromDropDown(By locator,String value, int timeInSeconds)
	{
		boolean elementPresence = whetherElementPresent(locator, timeInSeconds);
		Select select = new Select(driver.findElement(locator));
		if(elementPresence)
		{
			try
			{
				select.selectByValue(value);
			}
			catch(NoSuchElementException e){
				log.error("Given web element: "+locator + " was not found in DOM in time - "+timeInSeconds+" Seconds"+" - NoSuchElementException");	
			e.printStackTrace();
			}
		}
			else{
				log.info("Element is not present in DOM");
			}
		}
	
	//To enter text into given web element.
	public String getWindowTitle(int... timeInSeconds)
	{
		String title="";
		int givenWaitTime = 0;
		try
		{
			givenWaitTime =  toGetGivenAmountOfTime(timeInSeconds);
			implicitWaitForGivenTime(givenWaitTime);
			title = driver.getWindowHandle(); 
			log.info("Current window title is:  "+title );
		}
		
		catch(Exception e)
		{
			log.error( "unable to get page title");		
			Assert.fail("Unable to get page title");
		}
		return title;
	}
	

}

