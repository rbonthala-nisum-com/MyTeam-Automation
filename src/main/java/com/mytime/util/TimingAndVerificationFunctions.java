package com.mytime.util;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.*;

/**
 * 
 * This class is used for implicit and explicit waits to meet certain element state.
 */
public class TimingAndVerificationFunctions{
	
	public static WebDriver driver;
	static Logger log;
	public static int waitTime10Seconds = 10;
	public static int waitTime30Seconds = 30;
	public static int waitTime60Seconds = 60;
	public static int waitTime90Seconds = 90;
	
	public TimingAndVerificationFunctions(WebDriver driver){
		TimingAndVerificationFunctions.driver=driver;
		log = Logger.getLogger(getClass());
	}
	
	//To wait for given amount of time.
	public static void sleepInSeconds(int timeInSeconds){
		
		try{
			Thread.sleep(timeInSeconds);
		}
		catch(InterruptedException exception)
		{
			exception.getStackTrace();
		}
	}
	
	/**
	* Sets implicit wait to zero. This helps to make explicit wait work.
	*/
	public void nullifyImplicitWait()
	{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	
	/**
	 * implicit wait to wait for expected condition to be met
	 * @param timeInSeconds
	 */
	public static void implicitWaitForGivenTime(int timeInSeconds)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * To wait for an element to be active state on DOM and returns true if it present.
	 * @param elementLocator - locator of the element
	 * @param timeInSeconds - wait time in seconds
	 * @return - true if element exists.
	 */
    public static boolean whetherElementPresent(By elementLocator, int ...waitTimeInSeconds)
	{   
    	int timeInSeconds = toGetGivenAmountOfTime(waitTimeInSeconds);
    	boolean whetherElementPresent = false;	
    	log.info("Searching for given element:  " + elementLocator);
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator)); 	
			if(driver.findElement(elementLocator).isDisplayed()||driver.findElement(elementLocator).isEnabled())
			{
				whetherElementPresent = true;
				log.info("Given element - " + elementLocator + " is exists and displayed");
			}
		}		
		catch (NoSuchElementException e)
		{
			log.error("Given element " + elementLocator + " was not found in DOM in given time - "+timeInSeconds+" Seconds - NoSuchElementException");			
		}
		catch (TimeoutException e)
		{
			log.error("Given element " + elementLocator + " was not displayed in given time - "+timeInSeconds+" Seconds"+" - TimeoutException");
		}
		catch (Exception e)
		{
			log.error("Element " + elementLocator + " is not found " );
		}
		return whetherElementPresent;
	}
    
    /**
     * To get waitTime for expected condition to be met. if not given,it will take default value 30 seconds.
     * @param waitTimeArray
     * @return
     */
    public static int toGetGivenAmountOfTime(int[] waitTimeArray)
	{
		if(waitTimeArray.length<=0)
		{
			return waitTime30Seconds;
		}
		else
		{
			return waitTimeArray[0];
		}
	}

    /**
	 * This is to wait for an element till the given element is clickable.
	 * @param driver 
	 * @param locator 
	 * @param waitTime  
	 * @return - True (boolean) if given element is located 
	 */
	public static boolean whetherElementClickable(By locator,int... givenTimeInSeconds)
	{    	
		int timeInSeconds =  toGetGivenAmountOfTime(givenTimeInSeconds);
		boolean elementClickable = false;
		try
		{
			log.info("Waiting until " + locator+" is clickable");
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			 
			if(driver.findElement((locator)).isDisplayed())
			{
				elementClickable = true;
				log.info("Given element locator : "+ locator + " is displayed and is clickable");
			}
		}
		
		catch (NoSuchElementException e)
		{
			log.error("Unable to find " + locator +" in DOM in time "+timeInSeconds+" Seconds - NoSuchElementException");
			Assert.fail("Unable to find " + locator +" in DOM in time "+timeInSeconds+" Seconds - NoSuchElementException");
		}
		catch (TimeoutException e)
		{
			log.error(locator + "was not displayed in time - "+timeInSeconds+" Seconds - TimeoutException");
			Assert.fail(locator + " was not displayed in time - "+timeInSeconds+" Seconds - TimeoutException");
		}
		catch (Exception e)
		{
			log.error(locator + "was not displayed - " + e);
			Assert.fail(locator + " was not displayed");
		}
		return elementClickable;
	}
}
