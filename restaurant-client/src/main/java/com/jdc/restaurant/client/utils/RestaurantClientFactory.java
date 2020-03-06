package com.jdc.restaurant.client.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RestaurantClientFactory {

	private static final String BASE_URL = "http://localhost:3000/";
	
	private static final OkHttpClient CLIENT;
	
	static {
		
        CLIENT = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .build();
		
	}
	
	public static<T> T generate(Class<T> type) {
		return new Retrofit.Builder()
				.addConverterFactory(JacksonConverterFactory.create())
				.baseUrl(BASE_URL)
				.client(CLIENT)
				.build().create(type);
	}
}
