package com.jdc.restaurant.model;

import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.SaleApi;
import com.jdc.restaurant.client.dto.Sale;

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

	public void save(Sale sale) {
		// TODO Auto-generated method stub
		
	}

}
