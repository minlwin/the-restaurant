package com.jdc.restaurant.model;

import static com.jdc.restaurant.utils.ValidationUtils.notEmptyStringInput;
import static com.jdc.restaurant.utils.ValidationUtils.notNull;
import static com.jdc.restaurant.utils.ValidationUtils.notZeroNumberInputs;

import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.TableApi;
import com.jdc.restaurant.client.dto.Table;

public class TableModel {
	
	private TableApi api;
	private static TableModel model;
	
	private TableModel() {
		this.api = RestaurantClientFactory.generate(TableApi.class);
	}
	
	public static TableModel getModel() {
		if(null == model) {
			model = new TableModel();
		}
		return model;
	}

	public List<Table> search(String tableNumber) {
		
		try {
			return api.search(tableNumber).execute().body();
		} catch (Exception e) {
			throw new RestaurantAppException("API Error, Please check Network Connection.");
		}
	}

	public void save(Table table) {
		
		validate(table);
		
		try {
			if(table.getId() == 0) {
				api.create(table).execute();
			} else {
				api.update(table).execute();
			}
		} catch (Exception e) {
			throw new RestaurantAppException("API Error, Please check Network Connection.");
		}
	}

	private void validate(Table table) {

		notNull(table, Table.class);
		
		notEmptyStringInput(table.getTableNumber(), "Table Number");
		
		notZeroNumberInputs(table.getSeats(), "Seats for Table.");
	}
}
