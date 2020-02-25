package com.jdc.restaurant.controller;

import java.util.List;

import com.jdc.restaurant.client.dto.OrderDto;
import com.jdc.restaurant.model.OrderModel;
import com.jdc.restaurant.model.OrderModel.State;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderStatus {

    @FXML
    private ComboBox<State> status;

    @FXML
    private TextField tableNumber;

    @FXML
    private TableView<OrderDto> table;
    
    @FXML
    private void initialize() {
    	
    	status.getItems().addAll(State.values());
    	
    	MenuItem cooking = new MenuItem("Cooking");
    	cooking.setOnAction(e -> changeState(State.Coocking));
    	
    	MenuItem cooked = new MenuItem("Cooked");
    	cooked.setOnAction(e -> changeState(State.Cooked));

    	MenuItem finish = new MenuItem("Finished");
    	finish.setOnAction(e -> changeState(State.Finished));

    	MenuItem remind = new MenuItem("Remind");
    	remind.setOnAction(event -> {
        	OrderModel.getModel().remind(table.getSelectionModel().getSelectedItem());
        	search();
    	});
    	
    	MenuItem cancel = new MenuItem("Cancel");
    	cancel.setOnAction(e -> changeState(State.Cancel));
    	
    	table.setContextMenu(new ContextMenu(
    			cooking, cooked, finish,
    			new SeparatorMenuItem(),
    			remind, cancel
    	));
    	
    }
    
    private void changeState(State state) {
    	OrderModel.getModel().changeOrderState(state, table.getSelectionModel().getSelectedItem());
    	search();
    }

    @FXML
    void search() {
    	
    	List<OrderDto> orders = OrderModel.getModel().findOrders(tableNumber.getText(), status.getValue());
    	
    	table.getItems().clear();
    	
    	table.getItems().addAll(orders);
    }

}
