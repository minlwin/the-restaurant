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
import com.jdc.restaurant.client.MenuClient;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.utils.StringUtils;

public class MenuModel {

	private MenuClient client;
	private static MenuModel model;
	
	public enum Size {
		Regular,
		Small,
		Large
	}
	
	private MenuModel() {
		client = new MenuClient();
	}
	
	public static MenuModel getModel() {
		if(null == model) {
			model = new MenuModel();
		}
		return model;
	}
	
	public void save(Menu data) {
		
		validate(data);
		
		if(data.getId() == 0) {
			client.create(data);
		} else {
			client.update(data);
		}
	}
	
	private void validate(Menu menu) {
		
		notNull(menu, Menu.class);
		
		notNull(menu.getCategory(), Category.class);
		
		notEmptyStringInput(menu.getName(), "Code of Menu");

		notEmptyStringInput(menu.getName(), "Name of Menu");
		
		notZeroNumberInputs(menu.getPrice(), "Price of Menu");
		
	}

	public List<Menu> search(String type, Category category, String name) {
		Map<String, String> query = new HashMap<>();
		
		query.put("type", type);
		
		if(null != category) {
			query.put("categoryId", String.valueOf(category.getId()));
		}
		
		if(!StringUtils.isEmpty(name)) {
			query.put("name", name);
		}
		
		return client.search(query);
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
					
					menu.setCode(array[0]);
					menu.setName(array[1]);
					menu.setSize(array[2]);
					menu.setPrice(Integer.parseInt(array[3]));
					
					validate(menu);
					
					menus.add(menu);
					
				}
				
				if(!menus.isEmpty()) {
					client.upload(menus);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RestaurantAppException("Please check your up load file layout!");
			}
			
		}
	}	
}
