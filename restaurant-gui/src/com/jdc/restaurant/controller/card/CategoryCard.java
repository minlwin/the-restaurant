package com.jdc.restaurant.controller.card;

import java.util.function.Consumer;

import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.controller.CategoryEdit;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class CategoryCard extends HBox{

	
	public CategoryCard(Category data, Icons icon, Consumer<Category> listener, ReadOnlyDoubleProperty widthProp) {
		
		getStyleClass().add("card");
		
		prefWidthProperty().bind(widthProp);
		
		// icon box
		SVGPath svg = icon.getSvg();
		
		
		VBox iconBox = new VBox(svg);
		iconBox.setOnMouseClicked(event -> {
			ModalUtils.show(CategoryEdit.class, data, listener);
		});
		
		Label name = new Label(data.getName());
		
		name.getStyleClass().add("title");
		
		VBox dataBox = new VBox(name);
		
		getChildren().addAll(iconBox, dataBox);
	}
}
