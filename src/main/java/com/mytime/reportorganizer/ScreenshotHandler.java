package com.mytime.reportorganizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.mytime.util.HelpfulMethods;

public class ScreenshotHandler {
	
	static File sourceFile;
	static File screenshotsLoc;
	static Logger log = Logger.getLogger("ScreenshotHandler");
	static String scrPath = System.getProperty("user.dir")+"\\Screenshots";
	static String screenShotName="";
	
	//This is to get page screenshot.
	public static String getPageScreenshot(WebDriver driver)
	{
		try{
			sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			screenShotName = "Screenshot"+ HelpfulMethods.getRandomNumber()+ ".png";
			File screenshotsLoc = new File(scrPath, screenShotName);
			Files.copy(sourceFile.toPath(), screenshotsLoc.toPath());
			
		}catch (IOException e) {
			log.error("Unable to take screenshot due to file operations");
			e.printStackTrace();
		}catch (NullPointerException e) {
			log.error("driver object is not having any reference to take screenshot.");
			e.printStackTrace();
		}catch (Exception e) {
			log.error("Unable to take screenshot");
			e.printStackTrace();
		}
		return screenShotName;
	}

}
