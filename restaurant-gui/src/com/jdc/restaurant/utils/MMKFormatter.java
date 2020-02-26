package com.jdc.restaurant.utils;

import java.text.DecimalFormat;

public class MMKFormatter {

	private static final DecimalFormat DF = new DecimalFormat("#,##0 MMK");

	public static String format(int data) {
		return DF.format(data);
	}
}
