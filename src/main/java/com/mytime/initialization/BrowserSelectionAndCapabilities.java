package com.mytime.initialization;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;

/**
 * This class is to launch browser and set driver capabilities
 */
public class BrowserSelectionAndCapabilities {
	
	WebDriver driver;
	Logger log = Logger.getLogger(getClass());
	String fileSeperator = System.getProperty("file.separator");
	
	// To launch browser based on given browser name.
	public WebDriver launchSpecifiedBrowser(String browserName, ITestContext context)
	{
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			driver = chromeDriver();
			context.setAttribute("driver", driver);
		}
		else if (browserName.equalsIgnoreCase("firefox")){
			driver = firefoxDriver();
			context.setAttribute("driver", driver);
		}
		return driver;
	}
	
	// To launch Chrome browser.
	private WebDriver chromeDriver() 
	{
		log.info("Launching google chrome browser");
		System.setProperty("webdriver.chrome.driver", getDriverPath());
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.prompt_for_download", false);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type", "start-maximized");
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("disable-infobars"); 
		return new ChromeDriver(options);
	}
	
	// To launch Firefox browser.
	private WebDriver firefoxDriver() 
	{
		log.info("Launching firefox browser");
		System.setProperty("webdriver.gecko.driver", getFirefoxDriverPath());
		driver= new FirefoxDriver();
		return driver;
	}
	
	// This is to get Chrome driver path
	public String getDriverPath() {

		String chromeDriverLocation = System.getProperty("user.dir") + fileSeperator
				+"BrowserDrivers" + fileSeperator;
		chromeDriverLocation = chromeDriverLocation + "chromedriver.exe";
		return chromeDriverLocation;
	}
	
	// This is to get FF driver path.
	public String getFirefoxDriverPath() {

		String firefoxDriverLocation = System.getProperty("user.dir") + fileSeperator
				+ "BrowserDrivers" + fileSeperator + "Drivers" + fileSeperator;
		firefoxDriverLocation = firefoxDriverLocation + "geckodriver.exe";
		return firefoxDriverLocation;
	}
}
