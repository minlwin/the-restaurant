package com.jdc.restaurant.controller.card;

import java.util.function.BiConsumer;

import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.utils.Icons;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class SaleMenu extends StackPane {

	private IntegerProperty count;
	
	private VBox menuBox;
	private VBox saleBox;
	
	public SaleMenu(Menu menu, BiConsumer<Menu, Integer> listener,  ReadOnlyDoubleProperty widthProp) {
		
		count = new SimpleIntegerProperty(1);
		count.addListener((a,b,c) -> {
			if(c.intValue()  == 0) {
				switchView(false);
			}
		});

		menuBox = new VBox(10);
		menuBox.setPadding(new Insets(10));
		Color fill = Color.web(menu.getCategory().getFillColor());
		menuBox.setStyle(String.format("-fx-background-color: %s", menu.getCategory().getBackColor()));
		
		Label menuTitle = new Label(menu.getName());
		menuTitle.getStyleClass().add("title");
		menuTitle.setTextFill(fill);
		
		Label size = new Label(menu.getSize());
		size.setTextFill(fill);
		Label price = new Label(menu.getPrice() + " MMK");
		price.setTextFill(fill);
		
		menuBox.getChildren().addAll(menuTitle, size, price);
		
		menuBox.setOnMouseEntered(event -> switchView(true));
		menuBox.getStyleClass().addAll("dropShadow", "rounded-corner");
		
		
		saleBox = new VBox(10);
		saleBox.setPadding(new Insets(10));
			
		Label saleTitle = new Label(menu.getName());
		saleTitle.getStyleClass().add("title");
		
		HBox countBox = new HBox(10);
		
		SVGPath plus = Icons.PLUS.getSvg();
		VBox plusBox = new VBox(plus);
		plusBox.setOnMouseClicked(event -> count.setValue(count.getValue() + 1));
		
		SVGPath minus = Icons.MINUS.getSvg();
		VBox minusBox = new VBox(minus);
		minusBox.setOnMouseClicked(event -> count.setValue(count.getValue() - 1));
		
		Label countLabel = new Label();
		countLabel.textProperty().bind(count.asString());
		
		countBox.getChildren().addAll(minusBox, countLabel, plusBox);
		
		Label takeOrder = new Label("Take Order");
		HBox actionBox = new HBox();
		
		actionBox.getChildren().add(takeOrder);
		actionBox.setOnMouseClicked(event -> {
			listener.accept(menu, count.get());
			switchView(false);
		});
		
		saleBox.getChildren().addAll(saleTitle, countBox, actionBox);
		prefWidthProperty().bind(widthProp);
		
		getChildren().add(menuBox);
		
		saleBox.setOnMouseExited(event -> {
			if(count.get() == 1) {
				switchView(false);
			}
		});
		saleBox.getStyleClass().addAll("dropShadow", "rounded-corner", "ascent-back");
		
		getStyleClass().add("sale-menu");
	}
	
	private void switchView(boolean menu) {
		
		getChildren().clear();
		getChildren().add(menu ? saleBox : menuBox);
		
		if(menu) {
			count.setValue(1);
		}
	}

}
