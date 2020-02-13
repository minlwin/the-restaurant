package com.jdc.restaurant.client.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.CategoryApi;
import com.jdc.restaurant.client.api.MenuApi;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.client.utils.DatabaseCleanner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MenuClientTest {
	
	private MenuApi api;
	private static Category category;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseCleanner.clean("category", "product");

		category = new Category();
		category.setColor(20);
		category.setName("Chinese");
		
		category = RestaurantClientFactory.generate(CategoryApi.class).create(category).execute().body();
	}

	@BeforeEach
	void setUp() throws Exception {
		api = RestaurantClientFactory.generate(MenuApi.class);
	}

	@Test
	@Order(1)
	void createTest() {
		try {
			
			Menu menu = new Menu();
			menu.setCategory(category);
			menu.setName("Fried Rice");
			menu.setSize("Small");
			menu.setPrice(2000);
			
			Menu result = api.create(menu).execute().body();
			assertEquals(1, result.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void findByIdTest() {
		try {
			
			Menu result = api.findById(1).execute().body();
			assertEquals("Fried Rice", result.getName());
			assertEquals("Small", result.getSize());
			assertEquals(2000, result.getPrice());
			assertEquals("Chinese", result.getCategory().getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void findAllTest() {
		try {
			
			List<Menu> result = api.findAll().execute().body();
			assertEquals(1, result.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	void updateTest() {
		try {
			Menu data = api.findById(1).execute().body();
			data.setName("Hot Pot");
			
			Menu result = api.update(data).execute().body();
			assertEquals("Hot Pot", result.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
