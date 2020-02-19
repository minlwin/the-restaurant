package com.jdc.restaurant.controller;

import java.util.function.Consumer;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Table;
import com.jdc.restaurant.utils.ModalUtils.ModalController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TableEdit implements ModalController<Table>{

    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private TextField tableNumber;

    @FXML
    private TextField seats;
    
    private Consumer<Table> saveHandler;
    private Table data;

    @FXML
    void close() {
    	title.getScene().getWindow().hide();
    }

    @FXML
    void save() {
    	try {
    		
    		if(null == data) {
        		data = new Table();
    		}

    		data.setTableNumber(tableNumber.getText());
    		data.setSeats(Integer.parseInt(seats.getText()));
    		
    		saveHandler.accept(data);
    		
    		close();
			
		} catch (RestaurantAppException e) {
			message.setText(e.getMessage());
		} catch (NumberFormatException e) {
			message.setText("Please Enter Seats with Number!");
		}
    }

	public void init(Table table, Consumer<Table> saveListener) {
		title.setText(null == table ? "Add New Table" : "Edit Table");
		this.message.setText("");
		this.data = table;
		this.saveHandler = saveListener;
		
		if(null != table) {
			tableNumber.setText(table.getTableNumber());
			seats.setText(String.valueOf(table.getSeats()));
		}
	}

}
