package com.jdc.restaurant.controller.card;

import java.util.function.Consumer;

import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.controller.MenuEdit;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class MenuCard extends HBox {

	public MenuCard(Menu menu, Icons icon,Consumer<Menu> listener, ReadOnlyDoubleProperty widthProp) {
		
		getStyleClass().add("card-nocolor");
		prefWidthProperty().bind(widthProp);
		
		
		if(null != icon) {
			// icon box
			SVGPath svg = icon.getSvg();
			
			VBox iconBox = new VBox(svg);
			iconBox.setOnMouseClicked(event -> {
				ModalUtils.show(MenuEdit.class, menu, listener);
			});
			
			getChildren().add(iconBox);
			
		}
		
		
		Label name = new Label(menu.getName());
		name.getStyleClass().add("title");
		
		Label price = new Label(String.format("%d MMK", menu.getPrice()));
		
		Label size = new Label(menu.getSize());

		getChildren().addAll(new VBox(name, size, price));
	}
}
