package com.jdc.restaurant.client.api;

import java.util.List;

import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.CategoryDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategoryApi {

	@POST("/categories")
	Call<Category> create(@Body Category data);

	@POST("/categories/upload")
	Call<List<Category>> upload(@Body List<Category> list);

	@PUT("/categories")
	Call<Category> update(@Body Category data);

	@GET("/categories")
	Call<List<Category>> findAll();
	
	@GET("/categories/{id}")
	Call<Category> findById(@Path("id") long id);

	@GET("/categories/search")
	Call<List<Category>> search(@Query("name") String name);

	@GET("/categories/searchwithmenus")
	Call<List<CategoryDto>> searchWithMenus(@Query("name") String name);

}
