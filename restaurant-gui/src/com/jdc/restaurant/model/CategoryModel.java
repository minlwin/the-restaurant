package com.jdc.restaurant.model;

import static com.jdc.restaurant.utils.ValidationUtils.notEmptyStringInput;
import static com.jdc.restaurant.utils.ValidationUtils.notNull;

import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.CategoryApi;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.utils.StringUtils;

public class CategoryModel {

	private CategoryApi api;
	private static CategoryModel model;
	
	private CategoryModel() {
		api = RestaurantClientFactory.generate(CategoryApi.class);
	}
	
	public static CategoryModel getModel() {
		if(null == model) {
			model = new CategoryModel();
		}
		return model;
	}
	
	public void save(Category data) {
		
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
	
	private void validate(Category category) {
		
		notNull(category, Category.class);
		
		notEmptyStringInput(category.getName(), "Category Name");
		
		if(StringUtils.someIsEmpty(category.getColorCode(), category.getColorName())) {
			throw new RestaurantAppException("Please select Color!");
		}
	}

	public List<Category> search(String name) {
		try {
			return api.search(name).execute().body();
		} catch (Exception e) {
			throw new RestaurantAppException("API Error, Please check Network Connection.");
		}
	}

	public List<Category> findAll() {
		try {
			return api.findAll().execute().body();
		} catch (Exception e) {
			throw new RestaurantAppException("API Error, Please check Network Connection.");
		}
	}
}
