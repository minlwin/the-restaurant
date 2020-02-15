package com.jdc.restaurant.client.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.restaurant.client.api.CategoryApi;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.utils.ClientTestFactory;
import com.jdc.restaurant.client.utils.DatabaseCleanner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryClientTest {
	
	private CategoryApi api;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseCleanner.clean("category");
	}

	@BeforeEach
	void setUp() throws Exception {
		api = ClientTestFactory.generate(CategoryApi.class);
	}

	@Test
	@Order(1)
	void createTest() {
		try {
			
			Category c = new Category();
			c.setName("Chinese");
			c.setColor(20);
			
			Category result = api.create(c).execute().body();
			
			assertEquals(1, result.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void findByIdTest() {
		try {
			
			Category result = api.findById(1).execute().body();
			assertNotNull(result);
			assertEquals("Chinese", result.getName());
			assertEquals(20, result.getColor());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void findAllTest() {
		try {
			
			List<Category> result = api.findAll().execute().body();
			assertEquals(1, result.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	void updateTest() {
		try {
			Category oldObject = api.findById(1).execute().body();
			oldObject.setColor(40);
			
			Category result = api.update(oldObject).execute().body();
			assertEquals(40, result.getColor());		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
