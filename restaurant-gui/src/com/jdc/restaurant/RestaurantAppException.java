package com.jdc.restaurant;

public class RestaurantAppException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RestaurantAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestaurantAppException(String message) {
		super(message);
	}
	
	public RestaurantAppException() {
		super("API Error, Please check Network Connection.");
	}

}
