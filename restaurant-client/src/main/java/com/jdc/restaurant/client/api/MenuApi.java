package com.jdc.restaurant.client.api;

import java.util.List;

import com.jdc.restaurant.client.dto.Menu;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MenuApi {

	@POST("/products")
	Call<Menu> create(@Body Menu data);

	@PUT("/products")
	Call<Menu> update(@Body Menu data);

	@GET("/products")
	Call<List<Menu>> findAll();
	
	@GET("/products/{id}")
	Call<Menu> findById(@Path("id") long id);
}
