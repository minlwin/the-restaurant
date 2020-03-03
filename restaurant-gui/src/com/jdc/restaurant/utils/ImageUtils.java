package com.jdc.restaurant.utils;

public class ImageUtils {

	private static final String BASE_URL = "http://localhost:3000/products/photo";

	public static String getImageUrl(String image) {
		return String.format("%s/%s", BASE_URL, image);
	}
}
