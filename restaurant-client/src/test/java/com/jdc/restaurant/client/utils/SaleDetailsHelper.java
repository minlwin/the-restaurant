package com.jdc.restaurant.client.utils;

import java.util.Date;

import com.jdc.restaurant.client.api.SaleApi;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.client.dto.Sale;

public class SaleDetailsHelper {
	
	private Sale sale;
	
	private SaleHelper saleHelper;

	public SaleDetailsHelper() {
		
		try {
			
			saleHelper = new SaleHelper();
			
			this.sale = new Sale();
			this.sale.setDate(new Date());
			this.sale.setTable(saleHelper.getTable());
			
			this.sale = ClientTestFactory.generate(SaleApi.class).create(sale).execute().body();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Sale getSale() {
		return sale;
	}
	
	public Menu getMenu() {
		return saleHelper.getMenu();
	}
}
