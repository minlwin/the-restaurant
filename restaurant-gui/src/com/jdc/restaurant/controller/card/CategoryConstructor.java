package com.jdc.restaurant.controller.card;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.jdc.restaurant.client.dto.Category;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CategoryConstructor {
	
	public static void construct(HBox container, 
			StringProperty searchText, 
			Function<String, List<Category>> searcher,
			Consumer<Category> listener) {
		
		searchText.addListener((a,b, c) -> {
			reload(container, listener, searcher.apply(c));
		});
		
		container.widthProperty().addListener((a,b,c) -> {
			reload(container, listener, searcher.apply(searchText.getValue()));
		});
	}
	
	private static void reload(HBox container, Consumer<Category> listener,  List<Category> data) {
		
		container.getChildren().clear();
		
		double maxWidth = container.getWidth();
		double current = 0;
		
		Iterator<Category> iterator = data.iterator();
		
		while (iterator.hasNext()) {
			
			HBox box = getItem(iterator.next(), listener);
			container.getChildren().add(box);

			current += box.getWidth() + 10.0;
			
			if(current >= maxWidth) {
				break;
			}
		}	
	}
	
	private static HBox getItem(Category c, Consumer<Category> listener) {
		
		HBox box = new HBox(10);
		
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(0, 10, 0, 10));
		
		box.getChildren().add(new Label(c.getName()));
		box.setOnMouseClicked(event -> listener.accept(c));
		
		box.getStyleClass().addAll("primary", "rounded-corner");
		
		return box;
	}
}
