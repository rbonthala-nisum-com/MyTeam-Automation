package com.mytime.util;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.nisum.qa.automation.util.ExcelUtils;

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

	/**
	 * This method is to pass dynamic drop down value(value will read runtime from
	 * excel sheet)in to specific locator
	 */
//	public static String dropValue(String dropName, String sheetName, String fieldValue) {
//
////		ExcelUtils excel = new ExcelUtils();
//		String dropValueToSelect = null;
//		try {
//			if (dropName.equalsIgnoreCase("industryType")) {
//				dropValueToSelect = ExcelUtils.getCellData(getPropertiesFilePath(), sheetName, "Industry Type");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return dropValueToSelect;
//	}

//	public static String accoutRow(WebDriver driver, By accountRows, By accountCols, By accountColText,
//			By accountTable) {
//
//		Table = driver.findElement(accountTable);
//		List<WebElement> rows = Table.findElements(By.xpath("(//div[@class='ng-isolate-scope'])"));
//		rows_count = rows.size();
//
//		for (int i = 1; i <= rows_count; i++) {
//			WebElement row = Table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]"));
//			List<WebElement> cols = row.findElements(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div"));
//			cols_count = cols.size();
//			accountName = Table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[1]")).getText();
//			if (accountName
//					.equalsIgnoreCase(ReadExcelData.getCellData(UIMap.filePath(), UIMap.sheetName(), "Account Name"))) {
//
//				for (int j = 1; j < cols_count; j++) {
//					WebElement col = row
//							.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"));
//					if (j == cols_count - 1) {
//						cellText = cellText + col.getText();
//					} else {
//						cellText = cellText + col.getText() + ",";
//					}
//				}
//			}
//		}
//		System.out.println(cellText);
//		return cellText;
//		// Table.findElements(By.xpath("(//div[@class='ng-isolate-scope'])["+i+"])"));
//
//	}

//	public static String headerNames(WebDriver driver, By tableHeaderNames) {
//
//		List<WebElement> headers = driver.findElements(tableHeaderNames);
//		int header_count = headers.size();
//		for (int i = 0; i < header_count - 1; i++) {
//			if (i == header_count - 2) {
//				headerText = headerText + headers.get(i).getText();
//			} else {
//				headerText = headerText + headers.get(i).getText() + ",";
//			}
//		}
//		return headerText;
//	}
}
