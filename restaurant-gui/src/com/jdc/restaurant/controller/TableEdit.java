package com.jdc.restaurant.controller;

import java.util.function.Consumer;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Table;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TableEdit {

    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private TextField tableNumber;

    @FXML
    private TextField seats;
    
    private Consumer<Table> saveHandler;

    @FXML
    void close() {
    	title.getScene().getWindow().hide();
    }

    @FXML
    void save() {
    	try {
    		
    		Table data = new Table();
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

	public static void show(Table table, Consumer<Table> saveListener) {
		// TODO Auto-generated method stub
		
	}

}
