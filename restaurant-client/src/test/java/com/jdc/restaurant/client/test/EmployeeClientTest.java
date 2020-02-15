package com.jdc.restaurant.client.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.restaurant.client.api.EmployeeApi;
import com.jdc.restaurant.client.dto.Employee;
import com.jdc.restaurant.client.utils.ClientTestFactory;
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
		api = ClientTestFactory.generate(EmployeeApi.class);
	}

	@Test
	@Order(1)
	void createTest() {
		try {

			Employee e = new Employee();
			e.setName("Admin");
			e.setRole("Admin");
			e.setEmail("admin@gmail.com");
			e.setPhone("09782003098");
			e.setPassword("admin");
		
			Employee result = api.create(e).execute().body();
			assertEquals(1, result.getId());
			
			System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(result.getCreation()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void findByIdTest() {
		try {

			Employee result = api.findById(1).execute().body();
			assertEquals("Admin", result.getName());
			assertEquals("admin@gmail.com", result.getEmail());
			assertEquals("09782003098", result.getPhone());
			System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(result.getCreation()));
			
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
			for(Employee emp : result) {
				System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(emp.getModification()));
			}
			
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
	
	@Test
	@Order(5)
	void findByNameLikeTest() {
		try {
			
			List<Employee> data = api.search("update").execute().body();
			assertEquals(1, data.size());
			
			for(Employee emp : data) {
				System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(emp.getModification()));
			}

			data = api.search("name").execute().body();
			assertEquals(1, data.size());
			
			
			data = api.search("other").execute().body();
			assertEquals(0, data.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
