package com.jdc.restaurant.model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jdc.restaurant.client.OrderClient;
import com.jdc.restaurant.client.dto.Order;
import com.jdc.restaurant.client.dto.OrderDto;
import com.jdc.restaurant.client.dto.Sale;

public class OrderModel {

	private OrderClient client;
	private static OrderModel model;

	public enum State {
		Ordered, Coocking, Cooked, Finished, Cancel
	}
	
	private OrderModel() {
		client = new OrderClient();
	}
	
	public static OrderModel getModel() {
		
		if(null == model) {
			model = new OrderModel();
		}
		return model;
	}

	public void takeOrder(Sale sale) {
		
		List<Order> newOrders = sale.getOrders().stream().filter(a -> a.getId() == 0).map(od -> {
			od.setStatus(State.Ordered.name());
			od.setOrderTime(new Date());
			return od;
		}).collect(Collectors.toList());
		
		client.create(sale.getId(), newOrders);
	}

	public List<OrderDto> findOrders(String tableNumber, State state) {
		
		Map<String, String> query = new HashMap<>();
		query.put("table", tableNumber);
		
		if(null != state) {
			query.put("status", state.name());
		}
		
		return client.search(query);
	}

	public void changeOrderState(State state, OrderDto dto) {
		
		Order order = new Order(dto);
		order.setStatus(state.name());
		
		client.update(dto.getSale().getId(), Arrays.asList(order));
	}
}
