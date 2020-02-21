package com.jdc.restaurant.client.api;

import java.util.List;

import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.client.dto.Voucher;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SaleApi {

	@POST("/sales")
	Call<Sale> create(@Body Sale data);

	@PUT("/sales")
	Call<Sale> update(@Body Sale data);

	@GET("/sales")
	Call<List<Sale>> findAll();
	
	@GET("/sales/{id}")
	Call<Sale> findById(@Path("id") long id);
	
	@GET("/sales/actives")
	Call<List<Voucher>> getActiveVouchers();
}
