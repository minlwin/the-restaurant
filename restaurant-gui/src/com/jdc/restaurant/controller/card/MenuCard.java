package com.jdc.restaurant.controller.card;

import java.util.function.Consumer;

import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.controller.MenuEdit;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class MenuCard extends HBox {

	public MenuCard(Menu menu, Icons icon,Consumer<Menu> listener) {
		
		getStyleClass().add("card-nocolor");
		
		Color fill = Color.web(menu.getCategory().getFillColor());
		setStyle(String.format("-fx-background-color: %s", menu.getCategory().getBackColor()));
		
		// icon box
		SVGPath svg = icon.getSvg();
		svg.setFill(fill);
		
		VBox iconBox = new VBox(svg);
		iconBox.setOnMouseClicked(event -> {
			ModalUtils.show(MenuEdit.class, menu, listener);
		});
		
		Label name = new Label(menu.getName());
		name.getStyleClass().add("title");
		name.setTextFill(fill);
		
		Label price = new Label(String.format("%d MMK", menu.getPrice()));
		price.setTextFill(fill);
		
		Label size = new Label(menu.getSize());
		size.setTextFill(fill);

		getChildren().addAll(iconBox, new VBox(name, size, price));
	}
}
