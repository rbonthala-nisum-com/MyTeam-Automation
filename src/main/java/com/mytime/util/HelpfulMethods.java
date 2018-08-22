package com.mytime.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class HelpfulMethods {
	

	//Purpose - to get current system date and time
	public static String getCurrentSystemDateTime()
	{
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;    	
	}
	
	//To get the random number which will be used mostly swhile saving a screenshot
	public static int getRandomNumber()
	{
		Random rand = new Random();
		int numNoRange = rand.nextInt();
		return numNoRange;
	}
	
}
