package com.mytime.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	public static String getCurrentDate() {
		Date date = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String value = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH);
		return value;		
	}
	
	public static String getFutureDate() {
		Date date = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 2);
		String value = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH);
		return value;		
	}

	public static Map<String, String> getEntireRowOrActionsColumn(String name, WebDriver driver, By accountTable, By tableHeaderNames,
			String colNameOrRow, String moduleFunc) {

		Map<String, String> data = new HashMap<String, String>();
		int rowsCount;
		/*int colsCount;
		String accountName;
		WebElement col;*/
		WebElement table = driver.findElement(accountTable);
		List<WebElement> rows = table.findElements(By.xpath("(//div[@class='ng-isolate-scope'])"));
		rowsCount = rows.size();
		if(moduleFunc.equals("TeamMate")) {
		data = rowSelectionOnProjectModule(driver, table, rowsCount, tableHeaderNames, colNameOrRow, "View", "AddTeamMate");
		}else if(moduleFunc.equals("Project")) {
			data = rowSelectionOnProjectModule(driver, table, rowsCount, tableHeaderNames, colNameOrRow, "Edit", "AddProject");
		}else {
			data = rowSelectionOnAccountOrDomainModule(driver, table, rowsCount, tableHeaderNames, colNameOrRow, name);
		}
		/*for (int i = 1; i <= rowsCount; i++) {
			WebElement row = table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]"));
			List<WebElement> cols = row.findElements(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div"));
			colsCount = cols.size();
			accountName = table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[1]")).getText();
			if (accountName.equalsIgnoreCase(name)) {
				List<WebElement> headers = driver.findElements(tableHeaderNames);
				if ((colNameOrRow.equalsIgnoreCase("Actions")||colNameOrRow.equalsIgnoreCase("ActionsColumn"))){
					colsCount = cols.size();
					for (int j = 1; j <= colsCount; j++) {
						col = row.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"));
						if(j==colsCount) {
							if(actionName.equalsIgnoreCase("Edit")) {
								data.put(headers.get(j - 1).getText(),"(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"+"/p/i[2]");
								break;
							}else if(actionName.equalsIgnoreCase("View")) {
								data.put(headers.get(j - 1).getText(),"(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"+"/p/i[1]");
								break;
							}
						}
					}
				}
				if (colNameOrRow.equalsIgnoreCase("EntireRow")) {
					for (int j = 1; j < colsCount; j++) {
						col = row.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"));
						data.put(headers.get(j - 1).getText(), col.getText());
					}
				}
				break;
			}
		}*/
		return data;
	}
	
	private static Map<String, String> rowSelectionOnProjectModule(WebDriver driver, WebElement table, int rowsCount, By tableHeaderNames, String colNameOrRow, String actionName, String sheetName) {
		Map<String, String> excelData = readDataFromExcel(sheetName);
		Map<String, String> data = new HashMap<>();
		WebElement col;
		String exPName = excelData.get("Project Name");
		String exDName = excelData.get("Domain");
		String exAName = excelData.get("Account");
		for (int i = 1; i <= rowsCount; i++) {
			WebElement row = table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]"));
			List<WebElement> cols = row.findElements(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div"));
			int colsCount = cols.size();
			String projectName = table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[1]")).getText();
			String domainName = table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[2]")).getText();
			String accountName = table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[3]")).getText();
			if (projectName.equalsIgnoreCase(exPName)&&(domainName.equals(exDName))&&(accountName.equals(exAName))) {
				List<WebElement> headers = driver.findElements(tableHeaderNames);
				if ((colNameOrRow.equalsIgnoreCase("Actions")||colNameOrRow.equalsIgnoreCase("ActionsColumn"))){
					colsCount = cols.size();
					for (int j = 1; j <= colsCount; j++) {
						col = row.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"));
						if(j==colsCount) {
							if(actionName.equalsIgnoreCase("Edit")) {
								data.put(headers.get(j - 1).getText(),"(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"+"/p/i[2]");
								break;
							}else if(actionName.equalsIgnoreCase("View")) {
								data.put(headers.get(j - 1).getText(),"(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"+"/p/i[1]");
								break;
							}
						}
					}
				}
				if (colNameOrRow.equalsIgnoreCase("EntireRow")) {
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
	
	private static Map<String, String> rowSelectionOnAccountOrDomainModule(WebDriver driver, WebElement table, int rowsCount, By tableHeaderNames, String colNameOrRow, String name){
		Map<String, String> data = new HashMap<>();
		WebElement col;
		String accOrDmnName = "";
		for (int i = 1; i <= rowsCount; i++) {
			WebElement row = table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]"));
			List<WebElement> cols = row.findElements(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div"));
			int colsCount = cols.size();
			accOrDmnName = table.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[1]")).getText();
			if (accOrDmnName.equalsIgnoreCase(name)) {
				List<WebElement> headers = driver.findElements(tableHeaderNames);
				if ((colNameOrRow.equalsIgnoreCase("Actions")||colNameOrRow.equalsIgnoreCase("ActionsColumn"))){
					colsCount = cols.size();
					for (int j = 1; j <= colsCount; j++) {
						col = row.findElement(By.xpath("(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"));
						if(j==colsCount) {
								data.put(headers.get(j - 1).getText(),"(//div[@class='ng-isolate-scope'])[" + i + "]/div[" + j + "]"+"/p/i");
								break;
						}
					}
				}
				if (colNameOrRow.equalsIgnoreCase("EntireRow")) {
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
	
	private static Map<String, String> readDataFromExcel(String functionality) {
		Map<String, String> excelData = ExcelUtils.getCellData(MyTeamUtils.getExcelPath(), functionality);
		return excelData;
	}
	

}
