package com.jdc.restaurant.controller.card;

import java.util.function.BiConsumer;

import com.jdc.restaurant.client.dto.Menu;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EditMenuCard extends StackPane{

	private HBox refView;
	private VBox controlView;
	private BiConsumer<Action, Menu> listener;
	
	public enum Action {
		Upload, Edit
	}
	
	public EditMenuCard(Menu menu, ReadOnlyDoubleProperty width, BiConsumer<Action, Menu> listener) {

		prefWidthProperty().bind(width);
		
		this.listener = listener;
		
		refView = new MenuCard(menu);
		controlView = getControlView(menu);
		
		refView.setOnMouseEntered(e -> switchView(false));
		controlView.setOnMouseExited(e -> switchView(true));
		
		switchView(true);

	}
	
	private VBox getControlView(Menu menu) {
		
		VBox box = new VBox();
		box.getStyleClass().add("control-view");
		
		Label title = new Label(menu.getName());
		title.getStyleClass().add("title");
		
		Label code = new Label(String.format("%s - %s", menu.getCode(), menu.getCategory().getName()));
		
		HBox buttons = new HBox(10);
		HBox spacer = new HBox();
		VBox.setVgrow(spacer, Priority.ALWAYS);
		
		Button image = new Button("Image Upload");
		image.setOnAction(event -> listener.accept(Action.Upload, menu));
		
		Button edit = new Button("Edit");
		edit.setOnAction(event -> listener.accept(Action.Edit, menu));
		
		buttons.getChildren().addAll(image, edit);
		
		box.getChildren().addAll(title, code, spacer, buttons);
		
		return box;
	}

	private void switchView(boolean isCont) {
		getChildren().clear();
		getChildren().add(isCont ? refView : controlView);
	}

}
