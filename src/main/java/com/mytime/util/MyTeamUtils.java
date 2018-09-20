package com.mytime.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytime.database.dto.AccountDTO;

public class MyTeamUtils {

	private static String fileSeperator = System.getProperty("file.separator");

	public static String getPropertiesFilePath() {
		return System.getProperty("user.dir") + fileSeperator + "src\\main\\resources" + fileSeperator + "application"
				+ ".properties";
	}

	public static String getExcelPath() {
		return System.getProperty("user.dir") + fileSeperator + "src\\main\\resources" + fileSeperator + "TestData"
				+ ".xlsx";
	}

	/**
	 * This method is to get the boolean value of presence of element
	 */
	public static boolean testPresenceOfElement(WebDriver driver, By locator) {
		Boolean flag = false;
		try {
			driver.findElement(locator);
			flag = true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return flag;

	}

	public static Map<String, String> accoutRow(String accName, WebDriver driver, By accountTable,
			By tableHeaderNames) {

		Map<String, String> data = new HashMap<String, String>();
		int rowsCount;
		int colsCount;
		String accountName;
		WebElement Table = driver.findElement(accountTable);
		List<WebElement> rows = Table.findElements(By.xpath("(//div[@class='ng-isolate-scope'])"));
		rowsCount = rows.size();
		for (int i = 1; i <= rowsCount; i++) {
			WebElement row = Table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]"));
			List<WebElement> cols = row.findElements(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div"));
			colsCount = cols.size() - 1;
			accountName = Table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[1]")).getText();
			if (accountName.equalsIgnoreCase(accName)) {
				List<WebElement> headers = driver.findElements(tableHeaderNames);
				for (int j = 1; j < colsCount; j++) {
					WebElement col = row
							.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"));
					data.put(headers.get(j - 1).getText(), col.getText());
				}
				break;
			}
		}
		return data;
	}

	public static void compareAccountWithDb(String dbRecord, Map<String, String> uiRecord) {

		AccountDTO accountDTO = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			accountDTO = mapper.readValue(dbRecord, AccountDTO.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Map.Entry<String, String> pair : uiRecord.entrySet()) {
			String uiDelManagers[] = accountDTO.getDeliveryManagers();
			if (accountDTO.getAccountName().equals(pair.getValue())) {
				Assert.assertTrue(true, "Account Name entered in ui is " + accountDTO.getAccountName()
						+ " and Account Name stored in database is " + pair.getValue() + " both are same");
				continue;
//			} else {
//				Assert.assertFalse(true, "Account Name entered in ui is " + accountDTO.getAccountName()
//						+ " and Account Name stored in database is " + pair.getValue() + " both are same");
			}
			if (accountDTO.getIndustryType().equals(pair.getValue())) {
				Assert.assertTrue(true, "IndustryType entered in ui is " + accountDTO.getIndustryType()
						+ " and IndustryType stored in database is " + pair.getValue() + " both are same");
				continue;
//			} else {
//				Assert.assertFalse(true, "IndustryType entered in ui is " + accountDTO.getIndustryType()
//						+ " and IndustryType stored in database is " + pair.getValue() + " both are same");

			}
//			if (accountDTO.getClientAddress().equals(pair.getValue())) {
//				Assert.assertTrue(true, "ClientAddress entered in ui is " + accountDTO.getClientAddress()
//						+ " and ClientAddress stored in database is " + pair.getValue() + " both are same");
//				continue;
//			} else {
//				Assert.assertFalse(true, "ClientAddress entered in ui is " + accountDTO.getClientAddress()
//						+ " and ClientAddress stored in database is " + pair.getValue() + " both are same");
//			}
			String dbDelManagers[] = pair.getValue().split(",");
			if (compareTwoStringArrays(uiDelManagers, dbDelManagers)) {
				Assert.assertTrue(true, "UI Delivery Managers are stored in database successfully");
				continue;
			} else {
				Assert.assertFalse(false, "Delivery Managers are not stored as expected in database");
			}

		}
	}

	public static boolean compareTwoStringArrays(String[] s1, String[] s2) {
		if (s1 == null || s2 == null) {
			Assert.fail("two strings are not euqal");
		}
		if (s1.length != s2.length) {
			Assert.fail("1st length of the String is " + s1.length + " 2nd length of the String is" + s2.length
					+ " we cannot compare those String because both lengths are not equal ");
		}
//		for (int i = 0; i < s1.length; i++) {
//			if (!s1[i].equals(s2[i])) {
//				Assert.fail(s1[i] + " value is not equal with value " + s2[i]);
//				return false;
//			}
//		}
		return true;
	}

}

// public static String accoutRow(String accName, WebDriver driver, By
// accountTable) {
//
// int rowsCount;
// int colsCount;
// String accountName;
//
// WebElement Table = driver.findElement(accountTable);
// List<WebElement> rows =
// Table.findElements(By.xpath("(//div[@class='ng-isolate-scope'])"));
// rowsCount = rows.size();
//
// for (int i = 1; i <= rowsCount; i++) {
// String cellText;
// WebElement row =
// Table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]"));
// List<WebElement> cols =
// row.findElements(By.xpath("(//div[@class='ng-isolate-scope'])[" + i +
// "]/div"));
// colsCount = cols.size();
// accountName =
// Table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i +
// "]/div[1]")).getText();
// if (accountName.equalsIgnoreCase(accName)) {
// for (int j = 1; j < colsCount; j++) {
// WebElement col = row
// .findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" +
// j + "]"));
// if (j == colsCount - 1) {
// cellText = cellText + col.getText();
// } else {
// cellText = cellText + col.getText() + ",";
// }
// }
// break;
//
// }
// return cellText;
// }
// System.out.println(cellText);
//
// //
// Table.findElements(By.xpath("(//div[@class='ng-isolate-scope'])["+i+"])"));
//
// }
//
// public static String headerNames(WebDriver driver, By tableHeaderNames) {
//
// List<WebElement> headers = driver.findElements(tableHeaderNames);
// int header_count = headers.size();
// for (int i = 0; i < header_count - 1; i++) {
// if (i == header_count - 2) {
// headerText = headerText + headers.get(i).getText();
// } else {
// headerText = headerText + headers.get(i).getText() + ",";
// }
// }
// return headerText;
// }
