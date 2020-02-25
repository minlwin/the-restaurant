package com.jdc.restaurant.model;

import static com.jdc.restaurant.utils.ValidationUtils.notEmptyStringInput;
import static com.jdc.restaurant.utils.ValidationUtils.notNull;

import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.CategoryClent;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.utils.StringUtils;

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
		
		if(StringUtils.someIsEmpty(category.getBackColor(), category.getFillColor())) {
			throw new RestaurantAppException("Please select Color!");
		}
	}

	public List<Category> search(String name) {
		return client.search(name);
	}

	public List<Category> findAll() {
		return client.findAll();
	}
}
