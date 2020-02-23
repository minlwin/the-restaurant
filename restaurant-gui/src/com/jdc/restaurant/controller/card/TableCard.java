package com.jdc.restaurant.controller.card;

import java.util.function.Consumer;

import com.jdc.restaurant.client.dto.Table;
import com.jdc.restaurant.controller.TableEdit;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TableCard extends HBox {
	
	public TableCard(Table table, Icons icon,Consumer<Table> listener, ReadOnlyDoubleProperty widthProp) {

		super();
		prefWidthProperty().bind(widthProp);
		
		// icon box
		VBox iconBox = new VBox(icon.getSvg());
		iconBox.setOnMouseClicked(event -> {
			ModalUtils.show(TableEdit.class, table, listener);
		});
		
		// data box
		VBox dataBox = new VBox();
		Label title = new Label(table.getTableNumber());
		title.getStyleClass().add("title");
		
		Label seats = new Label(String.format("%d Seats", table.getSeats()));
		
		dataBox.getChildren().addAll(title, seats);
		
		getStyleClass().add("card");
		
		getChildren().addAll(iconBox, dataBox);
	}
	
	

}
