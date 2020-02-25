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

	public static void construct(HBox container, StringProperty searchText, Function<String, List<Category>> searcher,
			Consumer<Category> listener) {

		searchText.addListener((a, b, c) -> {
			reload(container, listener, searcher.apply(c));
		});

		container.widthProperty().addListener((a, b, c) -> {
			reload(container, listener, searcher.apply(searchText.getValue()));
		});
	}

	private static void reload(HBox container, Consumer<Category> listener, List<Category> data) {

		container.getChildren().clear();

		double maxWidth = container.getWidth() + 200;
		
		if(maxWidth > 1000) {
			maxWidth += 400;
		}
		double current = 0;

		Iterator<Category> iterator = data.iterator();

		while (iterator.hasNext()) {

			CategoryBox box = new CategoryBox(iterator.next(), listener);
			container.getChildren().add(box);
			box.layout();
			
			current += box.getEstimatedWidth();

			if (current >= maxWidth) {
				break;
			}
		}
	}

	private static class CategoryBox extends HBox {
		
		private Label name;

		CategoryBox(Category c, Consumer<Category> listener) {

			setAlignment(Pos.CENTER);
			setPadding(new Insets(0, 10, 0, 10));
			
			name = new Label(c.getName());
			getChildren().add(name);

			setOnMouseClicked(event -> listener.accept(c));

			getStyleClass().addAll("primary", "rounded-corner");
			
			applyCss();
			
		}
		
		public double getEstimatedWidth() {
			name.applyCss();
			return prefWidth(-1) + name.prefWidth(-1);
		}
	
	}
}
