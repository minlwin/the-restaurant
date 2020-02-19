package com.jdc.restaurant.model;

import java.io.File;
import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.MenuApi;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import static com.jdc.restaurant.utils.ValidationUtils.*;

public class MenuModel {

	private MenuApi api;
	private static MenuModel model;
	
	private MenuModel() {
		api = RestaurantClientFactory.generate(MenuApi.class);
	}
	
	public static MenuModel getModel() {
		if(null == model) {
			model = new MenuModel();
		}
		return model;
	}
	
	public void save(Menu data) {
		
		validate(data);
		
		try {
			
			if(data.getId() == 0) {
				api.create(data).execute();
			} else {
				api.update(data).execute();
			}
			
		} catch (Exception e) {
			throw new RestaurantAppException("API Error, Please check Network Connection.");
		}
	}
	
	private void validate(Menu menu) {
		
		notNull(menu, Menu.class);
		
		notNull(menu.getCategory(), Category.class);
		
		notEmptyStringInput(menu.getName(), "Name of Menu");
		
		notZeroNumberInputs(menu.getPrice(), "Price of Menu");
		
	}

	public List<Menu> search(Category category, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void upload(Category category, File file) {
		// TODO Auto-generated method stub
		
	}	
}
