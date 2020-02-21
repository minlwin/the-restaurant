package com.jdc.restaurant.controller;

import java.util.function.Consumer;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.model.MenuModel.Size;
import com.jdc.restaurant.utils.ModalUtils.ModalController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MenuEdit implements ModalController<Menu>{

    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private ComboBox<Category> category;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<Size> size;

    @FXML
    private TextField price;
    
    private Menu menu;
    private Consumer<Menu> listener;
    
    @FXML
    private void initialize() {
    	category.getItems().addAll(CategoryModel.getModel().findAll());
    	size.getItems().addAll(Size.values());
    	size.setValue(Size.Regular);
    }

    @FXML
    private void close() {
    	price.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
    	try {
    		
    		menu.setCategory(category.getValue());
    		menu.setName(name.getText());
    		menu.setSize(size.getValue().name());
    		menu.setPrice(Integer.parseInt(price.getText()));
    		
    		listener.accept(menu);
    		
    		close();
    		 
    	} catch (RestaurantAppException e) {
			message.setText(e.getMessage());
		} catch (NumberFormatException e) {
			message.setText("Please enter digit to Price.");
		}
    }

	@Override
	public void init(Menu data, Consumer<Menu> listener) {

		this.menu = data;
		this.listener = listener;
		
		if(null == data) {
			title.setText("Add New Menu");
			this.menu = new Menu();
		} else {
			title.setText("Edit Menu");
			category.setValue(data.getCategory());
			name.setText(data.getName());
			size.setValue(Size.valueOf(data.getSize()));
			price.setText(String.valueOf(data.getPrice()));
		}
	}

}
