package com.jdc.restaurant.client.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ClientTestFactory {

	private static final String BASE_URL = "http://localhost:3000/";
	
	private static final Retrofit.Builder builder;
	
	static {
		
		final OkHttpClient client = new OkHttpClient.Builder()
				.callTimeout(5, TimeUnit.MINUTES)
				.readTimeout(5, TimeUnit.MINUTES)
				.connectTimeout(5, TimeUnit.MINUTES)
				.writeTimeout(5, TimeUnit.MINUTES)
				.build();
		
		builder = new Retrofit.Builder()
				.addConverterFactory(JacksonConverterFactory.create())
				.client(client)
				.baseUrl(BASE_URL);
	}
	
	
	public static<T> T generate(Class<T> type) {
		return builder.build().create(type);
	}
}
