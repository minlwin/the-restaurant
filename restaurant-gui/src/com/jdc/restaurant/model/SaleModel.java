package com.jdc.restaurant.model;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.SaleApi;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.client.dto.Table;
import com.jdc.restaurant.utils.ValidationUtils;

public class SaleModel {
	
	private SaleApi api;
	private static SaleModel model;
	
	public enum Status {
		Active, Paid, Cancel
	}
	
	private SaleModel() {
		api = RestaurantClientFactory.generate(SaleApi.class);
	}
	
	public static SaleModel getModel() {
		
		if(null == model) {
			model = new SaleModel();
		}
		return model;
	}

	public void create(Sale sale) {

		validate(sale);
		
		try {
			
			sale.setDate(new Date());
			sale.setStatus(Status.Active.name());
			api.create(sale).execute();
			
		} catch (Exception e) {
			throw new RestaurantAppException();
		}
	}
	
	private void validate(Sale sale) {
		ValidationUtils.notNullSelect(sale.getTable(), Table.class);
	}

	public List<Sale> getActiveVoucher() {
		try {
			return api.getActiveVouchers().execute().body();
		} catch (IOException e) {
			throw new RestaurantAppException();
		}
	}

	public Sale findById(long saleId) {
		try {
			return api.findById(saleId).execute().body();
		} catch (IOException e) {
			throw new RestaurantAppException();
		}
	}

}
