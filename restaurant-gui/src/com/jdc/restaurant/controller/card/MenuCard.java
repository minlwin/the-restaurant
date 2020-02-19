package com.jdc.restaurant.controller.card;

import java.util.function.Consumer;

import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.controller.MenuEdit;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuCard extends HBox {

	public MenuCard(Menu menu, Icons icon,Consumer<Menu> listener) {
		
		getStyleClass().add("card");
		
		// icon box
		VBox iconBox = new VBox(icon.getSvg());
		iconBox.setOnMouseClicked(event -> {
			ModalUtils.show(MenuEdit.class, menu, listener);
		});
		
		Label name = new Label(menu.getName());
		name.getStyleClass().add("title");
		Label price = new Label(String.format("%s : %d MMK", menu.getSize(), menu.getPrice()));
		
		getChildren().addAll(iconBox, new VBox(name, price));
	}
}
