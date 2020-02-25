package com.jdc.restaurant.client.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtiles {
	
	private static DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static String getViewDateTime(Date date) {
		try {
			return dateTimeFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
}
