package com.mytime.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.myteam.exceptionhandler.MyTeamException;
import com.mytime.locators.AddTeamMateLocators;
import com.mytime.locators.ManageDropDownLocators;
import com.mytime.locators.ManageProjectLocators;
import com.mytime.locators.MyTeamCommonLocators;
import com.mytime.util.MyTeamLogger;
import com.nisum.qa.automation.components.Clicks;
import com.nisum.qa.automation.components.TextField;

public class AddTeamMatePage extends Clicks {

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
			MyTeamLogger.getInstance().error("Unable to parse the date to given simpleDateFormat");
		}
		Date currentDate = new Date();
		if (currentDate.compareTo(givenDate) < 0) {
			result = "Past";
		} else if (currentDate.compareTo(givenDate) > 0) {
			result = "Future";
		} else if (currentDate.compareTo(givenDate) == 0) {
			result = "Today";
		}
		return result;
	}

	public void valEmpCannotBeAddToCmpltdPrjct(String btnViewProject, String btnEditProject) {
		userClick(By.xpath(btnEditProject), waitTime10Seconds);
		String prjEndDate = enterText.userGetTextFromWebElement(ManageProjectLocators.txtPrjEndDate, waitTime10Seconds);
		String dateExists = checkDateIsPastOrFuture(prjEndDate);
		if (dateExists.equals("Past")) {
			userClick(AddTeamMateLocators.btnCancel, waitTime10Seconds);
			userClick(By.xpath(btnViewProject), waitTime10Seconds);
			userClick(AddTeamMateLocators.addTeamMateButton, waitTime10Seconds);
			String expectedMsg = "Employee cannot be added to Completed Project";
			String actualMsg = enterText.userGetTextFromWebElement(AddTeamMateLocators.addTeamMateErrorMsg,
					waitTime10Seconds);
			if (expectedMsg.equals(actualMsg)) {
				Assert.assertEquals(expectedMsg, actualMsg);
				MyTeamLogger.getInstance().info("We should not be able to add an employee to completed project");
			} else {
				Assert.fail("Expected message : " + expectedMsg + " Actual message : " + actualMsg + " are not equal");
			}
		} else {

		}
	}

	private void valprjNameAndmgrName(String pName, String mName) {

		String prjNameOnAddTMate = "";
		String mgrNameOnAddTMate = "";
		prjNameOnAddTMate = enterText.userGetTextFromWebElement(AddTeamMateLocators.getProjectName, waitTime10Seconds);
		String prjName[] = prjNameOnAddTMate.split(":");
		mgrNameOnAddTMate = enterText.userGetTextFromWebElement(AddTeamMateLocators.getManagerName, waitTime10Seconds);
		compareTwoStrings(pName, prjName[1].trim());
		compareTwoStrings(mName, mgrNameOnAddTMate.trim());

	}

	public Boolean addTeamMate(String btnViewProject, String btnEditProject, String empName, String assignedRole,
			String empShift, String billabilityStatus, String billableDate) throws MyTeamException {
		boolean result = false;
		userClick(By.xpath(btnEditProject), waitTime10Seconds);
		String prjEndDate = enterText.userGetTextUsingGetAttribute(ManageProjectLocators.txtPrjEndDate,
				waitTime10Seconds);
		String prjStartDate = enterText.userGetTextUsingGetAttribute(ManageProjectLocators.txtPrjStartDate,
				waitTime10Seconds);
		String dateExists = checkDateIsPastOrFuture(prjEndDate);
		if (dateExists.equals("Past")) {
			log.error("Project end date should be future or today's date");
			Assert.fail("Project EndDate ");
		} else if (dateExists.equals("Today") || dateExists.equals("Future")) {
			String pName = enterText.userGetTextUsingGetAttribute(ManageProjectLocators.txtProjectName,
					waitTime10Seconds);
			String mName = enterText.userGetTextFromWebElement(ManageProjectLocators.dropDelLead, waitTime10Seconds);
			userClick(ManageProjectLocators.btnCancelOnAddProject, waitTime10Seconds);
			userClick(By.xpath(btnViewProject), waitTime10Seconds);
			valprjNameAndmgrName(pName, mName);
			userClick(AddTeamMateLocators.addTeamMateButton, waitTime10Seconds);
			selectFieldValuesOnAddTeamMate(empName, assignedRole,empShift, billabilityStatus,billableDate, prjStartDate);
			userClick(AddTeamMateLocators.btnAddOnAddTeamMatePopUp, waitTime10Seconds);
			String addTeamMateSuccessMsg = enterText
					.userGetTextFromWebElement(AddTeamMateLocators.addTeamMateSuccessMsg, waitTime10Seconds);
			compareTwoStrings(addTeamMateSuccessMsg, "TeamMate added successfuly");
			userClick(MyTeamCommonLocators.btnOkOnAlertPopUp, waitTime10Seconds);
			result = true;
		}
		return result;
	}

	private void selectFieldValuesOnAddTeamMate(String empName, String assignedRole,String empShift, String billabilityStatus, String billableDate, String prjStartDate) {
		userClick(AddTeamMateLocators.selectAEmployeeDrop, waitTime10Seconds);
		enterText.userTypeIntoTextField(AddTeamMateLocators.empSearch, empName, "enterText", waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue(empName), waitTime10Seconds);
		userClick(AddTeamMateLocators.assignedRole, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue2(assignedRole), waitTime10Seconds);
		userClick(AddTeamMateLocators.empShift, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue2(empShift), waitTime10Seconds);
		userClick(AddTeamMateLocators.billabilityStatus, waitTime10Seconds);
		userClick(ManageDropDownLocators.dropDownValue2(billabilityStatus), waitTime10Seconds);
		validateBillabilityStatusLable(billabilityStatus);
		String dateExistsIn = compareTwoDates(prjStartDate, billableDate);
		if (dateExistsIn.equals("Future") || dateExistsIn.equals("Today")) {
			userClick(AddTeamMateLocators.currentBillabilityDate, waitTime10Seconds);
			userClick(ManageDropDownLocators.dateSelection(billableDate), waitTime10Seconds);
		} else if (dateExistsIn.equals("Past")) {
			log.error("Billability date should be same or greater than the project start date");
			Assert.fail("Billability date should be same or greater than the project start date");
		}
	}
	
	private void validateBillabilityStatusLable(String billabilityStatus) {
		String actual = "";
		if (billabilityStatus.equals("Billable")) {
			actual = enterText.userGetTextFromWebElement(AddTeamMateLocators.dynamicStartDateLbl, waitTime10Seconds);
			Assert.assertEquals(actual, "Billable Start Date");
		} else if (billabilityStatus.equals("Shadow")) {
			actual = enterText.userGetTextFromWebElement(AddTeamMateLocators.dynamicStartDateLbl, waitTime10Seconds);
			Assert.assertEquals(actual, "Shadow Start Date");
		} else if (billabilityStatus.equals("Non-Billable")) {
			actual = enterText.userGetTextFromWebElement(AddTeamMateLocators.dynamicStartDateLbl, waitTime10Seconds);
			Assert.assertEquals(actual, "Non-Billable Start Date");
		} else if (billabilityStatus.equals("Reserved")) {
			actual = enterText.userGetTextFromWebElement(AddTeamMateLocators.dynamicStartDateLbl, waitTime10Seconds);
			Assert.assertEquals(actual, "Reserved Start Date");
		} else if (billabilityStatus.equals("Trainee")) {
			actual = enterText.userGetTextFromWebElement(AddTeamMateLocators.dynamicStartDateLbl, waitTime10Seconds);
			Assert.assertEquals(actual, "Trainee Start Date");
		} else {
			Assert.fail(actual + " lable is not displayed as expected");
		}
	}

	private void compareTwoStrings(String s1, String s2) {
		if (s1.equals(s2)) {
			Assert.assertEquals(s1, s2);
		} else {
			Assert.fail("Expected message : " + s1 + " Actual message : " + s2 + " are not equal");
		}
	}

	private String compareTwoDates(String date1, String date2) {
		String d1[] = date1.split("-");
		String d2[] = date2.split("-");
		String dt1 = "";
		String dt2 = "";
		String dLcl = "";
		for (int i = 0; i < d1.length; i++) {
			dLcl = d1[i];
			if (d1[i].charAt(0) == '0') {
				dLcl = d1[i].replace("0", "");
			}
			dt1 += dLcl + "-";
		}
		dt1 = dt1.substring(0, dt1.length() - 1);
		for (String d : d2) {
			dLcl = d;
			if (d.charAt(0) == '0') {
				dLcl = d.replace("0", "");
			}
			dt2 += dLcl + "-";
		}
		dt2 = dt2.substring(0, dt2.length() - 1);
		String result = "";
		if (dt1.compareTo(dt2) < 0) {
			result = "Past";
		} else if (dt1.compareTo(dt2) > 0) {
			result = "Future";
		} else if (dt1.compareTo(dt2) == 0) {
			result = "Today";
		}
		return result;
	}

}
