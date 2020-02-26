package com.jdc.restaurant.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.CategoryDto;
import com.jdc.restaurant.client.utils.RestaurantApiException;
import com.jdc.restaurant.controller.card.CategoryCard;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.utils.CardWidthUtils;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class CategoryManagement {

    @FXML
    private TextField schName;

    @FXML
    private TilePane container;
    
    @FXML
    private void initialize() {
    	
    	schName.textProperty().addListener((a,b,c) -> search());
    	
    	search();
    }
    
    @FXML
    private void addNew() {
    	ModalUtils.show(CategoryEdit.class, null, this::save);
    }
    
    @FXML
    private void upload() {
       	try {
        	FileChooser fc = new FileChooser();
        	fc.setTitle("Category File Upload");
        	fc.getExtensionFilters().add(new ExtensionFilter("Tab Separated Text File", Arrays.asList("*.tsv")));
        	
        	File file = fc.showOpenDialog(container.getScene().getWindow());
        	
        	CategoryModel.getModel().upload(file);
        	
        	search();

    	} catch (RestaurantAppException e) {
    		MessageDialog.show(e.getMessage());
		}    	
    }

    private void search() {
    	
    	try {
        	container.getChildren().clear();
        	
        	List<CategoryDto> list = CategoryModel.getModel().searchWithMenus(schName.getText());
        	
        	DoubleProperty cardWidth = CardWidthUtils.getWidth(container.widthProperty(), 240.0, 10.0);
        	
        	list.stream().map(c -> new CategoryCard(c, Icons.EDIT, this::save, cardWidth))
        		.forEach(container.getChildren()::add);
        	
    	} catch (RestaurantApiException | RestaurantAppException e) {
    		MessageDialog.show(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    private void save(Category c) {
    	CategoryModel.getModel().save(c);
    	search();
    }

}
