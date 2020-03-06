package com.jdc.restaurant.client.utils;

public class SessionContext {

	private static String token;
	
	
	public static void setToken(String token) {
		SessionContext.token = token;
	}
	
	public static String getToken() {
		return token == null ? null : String.format("Bearer %s", token);
	}
}
