package com.jdc.restaurant.controller.card;

import java.util.function.Consumer;

import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.controller.CategoryEdit;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class CategoryCard extends HBox{

	
	public CategoryCard(Category data, Icons icon, Consumer<Category> listener) {
		
		getStyleClass().add("card-nocolor");
		setStyle(String.format("-fx-background-color: %s", data.getBackColor()));
		
		// icon box
		Color fill = Color.web(data.getFillColor());
		SVGPath svg = icon.getSvg();
		svg.setFill(fill);
		
		
		VBox iconBox = new VBox(svg);
		iconBox.setOnMouseClicked(event -> {
			ModalUtils.show(CategoryEdit.class, data, listener);
		});
		
		Label name = new Label(data.getName());
		name.setTextFill(fill);
		
		name.getStyleClass().add("title");
		
		VBox dataBox = new VBox(name);
		
		getChildren().addAll(iconBox, dataBox);
	}
}
