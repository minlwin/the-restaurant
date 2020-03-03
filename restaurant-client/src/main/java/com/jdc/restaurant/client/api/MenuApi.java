package com.jdc.restaurant.client.api;

import java.util.List;
import java.util.Map;

import com.jdc.restaurant.client.dto.Menu;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MenuApi {

	@POST("/products")
	Call<Menu> create(@Body Menu data);

	@POST("/products/upload")
	Call<List<Menu>> upload(@Body List<Menu> data);

	@PUT("/products")
	Call<Menu> update(@Body Menu data);

	@GET("/products")
	Call<List<Menu>> findAll();
	
	@GET("/products/{id}")
	Call<Menu> findById(@Path("id") long id);

	@GET("/products/category/{id}")
	Call<List<Menu>> findByCategory(@Path("id") long id);
	
	@GET("/products/search")
	Call<List<Menu>> search(@QueryMap Map<String, String> query);

	@Multipart
	@POST("/products/photo/{id}")
	Call<Menu> uploadPhoto(@Path("id") long id, @Part MultipartBody.Part file);
}
