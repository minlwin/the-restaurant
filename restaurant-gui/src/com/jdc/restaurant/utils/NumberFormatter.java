package com.jdc.restaurant.utils;

import java.text.DecimalFormat;

public class NumberFormatter {

	private static final DecimalFormat DF = new DecimalFormat("#,##0 MMK");

	private static final DecimalFormat NO_MMK_DF = new DecimalFormat("#,##0");

	private static final DecimalFormat NO_MMK_KILO_DF = new DecimalFormat("#,##0 K");

	public static String formatMMK(int data) {
		return DF.format(data);
	}
	
	public static String noMMKFormat(int data) {
		
		if(data > 9999) {
			return NO_MMK_KILO_DF.format(data / 1000);
		}
		
		return NO_MMK_DF.format(data);
	}
}
