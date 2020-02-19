package com.jdc.restaurant.utils;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class ResponseListener<T> implements Callback<T> {

	@Override
	public void onFailure(Call<T> call, Throwable e) {
	}
}
