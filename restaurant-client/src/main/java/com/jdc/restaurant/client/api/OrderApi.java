package com.jdc.restaurant.client.api;

import java.util.List;

import com.jdc.restaurant.client.dto.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderApi {

	@POST("/orders/sale/{id}")
	Call<Order> create(@Path("id") long saleId, @Body Order order);

	@PUT("/orders/sale/{id}")
	Call<Order> update(@Path("id") long saleId, @Body Order order);

	@GET("/orders")
	Call<List<Order>> findAll();
	
	@GET("/orders/{id}")
	Call<Order> findById(@Path("id") long id);
}
