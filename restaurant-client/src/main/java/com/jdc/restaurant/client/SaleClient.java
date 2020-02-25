package com.jdc.restaurant.client;

import java.util.List;

import com.jdc.restaurant.client.api.SaleApi;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.client.utils.RequestUtils;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;

public class SaleClient {

	private SaleApi api;
	
	public SaleClient() {
		api = RestaurantClientFactory.generate(SaleApi.class);
	}
	
	public Sale create(Sale data) {
		return RequestUtils.execute(api.create(data));
	}
	
	public Sale update(Sale data) {
		return RequestUtils.execute(api.update(data));
	}
	
	public List<Sale> findAll() {
		return RequestUtils.execute(api.findAll());
	}
	
	public Sale findById(long id) {
		return RequestUtils.execute(api.findById(id));
	}
	
	public List<Sale> getActiveVouchers() {
		return RequestUtils.execute(api.getActiveVouchers());
	}

}
