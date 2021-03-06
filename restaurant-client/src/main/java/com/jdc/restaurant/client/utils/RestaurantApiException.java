package com.jdc.restaurant.client.utils;

public class RestaurantApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RestaurantApiException(String message) {
		super(message);
	}

	public RestaurantApiException() {
		super("Api Error. Please check Internet Connection.");
	}
}
