package com.jdc.restaurant.client.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.restaurant.client.utils.DatabaseCleanner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderClientTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseCleanner.clean("sale_details");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void createTest() {
		try {
			
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
