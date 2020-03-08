package com.jdc.restaurant.client;

import java.io.IOException;

import com.jdc.commons.utils.StringsUtils;
import com.jdc.restaurant.client.api.LoginApi;
import com.jdc.restaurant.client.dto.Employee;
import com.jdc.restaurant.client.dto.LoginDto;
import com.jdc.restaurant.client.dto.LoginResultDto;
import com.jdc.restaurant.client.utils.RestaurantApiException;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;
import com.jdc.restaurant.client.utils.SessionContext;

import retrofit2.Response;

public class LoginClient {

	private LoginApi api;
	
	public LoginClient() {
		this.api = RestaurantClientFactory.generate(LoginApi.class);
	}
	
	public Employee login(String email, String password) {
		
		try {
			
			if(StringsUtils.isEmpty(email)) {
				throw new RestaurantApiException("Please enter yor email as login id.");
			}
			
			if(StringsUtils.isEmpty(password)) {
				throw new RestaurantApiException("Please enter password.");
			}
			
			LoginDto login = new LoginDto();
			login.setUsername(email);
			login.setPassword(password);
			
			Response<LoginResultDto> resp = api.login(login).execute();
			
			if(resp.code() == 401) {
				throw new RestaurantApiException("Please check your login informations.");
			}
			
			if(!resp.isSuccessful()) {
				throw new RestaurantApiException("Please check your login informations.");
			}
			
			LoginResultDto result = api.login(login).execute().body();
			SessionContext.setToken(result.getToken());
			
			return result.getUser();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RestaurantApiException();
		}
	}
}
