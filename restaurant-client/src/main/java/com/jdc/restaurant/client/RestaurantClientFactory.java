package com.jdc.restaurant.client;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RestaurantClientFactory {

	private static final String BASE_URL = "http://localhost:3000/";
	
	private static final Retrofit.Builder builder;
	
	static {
		builder = new Retrofit.Builder()
				.addConverterFactory(JacksonConverterFactory.create())
				.baseUrl(BASE_URL);
	}
	
	
	public static<T> T generate(Class<T> type) {
		return builder.build().create(type);
	}
}
