package com.jdc.restaurant.client.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.restaurant.client.api.OrderApi;
import com.jdc.restaurant.client.dto.Sale;
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
			
			Sale sale = api.create(helper.getSale().getId(), Arrays.asList(od)).execute().body();
			
			assertEquals(1, sale.getOrders().size());
			
			assertEquals(6000, sale.getSubTotal());
			assertEquals(300, sale.getTax());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void findByIdTest() {
		try {
			
			com.jdc.restaurant.client.dto.Order od = api.findById(1).execute().body();
			
			assertEquals(helper.getMenu().getId(), od.getMenu().getId());
			assertEquals(helper.getMenu().getCategory().getId(), od.getMenu().getCategory().getId());
			assertEquals(3, od.getQuantity());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void findAllTest() {
		try {
			
			List<com.jdc.restaurant.client.dto.Order> list = api.findAll().execute().body();
			assertEquals(1, list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	void updateTest() {
		try {
			com.jdc.restaurant.client.dto.Order od = api.findById(1).execute().body();
			od.setQuantity(1);
			
			Sale sale = api.update(1, Arrays.asList(od)).execute().body();

			assertEquals(2000, sale.getSubTotal());
			assertEquals(100, sale.getTax());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
