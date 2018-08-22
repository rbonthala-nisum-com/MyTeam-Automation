package com.mytime.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.mytime.reportorganizer.ScreenshotHandler;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MyTimeListener extends TestListenerAdapter implements ISuiteListener{
	
	protected static ExtentReports reports;
	protected static ExtentTest test;
	
	Logger log = Logger.getLogger(getClass());
	private static String fileSeperator = System.getProperty("file.separator");
	

	public void onTestStart(ITestResult result) {
		log.info("Test case "+result.getName()+" is started.");
		test = reports.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
	}

	public void onTestSuccess(ITestResult result) {
		
		log.info("PASSED :    " + result.getName() + "    has passed             ");
		test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
		
	}

	public void onTestFailure(ITestResult result) {
		
		log.error("FAILURE :   " + result.getName() + "    has failed                ");
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		String screenshotName = ScreenshotHandler.getPageScreenshot(driver);
		String screenshot = System.getProperty("user.dir") + fileSeperator + "Screenshots" + fileSeperator + screenshotName;
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
		String file = test.addScreenCapture(".\\Screenshots" + result.getMethod().getMethodName() + ".png");
		
	}

	public void onTestSkipped(ITestResult result) {
		
		log.warn("SKIPPED:   " + result.getName() + "    has skipped    ");
		test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		log.info("Test execution has initiated..");
		reports = new ExtentReports(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html");
	}

	public void onFinish(ITestContext context) {
		log.info("Test execution has completed..");
		reports.endTest(test);
		reports.flush();
	}

	public void onStart(ISuite suite) {
	}

	public void onFinish(ISuite suite) {
	}
	

}
