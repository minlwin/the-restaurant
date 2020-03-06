package com.jdc.restaurant.utils;

import com.jdc.restaurant.client.dto.Employee;

public class ApplicationContext {

	private static Employee loginUser;
	
	public static void setLoginUser(Employee loginUser) {
		ApplicationContext.loginUser = loginUser;
	}
	
	public static Employee getLoginUser() {
		return loginUser;
	}
}
