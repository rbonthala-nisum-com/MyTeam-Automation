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

	public static Map<String, String> getEntireRowOrActionsColumn(String accName, WebDriver driver, By accountTable, By tableHeaderNames,
			String functionality) {

		Map<String, String> data = new HashMap<String, String>();
		int rowsCount;
		int colsCount;
		String accountName;
		WebElement col;
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
				if ((functionality.replace("\\s","").trim().equalsIgnoreCase("Actions")||functionality.replace("\\s","").trim().equalsIgnoreCase("ActionsColumn"))){
					colsCount = cols.size();
					for (int j = 1; j <= colsCount; j++) {
						col = row.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"));
						if(j==colsCount) {
						data.put(headers.get(j - 1).getText(),"(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]");
						break;
						}
					}
				}
				if (functionality.replace("\\s","").trim().equalsIgnoreCase("EntireRow")) {
					for (int j = 1; j < colsCount; j++) {
						col = row.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"));
						data.put(headers.get(j - 1).getText(), col.getText());
					}
				}
				break;
			}
		}
		return data;
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
