package com.jdc.restaurant.client;

import java.util.List;
import java.util.Map;

import com.jdc.restaurant.client.api.MenuApi;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.client.utils.RequestUtils;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;

public class MenuClient {

	private MenuApi api;
	
	public MenuClient() {
		api = RestaurantClientFactory.generate(MenuApi.class);
	}
	
	public Menu create(Menu data) {
		return RequestUtils.execute(api.create(data));
	}

	public List<Menu> upload(List<Menu> data) {
		return RequestUtils.execute(api.upload(data));
	}

	public Menu update(Menu data) {
		return RequestUtils.execute(api.update(data));
	}

	public List<Menu> findAll() {
		return RequestUtils.execute(api.findAll());
	}
	
	public Menu findById(long id) {
		return RequestUtils.execute(api.findById(id));
	}

	public List<Menu> findByCategory(long id) {
		return RequestUtils.execute(api.findByCategory(id));
	}
	
	public List<Menu> search(Map<String, String> query) {
		return RequestUtils.execute(api.search(query));
	}
}
