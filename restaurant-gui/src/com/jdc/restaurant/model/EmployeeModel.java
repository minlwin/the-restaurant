package com.jdc.restaurant.model;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.EmployeeApi;
import com.jdc.restaurant.client.dto.Employee;

public class EmployeeModel {

	private EmployeeApi api;
	
	private static EmployeeModel model;
	
	private EmployeeModel() {
		api = RestaurantClientFactory.generate(EmployeeApi.class);
	}
	
	public static EmployeeModel getModel() {
		
		if(null == model) {
			model = new EmployeeModel();
		}
		
		return model;
	}
	
	public void save(Employee emp) {
		
		validate(emp);
		
		try {
			
			if(emp.getId() == 0) {
				api.create(emp).execute();
			} else {
				api.update(emp).execute();
			}
			
		} catch (Exception e) {
			throw new RestaurantAppException("API Error, Please check Network Connection.");
		}
	}

	private void validate(Employee emp) {
		// TODO Auto-generated method stub
		
	}
}
