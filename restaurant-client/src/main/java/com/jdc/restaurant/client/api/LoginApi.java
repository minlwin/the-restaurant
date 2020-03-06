package com.jdc.restaurant.client.api;

import com.jdc.restaurant.client.dto.LoginDto;
import com.jdc.restaurant.client.dto.LoginResultDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

	@POST("auth/login")
	Call<LoginResultDto> login(@Body LoginDto dto);
}
