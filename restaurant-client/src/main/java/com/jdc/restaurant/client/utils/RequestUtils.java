package com.jdc.restaurant.client.utils;

import java.io.IOException;

import retrofit2.Call;

public class RequestUtils {

	public static<T> T execute(Call<T> call) {
		try {
			return call.execute().body();
		} catch (IOException e) {
			throw new RestaurantApiException();
		}
	}
}
