package com.jdc.restaurant.model;

import static com.jdc.restaurant.utils.ValidationUtils.notEmptyStringInput;
import static com.jdc.restaurant.utils.ValidationUtils.*;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.CategoryClent;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.CategoryDto;

public class CategoryModel {

	private CategoryClent client;
	private static CategoryModel model;
	
	private CategoryModel() {
		client = new CategoryClent();
	}
	
	public static CategoryModel getModel() {
		if(null == model) {
			model = new CategoryModel();
		}
		return model;
	}
	
	public List<String> types() {
		return client.types();
	}
	
	public void save(Category data) {
		
		validate(data);
		
		if(data.getId() == 0) {
			client.create(data);
		} else {
			client.update(data);
		}
	}
	
	private void validate(Category category) {
		
		notNull(category, Category.class);
			
		notNullSelect(category.getType(), "Type");
		notEmptyStringInput(category.getName(), "Category Name");
		
	}

	public List<Category> search(String type, String name) {
		Map<String, String> query = new HashMap<String, String>();
		query.put("type", type);
		query.put("name", name);
		return client.search(query);
	}

	public List<CategoryDto> searchWithMenus(String type, String name) {
		Map<String, String> query = new HashMap<String, String>();
		query.put("type", type);
		query.put("name", name);
		return client.searchWithMenus(query);
	}

	public List<Category> findAll() {
		return client.findAll();
	}

	public void upload(String type, File file) {

		if(null != file) {
			
			try {
				List<String> lines = Files.readAllLines(file.toPath());
				
				List<Category> categories = new ArrayList<>();
				
				for(String line : lines) {
					
					String [] array = line.split("\t");
					
					Category category = new Category();
					category.setName(array[0]);
					category.setType(type);
					validate(category);
					
					categories.add(category);
					
				}
				
				if(!categories.isEmpty()) {
					client.upload(categories);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RestaurantAppException("Please check your up load file layout!");
			}
			
		}			
	}
}
