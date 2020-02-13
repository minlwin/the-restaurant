package com.jdc.restaurant.client.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.restaurant.client.api.OrderApi;
import com.jdc.restaurant.client.utils.ClientTestFactory;
import com.jdc.restaurant.client.utils.DatabaseCleanner;
import com.jdc.restaurant.client.utils.SaleDetailsHelper;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderClientTest {
	
	
	private static SaleDetailsHelper helper;
	private OrderApi api;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseCleanner.clean("category", "product", "sale", "sale_details", "tables");
		
		helper = new SaleDetailsHelper();
	}

	@BeforeEach
	void setUp() throws Exception {
		api = ClientTestFactory.generate(OrderApi.class);
	}

	@Test
	@Order(1)
	void createTest() {
		try {
			
			com.jdc.restaurant.client.dto.Order od = new com.jdc.restaurant.client.dto.Order();
			od.setMenu(helper.getMenu());
			od.setPrice(helper.getMenu().getPrice());
			od.setQuantity(3);
			
			od = api.create(helper.getSale().getId(), od).execute().body();
			
			assertEquals(1, od.getId());
			
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
