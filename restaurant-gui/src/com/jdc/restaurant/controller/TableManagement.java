package com.jdc.restaurant.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Table;
import com.jdc.restaurant.controller.card.TableCard;
import com.jdc.restaurant.model.TableModel;
import com.jdc.restaurant.utils.CardWidthUtils;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class TableManagement {

	@FXML
	private TextField schNumber;

	@FXML
	private TilePane container;

	@FXML
    private void initialize() {
		
		schNumber.textProperty().addListener((a,b,c) -> search());
		
    	search();
    }

	@FXML
	void addNew() {
		ModalUtils.show(TableEdit.class, null, this::save);
	}

	void search() {
		container.getChildren().clear();
		List<Table> list = TableModel.getModel().search(schNumber.getText());

		DoubleProperty cardWidth = CardWidthUtils.getWidth(container.widthProperty(), 240.0, 10.0);
		list.stream().map(table -> new TableCard(table, Icons.EDIT, this::save, cardWidth)).forEach(container.getChildren()::add);
	}
	
	@FXML
	void upload() {
		
    	try {
        	FileChooser fc = new FileChooser();
        	fc.setTitle("Menu File Upload");
        	fc.getExtensionFilters().add(new ExtensionFilter("Tab Separated Text File", Arrays.asList("*.tsv")));
        	
        	File file = fc.showOpenDialog(container.getScene().getWindow());
        	
        	TableModel.getModel().upload(file);

        	search();

    	} catch (RestaurantAppException e) {
    		MessageDialog.show(e.getMessage());
		}
	}

	private void save(Table table) {
		TableModel.getModel().save(table);
		search();
	}

}
