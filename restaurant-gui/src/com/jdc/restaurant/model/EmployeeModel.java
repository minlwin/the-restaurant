package com.jdc.restaurant.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.EmployeeApi;
import com.jdc.restaurant.client.dto.Employee;
import static com.jdc.restaurant.utils.ValidationUtils.*;

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
			throw new RestaurantAppException();
		}
	}

	private void validate(Employee emp) {

		notEmptyStringInput(emp.getName(), "Employee");
		notEmptyStringSelect(emp.getRole(), "Role of Employee");
		notEmptyStringInput(emp.getPhone(), "Phone Number");
		notEmptyStringInput(emp.getEmail(), "Email Address");
	}

	public List<Employee> search(String name, String phone) {

		try {
			
			Map<String, String> query = new HashMap<>();
			query.put("name", name);
			query.put("phone", phone);
			
			return api.search(query).execute().body();
			
		} catch (Exception e) {
			throw new RestaurantAppException();
		}
	}
	
	public enum Role {
		Admin, Counter, Waiter, Owner, Kitchen
	}
}
