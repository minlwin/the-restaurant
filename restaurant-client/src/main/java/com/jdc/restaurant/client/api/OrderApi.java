package com.jdc.restaurant.client.api;

import java.util.List;
import java.util.Map;

import com.jdc.restaurant.client.dto.Order;
import com.jdc.restaurant.client.dto.OrderDto;
import com.jdc.restaurant.client.dto.Sale;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface OrderApi {

	@POST("/orders/sale/{id}")
	Call<Sale> create(@Path("id") long saleId, @Body List<Order> order);

	@PUT("/orders/sale/{id}")
	Call<Sale> update(@Path("id") long saleId, @Body List<Order> order);

	@GET("/orders")
	Call<List<Order>> findAll();
	
	@GET("/orders/search")
	Call<List<OrderDto>> search(@QueryMap Map<String, String> query);

	@GET("/orders/{id}")
	Call<Order> findById(@Path("id") long id);
}
