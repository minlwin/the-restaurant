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
		
		Color back = Color.valueOf(data.getColorName());
		Color fill = back.getBrightness() < 0.9 ? Color.WHITE : Color.BLACK;

		setStyle(String.format("-fx-background-color: %s", data.getColorCode()));
		
		// icon box
		SVGPath svg = icon.getSvg();
		svg.setFill(fill);
		
		
		VBox iconBox = new VBox(svg);
		iconBox.setOnMouseClicked(event -> {
			ModalUtils.show(CategoryEdit.class, data, listener);
		});
		
		Label name = new Label(data.getName());
		name.setTextFill(fill);
		
		name.getStyleClass().add("title");
		Label color = new Label(data.getColorName());
		color.setTextFill(fill);
		
		VBox dataBox = new VBox(name, color);
		
		getChildren().addAll(iconBox, dataBox);
	}
}
