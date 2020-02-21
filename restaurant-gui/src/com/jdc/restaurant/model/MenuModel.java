package com.jdc.restaurant.model;

import static com.jdc.restaurant.utils.ValidationUtils.notEmptyStringInput;
import static com.jdc.restaurant.utils.ValidationUtils.notNull;
import static com.jdc.restaurant.utils.ValidationUtils.notNullSelect;
import static com.jdc.restaurant.utils.ValidationUtils.notZeroNumberInputs;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.MenuApi;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.utils.StringUtils;

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
			throw new RestaurantAppException();
		}
	}
	
	private void validate(Menu menu) {
		
		notNull(menu, Menu.class);
		
		notNull(menu.getCategory(), Category.class);
		
		notEmptyStringInput(menu.getName(), "Name of Menu");
		
		notZeroNumberInputs(menu.getPrice(), "Price of Menu");
		
	}

	public List<Menu> search(Category category, String name) {
		try {
			Map<String, String> query = new HashMap<>();
			
			if(null != category) {
				query.put("categoryId", String.valueOf(category.getId()));
			}
			
			if(!StringUtils.isEmpty(name)) {
				query.put("name", name);
			}
			
			return api.search(query).execute().body();
			
		} catch (Exception e) {
			throw new RestaurantAppException();
		}
	}

	public void upload(Category category, File file) {

		if(null != file) {
			
			notNullSelect(category, Category.class);
			
			try {
				List<String> lines = Files.readAllLines(file.toPath());
				
				List<Menu> menus = new ArrayList<>();
				
				for(String line : lines) {
					
					String [] array = line.split("\t");
					
					Menu menu = new Menu();
					menu.setCategory(category);
					
					menu.setName(array[0]);
					menu.setSize(array[1]);
					menu.setPrice(Integer.parseInt(array[2]));
					
					validate(menu);
					
					menus.add(menu);
					
				}
				
				if(!menus.isEmpty()) {
					api.upload(menus).execute();
				}
				
				
			} catch (Exception e) {
				throw new RestaurantAppException("Please check your up load file layout!");
			}
			
		}
	}	
}
