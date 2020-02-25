package com.jdc.restaurant.client;

import java.util.List;

import com.jdc.restaurant.client.api.TableApi;
import com.jdc.restaurant.client.dto.Table;
import com.jdc.restaurant.client.utils.RequestUtils;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;

public class TableClient {

	private TableApi api;
	
	public TableClient() {
		api = RestaurantClientFactory.generate(TableApi.class);
	}
	
	public Table create(Table data) {
		return RequestUtils.execute(api.create(data));
	}
	
	public List<Table> upload(List<Table> data) {
		return RequestUtils.execute(api.upload(data));
	}

	public Table update(Table data) {
		return RequestUtils.execute(api.update(data));
	}
	
	public Table findById(long id) {
		return RequestUtils.execute(api.findById(id));
	}
	
	public List<Table> findAll() {
		return RequestUtils.execute(api.findAll());
	}
	
	public List<Table> search(String number) {
		return RequestUtils.execute(api.search(number));
	}

}
