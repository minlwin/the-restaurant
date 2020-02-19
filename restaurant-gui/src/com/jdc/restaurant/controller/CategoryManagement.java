package com.jdc.restaurant.controller;

import java.util.List;

import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.controller.card.CategoryCard;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class CategoryManagement {

    @FXML
    private TextField schName;

    @FXML
    private TilePane container;

    @FXML
    private void addNew() {
    	ModalUtils.show(CategoryEdit.class, null, this::save);
    }

    @FXML
    private void search() {
    	
    	container.getChildren().clear();
    	
    	List<Category> list = CategoryModel.getModel().search(schName.getText());
    	
    	list.stream().map(c -> new CategoryCard(c, Icons.EDIT, this::save))
    		.forEach(container.getChildren()::add);
    }
    
    private void save(Category c) {
    	CategoryModel.getModel().save(c);
    	search();
    }

}
