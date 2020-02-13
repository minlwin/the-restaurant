package com.jdc.restaurant.client.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseCleanner {

	private static final String USER = "root";
	private static final String URL = "jdbc:mariadb://localhost:3306/nest2_db";
	private static final String PASS = "admin";

	public static void clean(String ... tables) {
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement()) {
			
			stmt.execute("set FOREIGN_KEY_CHECKS = 0");
			
			for(String table : tables) {
				stmt.execute(String.format("truncate table %s", table));
			}
			
			stmt.execute("set FOREIGN_KEY_CHECKS = 1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
