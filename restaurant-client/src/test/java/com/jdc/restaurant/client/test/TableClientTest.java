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

import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.TableApi;
import com.jdc.restaurant.client.dto.Table;
import com.jdc.restaurant.client.utils.DatabaseCleanner;

import retrofit2.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TableClientTest {
	
	private TableApi api;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseCleanner.clean("tables");
	}

	@BeforeEach
	void setUp() throws Exception {
		api = RestaurantClientFactory.generate(TableApi.class);
	}

	@Test
	@Order(1)
	void createTest() {
		
		try {
			Table data = new Table();
			data.setSeats(5);
			data.setTableNumber("A-01");
			
			Table result = api.create(data).execute().body();
			
			assertNotNull(result);
			
			assertEquals(1L, result.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(2)
	void findByIdTest() {
		
		try {
			Response<Table> resp = api.findById(1).execute();
			
			Table result = resp.body();
			assertNotNull(result);
			assertEquals(1L, result.getId());
			assertEquals("A-01", result.getTableNumber());
			assertEquals(5, result.getSeats());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	void findAllTest() {
		try {
			Response<List<Table>> resp = api.findAll().execute();
			
			List<Table> result = resp.body();
			assertNotNull(result);
			assertEquals(1, result.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(4)
	void updateTest() {
		
		try {
			
			Table oldObj = api.findById(1).execute().body();
			oldObj.setSeats(4);
			
			api.update(oldObj).execute();
			
			Table newObject = api.findById(1).execute().body();
			assertEquals(4, newObject.getSeats());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
