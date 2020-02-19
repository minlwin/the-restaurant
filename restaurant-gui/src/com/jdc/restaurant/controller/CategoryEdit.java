package com.jdc.restaurant.controller;

import java.util.function.Consumer;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.utils.ModalUtils.ModalController;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CategoryEdit implements ModalController<Category>{

    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private ColorPicker color;

    @FXML
    private TextField name;
    
    private Category data; 
    private Consumer<Category> listener;

    @FXML
    private void close() {
    	title.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
    	
    	try {
			
    		data.setName(name.getText());
    		
    		Color c = color.getValue();
    		
    		if(null != c) {
    			data.setName(c.toString());
    			data.setColorCode(String.format("#%s", Integer.toHexString(c.hashCode())));
    		}
    		
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
			this.name.setText(data.getName());
			
			if(null != data.getColorName()) {
				this.color.setValue(Color.valueOf(data.getColorName()));
			}
			
		}
	}

}
