package com.jdc.restaurant.controller.card;

import java.util.function.Consumer;

import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.controller.CategoryEdit;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CategoryCard extends HBox{

	
	public CategoryCard(Category data, Icons icon, Consumer<Category> listener) {
		
		getStyleClass().add("card");
		
		// icon box
		VBox iconBox = new VBox(icon.getSvg());
		iconBox.setOnMouseClicked(event -> {
			ModalUtils.show(CategoryEdit.class, data, listener);
		});
		
		Label name = new Label(data.getName());
		name.getStyleClass().add("title");
		Label color = new Label(data.getColorName());
		
		VBox dataBox = new VBox(name, color);
		
		getChildren().addAll(iconBox, dataBox);
	}
}
