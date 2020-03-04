package com.jdc.restaurant.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.jdc.restaurant.client.utils.RestaurantApiException;

public class ImageUtils {

	private static final String BASE_URL = "http://localhost:3000/products/photo";

	public static String getImageUrl(String image) {
		try {
			return String.format("%s/%s", BASE_URL, URLEncoder.encode(image, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RestaurantApiException();
		}
	}
}
