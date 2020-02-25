package com.jdc.restaurant.model;

import java.util.Date;
import java.util.List;

import com.jdc.restaurant.client.SaleClient;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.client.dto.Table;
import com.jdc.restaurant.model.OrderModel.State;
import com.jdc.restaurant.utils.ValidationUtils;

public class SaleModel {
	
	private SaleClient client;
	
	private static SaleModel model;
	
	public enum Status {
		Active, Paid, Cancel
	}
	
	private SaleModel() {
		client = new SaleClient();
	}
	
	public static SaleModel getModel() {
		
		if(null == model) {
			model = new SaleModel();
		}
		return model;
	}

	public void create(Sale sale) {

		validate(sale);
		
		sale.setDate(new Date());
		sale.setStatus(Status.Active.name());
		client.create(sale);
	}
	
	private void validate(Sale sale) {
		ValidationUtils.notNullSelect(sale.getTable(), Table.class);
	}

	public List<Sale> getActiveVoucher() {
		return client.getActiveVouchers();
	}

	public Sale findById(long saleId) {
		return client.findById(saleId);
	}

	public void paid(Sale sale) {

		sale.setStatus(Status.Paid.name());
		
		sale.getValidOrders().forEach(od -> od.setStatus(State.Finished.name()));
		
		client.update(sale);
	}



}
