package com.jdc.restaurant.client;

import java.util.List;
import java.util.Map;

import com.jdc.restaurant.client.api.EmployeeApi;
import com.jdc.restaurant.client.dto.Employee;
import com.jdc.restaurant.client.utils.RequestUtils;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;

public class EmployeeClient {

	private EmployeeApi api;
	
	public EmployeeClient() {
		api = RestaurantClientFactory.generate(EmployeeApi.class);
	}
	
	public Employee create(Employee emp) {
		return RequestUtils.execute(api.create(emp));
	}

	public Employee update(Employee emp) {
		return RequestUtils.execute(api.update(emp));
	}
	
	public Employee findById(long id) {
		return RequestUtils.execute(api.findById(id));
	}
	
	public List<Employee> search(Map<String, String> query) {
		return RequestUtils.execute(api.search(query));
	}

}
