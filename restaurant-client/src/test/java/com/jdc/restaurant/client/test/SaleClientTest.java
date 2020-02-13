package com.jdc.restaurant.client.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.SaleApi;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.client.utils.DatabaseCleanner;
import com.jdc.restaurant.client.utils.SaleHelper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SaleClientTest {
	
	private SaleApi api;
	private static SaleHelper helper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseCleanner.clean("category", "product", "sale", "sale_details", "tables");
		
		helper = new SaleHelper();
	}

	@BeforeEach
	void setUp() throws Exception {
		api = RestaurantClientFactory.generate(SaleApi.class);
	}

	@Test
	@Order(1)
	void createTest() {
		try {
			
			Sale data = new Sale();
			data.setDate(new Date());
			data.setTable(helper.getTable());
			
			data.addOrder(helper.getMenu());
			
			Sale result = api.create(data).execute().body();
			assertEquals(1, result.getId());
			assertEquals(2000, result.getSubTotal());
			assertEquals(100, result.getTax());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void findByIdTest() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void findAllTest() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	void updateTest() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
