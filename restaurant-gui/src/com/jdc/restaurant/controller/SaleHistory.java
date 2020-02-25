package com.jdc.restaurant.controller;

import java.util.List;

import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.model.SaleModel;
import com.jdc.restaurant.model.SaleModel.Status;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SaleHistory {
	
	@FXML
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

    @FXML
    private ComboBox<Status> status;

	@FXML
    private TextField tableNumber;

    @FXML
    private TableView<Sale> table;
    
    @FXML
    private void initialize() {
    	status.getItems().addAll(Status.values());
    	
    	table.setOnMouseClicked(event -> {
    		if(event.getClickCount() == 2) {
    			Sale sale = table.getSelectionModel().getSelectedItem();
    			if(null != sale) {
    				SaleDetails.show(sale.getId());
    			}
    		}
    	});
    }

    @FXML
    void search() {
    	
    	List<Sale> list = SaleModel.getModel().search(dateFrom.getValue(), 
    			dateTo.getValue(), status.getValue(), tableNumber.getText());
    	
    	table.getItems().clear();
    	
    	table.getItems().addAll(list);
    }

}
