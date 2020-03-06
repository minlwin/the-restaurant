package com.jdc.restaurant.client;

import com.jdc.restaurant.client.api.LoginApi;
import com.jdc.restaurant.client.dto.Employee;
import com.jdc.restaurant.client.dto.LoginDto;
import com.jdc.restaurant.client.dto.LoginResultDto;
import com.jdc.restaurant.client.utils.RestaurantApiException;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;
import com.jdc.restaurant.client.utils.SessionContext;

public class LoginClient {

	private LoginApi api;
	
	public LoginClient() {
		this.api = RestaurantClientFactory.generate(LoginApi.class);
	}
	
	public Employee login(String email, String password) {
		
		try {
			
			LoginDto login = new LoginDto();
			login.setUsername(email);
			login.setPassword(password);
			
			LoginResultDto result = api.login(login).execute().body();
			SessionContext.setToken(result.getToken());
			
			return result.getUser();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RestaurantApiException();
		}
	}
}
