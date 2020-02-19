package com.jdc.restaurant.controller;

import java.io.File;
import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.controller.card.MenuCard;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.model.MenuModel;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MenuManagement {

    @FXML
    private ComboBox<Category> schCategory;

    @FXML
    private TextField schName;

    @FXML
    private TilePane container;
    
    @FXML
    private void initialize() {
    	schCategory.getItems().addAll(CategoryModel.getModel().findAll());
    }

    @FXML
    private void addNew() {
    	ModalUtils.show(MenuEdit.class, null, this::save);
    }

    @FXML
    void search() {
    	
    	container.getChildren().clear();
    	
    	List<Menu> list = MenuModel.getModel().search(schCategory.getValue(), schName.getText());
    	
    	list.stream().map(m -> new MenuCard(m, Icons.EDIT, this::save))
    		.forEach(container.getChildren()::add);
    }

    @FXML
    void upload() {
    	
    	try {
        	FileChooser fc = new FileChooser();
        	fc.setTitle("Menu File Upload");
        	fc.setSelectedExtensionFilter(new ExtensionFilter("Tab Separated File", "*.tsv"));
        	
        	File file = fc.showOpenDialog(container.getScene().getWindow());
        	
        	MenuModel.getModel().upload(schCategory.getValue(), file);
        	
        	search();

    	} catch (RestaurantAppException e) {
    		MessageDialog.show(e.getMessage());
		}
    	
    	
    }
    
    private void save(Menu menu) {
    	MenuModel.getModel().save(menu);
    	search();
    }

}
