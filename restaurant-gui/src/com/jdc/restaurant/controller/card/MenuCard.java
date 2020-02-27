package com.jdc.restaurant.controller.card;

import java.util.function.Consumer;

import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.controller.MenuEdit;
import com.jdc.restaurant.utils.Icons;
import com.jdc.restaurant.utils.MMKFormatter;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class MenuCard extends HBox {

	public MenuCard(Menu menu, Icons icon,Consumer<Menu> listener, ReadOnlyDoubleProperty widthProp) {
		
		getStyleClass().add("card");
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
		
		Label code = new Label(menu.getCode());
		code.getStyleClass().add("title");
		
		Label name = new Label(menu.getName());
		
		
		HBox sizeBox = new HBox(new Label(menu.getSize()));
		
		sizeBox.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(sizeBox, Priority.ALWAYS);
		
		Label price = new Label(MMKFormatter.format(menu.getPrice()));
		
		HBox row = new HBox(sizeBox, price);
		row.setPadding(new Insets(10, 0, 0, 0));
		
		VBox dataBox = new VBox(code, name, row);
		HBox.setHgrow(dataBox, Priority.ALWAYS);

		getChildren().add(dataBox);
	}
}
