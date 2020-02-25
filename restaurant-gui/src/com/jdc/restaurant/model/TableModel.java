package com.jdc.restaurant.model;

import static com.jdc.restaurant.utils.ValidationUtils.notEmptyStringInput;
import static com.jdc.restaurant.utils.ValidationUtils.notNull;
import static com.jdc.restaurant.utils.ValidationUtils.notZeroNumberInputs;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.TableClient;
import com.jdc.restaurant.client.dto.Table;

public class TableModel {
	
	private TableClient client;
	private static TableModel model;
	
	private TableModel() {
		this.client = new TableClient();
	}
	
	public static TableModel getModel() {
		if(null == model) {
			model = new TableModel();
		}
		return model;
	}

	public List<Table> search(String tableNumber) {
		return client.search(tableNumber);
	}

	public void save(Table table) {
		
		validate(table);
		
		if(table.getId() == 0) {
			client.create(table);
		} else {
			client.update(table);
		}
	}

	private void validate(Table table) {

		notNull(table, Table.class);
		
		notEmptyStringInput(table.getTableNumber(), "Table Number");
		
		notZeroNumberInputs(table.getSeats(), "Seats for Table.");
	}

	public void upload(File file) {

		if(null != file) {
			
			try {
				List<String> lines = Files.readAllLines(file.toPath());
				
				List<Table> tables = new ArrayList<>();
				
				for(String line : lines) {
					
					String [] array = line.split("\t");
					
					Table table = new Table();
					table.setTableNumber(array[0]);
					table.setSeats(Long.parseLong(array[1]));
					
					validate(table);
					
					tables.add(table);
					
				}
				
				if(!tables.isEmpty()) {
					client.upload(tables);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RestaurantAppException("Please check your up load file layout!");
			}
			
		}		
	}
}
