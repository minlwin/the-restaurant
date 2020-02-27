package com.jdc.restaurant.client;

import java.util.List;
import java.util.Map;

import com.jdc.restaurant.client.api.CategoryApi;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.CategoryDto;
import com.jdc.restaurant.client.utils.RequestUtils;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;

public class CategoryClent {
	
	private CategoryApi api;
	
	public CategoryClent() {
		this.api = RestaurantClientFactory.generate(CategoryApi.class);
	}

	public Category create(Category c) {
		return RequestUtils.execute(api.create(c));
	}
	
	public List<Category> upload(List<Category> categories) {
		return RequestUtils.execute(api.upload(categories));
	}

	public Category update(Category c) {
		return RequestUtils.execute(api.update(c));
	}
	
	public Category findById(long id) {
		return RequestUtils.execute(api.findById(id));
	}
	
	public List<String> types() {
		return RequestUtils.execute(api.types());
	}
	
	public List<Category> findAll() {
		return RequestUtils.execute(api.findAll());
	}

	public List<Category> search(Map<String, String> query) {
		return RequestUtils.execute(api.search(query));
	}

	public List<CategoryDto> searchWithMenus(Map<String, String> query) {
		return RequestUtils.execute(api.searchWithMenus(query));
	}
}
