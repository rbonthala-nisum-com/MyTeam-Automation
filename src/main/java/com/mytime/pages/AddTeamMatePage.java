package com.mytime.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.mytime.database.dto.ProjectDTO;
import com.mytime.locators.AddTeamMateLocators;
import com.mytime.locators.ManageProjectLocators;
import com.mytime.util.MyTeamLogger;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;

public class AddTeamMatePage extends Clicks{

	TextField enterText = new TextField(driver);
	public AddTeamMatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private String checkDateIsPastOrFuture(String date) {
		String result = "";
		Date givenDate = null;
		try {
			givenDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			MyTeamLogger.getInstance().error("Unable to parse the date to given simpleDateFormat");
			e.printStackTrace();
		}
		Date currentDate = new Date();
		if(givenDate.compareTo(currentDate)<0) {
			result = "Past";
		}else if(givenDate.compareTo(currentDate)>0) {
			result = "Future";
		}else if(givenDate.compareTo(currentDate)==0){
			result = "Today";
		}
		return result;
	}

	public void addTeamMate(String btnViewProject, String btnEditProject) {
		userClick(By.xpath(btnEditProject), waitTime10Seconds);
		String prjEndDate = enterText.userGetTextFromWebElement(ManageProjectLocators.dateProjectEndDate, waitTime10Seconds);
		String dateExists = checkDateIsPastOrFuture(prjEndDate);
		if(dateExists.equals("Past")) {
			userClick(AddTeamMateLocators.btnCancel, waitTime10Seconds);
			userClick(By.xpath(btnViewProject), waitTime10Seconds);
			userClick(AddTeamMateLocators.addTeamMateButton, waitTime10Seconds);
			String expectedMsg = "Employee cannot be added to Completed Project";
			String actualMsg = enterText.userGetTextFromWebElement(AddTeamMateLocators.addTeamMateErrorMsg, waitTime10Seconds);
			if(expectedMsg.equals(actualMsg)) {
				Assert.assertEquals(expectedMsg, actualMsg);
				MyTeamLogger.getInstance().info("We should not be able to add an employee to completed project");
			}else {
				Assert.fail("Expected message : "+expectedMsg+" Actual message : "+actualMsg+" are not equal");
			}
		}else if(dateExists.equals("Today")||dateExists.equals("Future")) {
			String prjName = enterText.userGetTextFromWebElement(ManageProjectLocators.txtProjectName, waitTime10Seconds);
			String managerName = enterText.userGetTextFromWebElement(ManageProjectLocators.dropDelLead, waitTime10Seconds);
			String prjNameOnTMatePage = enterText.userGetTextFromWebElement(AddTeamMateLocators.getProjectName, waitTime10Seconds);
			String mgrNameOnTMatePage = enterText.userGetTextFromWebElement(AddTeamMateLocators.getManagerName, waitTime10Seconds);
			if((compareToStrings(prjName,prjNameOnTMatePage))&&(compareToStrings(managerName, mgrNameOnTMatePage))){
				Assert.assertTrue(true);
				MyTeamLogger.getInstance().info("projectName and ManagerNames are displayed as expected");
			}else {
				Assert.assertFalse(false, "ProjectName and ManagerNames are not displayed as expected");
			}
			userClick(AddTeamMateLocators.btnCancel, waitTime10Seconds);
			userClick(By.xpath(btnViewProject), waitTime10Seconds);
			userClick(AddTeamMateLocators.addTeamMateButton, waitTime10Seconds);
		}
		
	}
	
	private Boolean compareToStrings(String s1, String s2) {
		Boolean result = false;
		if(s1.equals(s2)) {
			Assert.assertEquals(s1, s2);
			result = true;
		}else {
			Assert.fail("Expected message : "+s1+" Actual message : "+s2+" are not equal");
		}
		return result;
	}
	
	
}
