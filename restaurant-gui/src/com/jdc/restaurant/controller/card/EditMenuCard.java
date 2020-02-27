package com.jdc.restaurant.controller.card;

import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.utils.MMKFormatter;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EditMenuCard extends StackPane{

	private HBox refView;
	private VBox controlView;
	private Menu menu;
	
	private static Image defaultImage;
	
	static {
		defaultImage = new Image(EditMenuCard.class.getResourceAsStream("menu.png"));
	}
	
	public EditMenuCard(Menu menu, ReadOnlyDoubleProperty width) {
		this.menu = menu;
		prefWidthProperty().bind(width);
		
		initRefView();
		
		initControlView();
		
		switchView(true);
		
		getStyleClass().add("edit-menu-card");
	}
	
	private void switchView(boolean isCont) {
		getChildren().clear();
		
		getChildren().add(isCont ? refView : controlView);
	}

	private void initControlView() {

		refView = new HBox();
		
		ImageView imageView = new ImageView(defaultImage);
		imageView.setFitHeight(120);
		imageView.setPreserveRatio(true);
		
		
		VBox dataBox = new VBox(10);
		HBox.setHgrow(dataBox, Priority.ALWAYS);
		dataBox.getStyleClass().add("content");
		
		// title
		Label name = new Label(menu.getName());
		name.getStyleClass().add("title");
		Label code = new Label(String.format("%s - %s", menu.getCode(), menu.getCategory().getName()));
		
		// size
		// price
		VBox rowBox = new VBox(
				getRow("Size", menu.getSize()),
				getRow("Price", MMKFormatter.format(menu.getPrice()))
		);
		
		rowBox.getStyleClass().add("bottom");

		dataBox.getChildren().addAll(name, code, rowBox);
		refView.getChildren().addAll(imageView, dataBox);
	}
	
	private HBox getRow(String key, String value) {
		Label label = new Label(key);
		label.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(label, Priority.ALWAYS);
		
		return new HBox(label, new Label(value));
	}

	private void initRefView() {
		// TODO Auto-generated method stub
		
	}
}
