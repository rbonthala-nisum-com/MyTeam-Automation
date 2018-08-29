package com.mytime.reusablemethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mytime.applicationdata.ReadExcelData;
import com.mytime.initialization.UIMap;

public class ApplicationReusableFunctions {

	static WebElement Table;
	static int rows_count;
	static int cols_count;
	static String cellText = "";
	static String accountName;
	static String firstCellText;
	static String temp;
	static String deliveryManagers;
	public static WebDriver driver;
	static String dropValueToSelect;
	static String headerText = "";

	public static String dropValue(WebDriver driver, String dropName) {

		try {
			if (dropName.equalsIgnoreCase("industryType")) {
				dropValueToSelect = ReadExcelData.getCellData(UIMap.filePath(), UIMap.sheetName(), "Industry Type");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dropValueToSelect;
	}

	public static String accoutRow(WebDriver driver, By accountRows, By accountCols, By accountColText,
			By accountTable) {

		Table = driver.findElement(accountTable);
		List<WebElement> rows = Table.findElements(By.xpath("(//div[@class='ng-isolate-scope'])"));
		rows_count = rows.size();

		for (int i = 1; i <= rows_count; i++) {
			WebElement row = Table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]"));
			List<WebElement> cols = row.findElements(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div"));
			cols_count = cols.size();
			accountName = Table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[1]")).getText();
			if (accountName
					.equalsIgnoreCase(ReadExcelData.getCellData(UIMap.filePath(), UIMap.sheetName(), "Account Name"))) {

				for (int j = 1; j < cols_count; j++) {
					WebElement col = row
							.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"));
					if (j == cols_count - 1) {
						cellText = cellText + col.getText();
					} else {
						cellText = cellText + col.getText() + ",";
					}
				}
			}
		}
		System.out.println(cellText);
		return cellText;
		// Table.findElements(By.xpath("(//div[@class='ng-isolate-scope'])["+i+"])"));

	}

	public static String headerNames(WebDriver driver, By tableHeaderNames) {

		List<WebElement> headers = driver.findElements(tableHeaderNames);
		int header_count = headers.size();
		for (int i = 0; i < header_count-1; i++) {
			if (i == header_count-2) {
				headerText = headerText + headers.get(i).getText();
			} else {
				headerText = headerText + headers.get(i).getText() + ",";
			}
		}
		return headerText;
	}

}
