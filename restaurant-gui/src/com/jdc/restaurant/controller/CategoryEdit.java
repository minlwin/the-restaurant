package com.jdc.restaurant.controller;

import java.util.function.Consumer;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.utils.ModalUtils.ModalController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CategoryEdit implements ModalController<Category>{

    @FXML
    private Label title;

    @FXML
    private Label message;
    
    @FXML
    private ComboBox<String>  types;

    @FXML
    private TextField name;
    
    private Category data; 
    private Consumer<Category> listener;
    
    @FXML
    private void initialize() {
    	types.getItems().addAll(CategoryModel.getModel().types());
    }

    @FXML
    private void close() {
    	title.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
    	
    	try {
			
    		data.setName(name.getText());
    		data.setType(types.getValue());
    		
    		listener.accept(data);
    		
    		close();
    		
		} catch (RestaurantAppException e) {
			message.setText(e.getMessage());
		}

    }

	public void init(Category data, Consumer<Category> listener) {

		this.data = data;
		this.listener = listener;
		
		if(null == data) {
			this.title.setText("Add New Category");
			this.data = new Category();
		} else  {
			this.title.setText("Edit Category");
			this.types.setValue(data.getType());
			this.name.setText(data.getName());			
		}
	}

}
