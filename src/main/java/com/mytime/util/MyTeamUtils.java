package com.mytime.util;

public class MyTeamUtils {

	private static String fileSeperator = System.getProperty("file.separator");
	public static String getPropertiesFilePath() {
		return System.getProperty("user.dir") + fileSeperator + "application" + ".properties";
	}
}
