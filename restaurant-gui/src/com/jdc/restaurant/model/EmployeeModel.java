package com.jdc.restaurant.model;

import static com.jdc.restaurant.utils.ValidationUtils.notEmptyStringInput;
import static com.jdc.restaurant.utils.ValidationUtils.notEmptyStringSelect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdc.restaurant.client.EmployeeClient;
import com.jdc.restaurant.client.dto.Employee;

public class EmployeeModel {

	private EmployeeClient client;
	
	private static EmployeeModel model;
	
	private EmployeeModel() {
		client = new EmployeeClient();
	}
	
	public static EmployeeModel getModel() {
		
		if(null == model) {
			model = new EmployeeModel();
		}
		
		return model;
	}
	
	public void save(Employee emp) {
		
		validate(emp);
		
		if(emp.getId() == 0) {
			client.create(emp);
		} else {
			client.update(emp);
		}
	}

	private void validate(Employee emp) {

		notEmptyStringInput(emp.getName(), "Employee");
		notEmptyStringSelect(emp.getRole(), "Role of Employee");
		notEmptyStringInput(emp.getPhone(), "Phone Number");
		notEmptyStringInput(emp.getEmail(), "Email Address");
	}

	public List<Employee> search(String name, String phone) {

		Map<String, String> query = new HashMap<>();
		query.put("name", name);
		query.put("phone", phone);
		
		return client.search(query);
	}
	
	public enum Role {
		Admin, Counter, Waiter, Owner, Kitchen
	}
}
