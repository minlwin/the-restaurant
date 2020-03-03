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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class SaleMenu extends StackPane {

	private IntegerProperty count;
	
	private Pane menuBox;
	private VBox saleBox;
	
	public SaleMenu(Menu menu, BiConsumer<Menu, Integer> listener,  ReadOnlyDoubleProperty widthProp) {
		
		prefWidthProperty().bind(widthProp);

		count = new SimpleIntegerProperty(1);
		count.addListener((a,b,c) -> {
			if(c.intValue()  == 0) {
				switchView(false);
			}
		});

		menuBox = new MenuCard(menu);
		menuBox.setOnMouseEntered(e -> switchView(true));
		
		getChildren().add(menuBox);
		
		initSaleBox(menu, listener);
	}
	
	private void initSaleBox(Menu menu, BiConsumer<Menu, Integer> listener) {
		
		saleBox = new VBox(10);
		saleBox.setPadding(new Insets(10));
		saleBox.getStyleClass().add("control-view");

		saleBox.setOnMouseExited(event -> {
			if(count.get() == 1) {
				switchView(false);
			}
		});
		
			
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
		
		HBox actionBox = new HBox(new Label("Take Order"));

		actionBox.setOnMouseClicked(event -> {
			listener.accept(menu, count.get());
			switchView(false);
		});
		
		saleBox.getChildren().addAll(saleTitle, countBox, actionBox);
	}
	
	private void switchView(boolean menu) {
		
		getChildren().clear();
		getChildren().add(menu ? saleBox : menuBox);
		
		if(menu) {
			count.setValue(1);
		}
	}

}
