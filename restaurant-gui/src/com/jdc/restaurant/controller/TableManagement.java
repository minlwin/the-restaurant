package com.jdc.restaurant.controller;

import java.util.List;

import com.jdc.restaurant.client.RestaurantClientFactory;
import com.jdc.restaurant.client.api.TableApi;
import com.jdc.restaurant.client.dto.Table;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableManagement {

    @FXML
    private TextField schNumber;

    @FXML
    private TilePane container;
    
    private TableApi api;
    
    @FXML
    private void initialize() {
    	api = RestaurantClientFactory.generate(TableApi.class);
    }

    @FXML
    void addNew() {
    	TableEdit.show(null, this::save);
    }

    @FXML
    void search() {
    	api.search(schNumber.getText()).enqueue(new Callback<List<Table>>() {
			
			@Override
			public void onResponse(Call<List<Table>> call, Response<List<Table>> resp) {
				
			}
			
			@Override
			public void onFailure(Call<List<Table>> call, Throwable e) {
				
			}
		});
    }
    
    private void save(Table table) {
    	
    }

}
