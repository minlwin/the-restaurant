package com.jdc.restaurant.client.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.EmployeeApi;
import com.jdc.restaurant.client.dto.Employee;
import com.jdc.restaurant.client.utils.DatabaseCleanner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeClientTest {
	
	private EmployeeApi api;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseCleanner.clean("employee");
	}

	@BeforeEach
	void setUp() throws Exception {
		api = RestaurantClientFactory.generate(EmployeeApi.class);
	}

	@Test
	@Order(1)
	void createTest() {
		try {

			Employee e = new Employee();
			e.setName("Admin");
			e.setEmail("admin@gmail.com");
			e.setPhone("09782003098");
			e.setPassword("admin");
		
			Employee result = api.create(e).execute().body();
			assertEquals(1, result.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void findByIdTest() {
		try {
			System.out.println("Finde One");
			Employee result = api.findById(1).execute().body();
			assertEquals("Admin", result.getName());
			assertEquals("admin@gmail.com", result.getEmail());
			assertEquals("09782003098", result.getPhone());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void findAllTest() {
		try {
			
			List<Employee> result  = api.findAll().execute().body();
			assertEquals(1, result.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	void updateTest() {
		try {
			
			Employee data = api.findById(1).execute().body();
			data.setName("Update Name");
			
			Employee result = api.update(data).execute().body();
			assertEquals("Update Name", result.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
