package com.jdc.restaurant.controller;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.client.dto.Table;
import com.jdc.restaurant.model.SaleModel;
import com.jdc.restaurant.model.TableModel;
import com.jdc.restaurant.utils.AutoCompleteUtils;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class SaleManagement {

    @FXML
    private TextField tableNumber;

    @FXML
    private TilePane container;
    
    private Table selectedTable;
    
    @FXML
    private void initialize() {
    	AutoCompleteUtils.attach(tableNumber, 
    			table -> TableModel.getModel().search(table), 
    			table -> this.selectedTable = table);
    }

    @FXML
    private void addNew() {
    	
    	try {
        	Sale sale = new Sale();
        	sale.setTable(selectedTable);
        	
        	SaleModel.getModel().save(sale);
        	
        	selectedTable = null;
        	
        	search();
			
		} catch (RestaurantAppException e) {
			MessageDialog.show(e.getMessage());
		}
    	
    }

    @FXML
    private void search() {

    }

}
