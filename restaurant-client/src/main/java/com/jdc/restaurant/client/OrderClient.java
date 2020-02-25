package com.jdc.restaurant.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jdc.restaurant.client.api.OrderApi;
import com.jdc.restaurant.client.dto.Order;
import com.jdc.restaurant.client.dto.OrderDto;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.client.utils.RequestUtils;
import com.jdc.restaurant.client.utils.RestaurantApiException;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;

public class OrderClient {

	private OrderApi api;
	
	public OrderClient() {
		api = RestaurantClientFactory.generate(OrderApi.class);
	}
	
	public Sale create(long saleId, List<Order> order) {
		try {
			return api.create(saleId, order).execute().body();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RestaurantApiException();
		}
	}
	
	public Sale update(long saleId, List<Order> order) {
		try {
			return api.update(saleId, order).execute().body();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RestaurantApiException();
		}
	}
	
	public List<Order> findAll() {
		return RequestUtils.execute(api.findAll());
	}
	
	public List<OrderDto> search(Map<String, String> query) {
		try {
			return api.search(query).execute().body();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RestaurantApiException();
		}
	}
	
	public Order findById(long id) {
		return RequestUtils.execute(api.findById(id));
	}

}
