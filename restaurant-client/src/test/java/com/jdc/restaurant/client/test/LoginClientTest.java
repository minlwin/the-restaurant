package com.jdc.restaurant.client.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.restaurant.client.LoginClient;
import com.jdc.restaurant.client.SummaryClient;
import com.jdc.restaurant.client.dto.Employee;
import com.jdc.restaurant.client.dto.SummaryDTO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginClientTest {
	
	static  LoginClient client;
	
	@BeforeAll
	public static void before() {
		client = new LoginClient();
	}
	
	
	@Test
	@Order(1)
	public void loginTest() {
		
		Employee loginUser = client.login("Admin", "Admin");
		assertNotNull(loginUser);
	}
	
	@Test
	@Order(2)
	public void summary() {
		
		SummaryClient summaryClient = new SummaryClient();
		SummaryDTO dto = summaryClient.getSummary();
		assertNotNull(dto);
		
	}

}
