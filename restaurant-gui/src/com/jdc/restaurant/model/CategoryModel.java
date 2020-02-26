package com.jdc.restaurant.model;

import static com.jdc.restaurant.utils.ValidationUtils.notEmptyStringInput;
import static com.jdc.restaurant.utils.ValidationUtils.notNull;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

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
		
		notEmptyStringInput(category.getName(), "Category Name");
		
	}

	public List<Category> search(String name) {
		return client.search(name);
	}

	public List<CategoryDto> searchWithMenus(String name) {
		return client.searchWithMenus(name);
	}

	public List<Category> findAll() {
		return client.findAll();
	}

	public void upload(File file) {

		if(null != file) {
			
			try {
				List<String> lines = Files.readAllLines(file.toPath());
				
				List<Category> categories = new ArrayList<>();
				
				for(String line : lines) {
					
					String [] array = line.split("\t");
					
					Category category = new Category();
					category.setName(array[0]);
					
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
