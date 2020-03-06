package com.jdc.restaurant.client.dto;

public class LoginResultDto {

	private Employee user;
	private String token;

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
