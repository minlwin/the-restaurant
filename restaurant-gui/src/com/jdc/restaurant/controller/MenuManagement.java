package com.jdc.restaurant.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.controller.card.MenuCard;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.model.MenuModel;
import com.jdc.restaurant.utils.AutoCompleteUtils;
import com.jdc.restaurant.utils.CardWidthUtils;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;
import com.jdc.restaurant.utils.StringUtils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MenuManagement {

    @FXML
    private TextField schCategory;

    @FXML
    private TextField schName;

    @FXML
    private TilePane container;
    
    private ObjectProperty<Category> category;
    
    @FXML
    private void initialize() {
    	
    	category = new SimpleObjectProperty<>();
    	category.addListener((a,b,c) -> search());
    	
    	schCategory.textProperty().addListener((a,b,c) -> {
    		if(StringUtils.isEmpty(c)) {
    			search();
    		}
    	});

    	AutoCompleteUtils.attach(schCategory, 
    			CategoryModel.getModel()::search, c -> this.category.set(c), 1);
    	
    	search();
    }

    @FXML
    private void addNew() {
    	ModalUtils.show(MenuEdit.class, null, this::save);
    }

    @FXML
    void search() {
    	
    	container.getChildren().clear();
    	
    	List<Menu> list = MenuModel.getModel().search(category.get(), schName.getText());

    	DoubleProperty cardWidth = CardWidthUtils.getWidth(container.widthProperty(), 240.0, 10.0);
    	
    	list.stream().map(m -> new MenuCard(m, Icons.EDIT, this::save, cardWidth))
    		.forEach(container.getChildren()::add);
    }

    @FXML
    void upload() {
    	
    	try {
        	FileChooser fc = new FileChooser();
        	fc.setTitle("Menu File Upload");
        	fc.getExtensionFilters().add(new ExtensionFilter("Tab Separated Text File", Arrays.asList("*.tsv")));
        	
        	File file = fc.showOpenDialog(container.getScene().getWindow());
        	
        	MenuModel.getModel().upload(category.get(), file);
        	
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
