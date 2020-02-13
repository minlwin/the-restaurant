package com.jdc.restaurant.client.utils;

import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.CategoryApi;
import com.jdc.restaurant.client.api.MenuApi;
import com.jdc.restaurant.client.api.TableApi;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.client.dto.Table;

public class SaleHelper {

	private Table table;
	private Menu menu;
	
	public SaleHelper() {
		
		try {
			Category c  = new Category();
			c.setName("Chinese");
			
			Menu m = new Menu();
			m.setName("Fride Rice");
			m.setPrice(2000);
			m.setSize("Small");
			
			m.setCategory(RestaurantClientFactory.generate(CategoryApi.class).create(c).execute().body());
			
			this.menu = RestaurantClientFactory.generate(MenuApi.class).create(m).execute().body();
			
			Table t = new Table();
			t.setSeats(4);
			t.setTableNumber("A01");
			
			table = RestaurantClientFactory.generate(TableApi.class).create(t).execute().body();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Table getTable() {
		return table;
	}
	
	public Menu getMenu() {
		return menu;
	}
}
