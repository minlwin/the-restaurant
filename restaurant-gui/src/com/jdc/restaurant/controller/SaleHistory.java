package com.jdc.restaurant.controller;

import java.util.List;

import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.model.SaleModel;
import com.jdc.restaurant.model.SaleModel.Status;
import com.jdc.restaurant.utils.DecimalFormatCellValueFactory;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
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
    private TableColumn<Sale, String> colSubTotal;
    @FXML
    private TableColumn<Sale, String> colTax;
    @FXML
    private TableColumn<Sale, String> colTotal;
    
    
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
    	
    	colSubTotal.setCellValueFactory(new DecimalFormatCellValueFactory<Sale>(sale -> sale.getSubTotal()));
    	colTax.setCellValueFactory(new DecimalFormatCellValueFactory<Sale>(sale -> sale.getTax()));
    	colTotal.setCellValueFactory(new DecimalFormatCellValueFactory<Sale>(sale -> sale.getTotal()));

    }

    @FXML
    void search() {
    	
    	List<Sale> list = SaleModel.getModel().search(dateFrom.getValue(), 
    			dateTo.getValue(), status.getValue(), tableNumber.getText());
    	
    	table.getItems().clear();
    	
    	table.getItems().addAll(list);
    }

}
