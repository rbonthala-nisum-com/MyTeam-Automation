package com.mytime.initialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UIMap 
{
	static Properties properties;
	static String sheetName;
	static String filePath;

	public static void path(String FilePath)
	{
		try
		{
			FileInputStream prop = new FileInputStream(FilePath);
			properties = new Properties();
			properties.load(prop);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static String getData(String ElementName) throws Exception
	{
		//Read value using logical name as key
		String data = properties.getProperty(ElementName);
		return data;
	}
	public static String sheetName() {
		try {
			sheetName = UIMap.getData("sheetName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheetName;
	}
	public static String filePath()
	{
		try {
			filePath = System.getProperty("user.dir") + "\\InputData\\InputData.xlsx";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}
}
