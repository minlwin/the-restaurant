package com.jdc.restaurant.controller.card;

import java.util.function.Consumer;

import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.utils.DateUtiles;
import com.jdc.restaurant.utils.Icons;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class VoucherCard extends HBox {
	
	public VoucherCard(Sale voucher, Consumer<Long> listener) {
		
		getStyleClass().add("card");

		SVGPath icon = Icons.VOUCHER.getSvg();
		
		VBox dataBox = new VBox();
		dataBox.getStyleClass().add("sp-10");
		
		Label title = new Label(voucher.getTable().getTableNumber());
		title.getStyleClass().add("title");
		
		Label date = new Label(DateUtiles.getVuewDateTime(voucher.getDate()));
		Label charges = new Label(String.format("%d MMK", voucher.getSubTotal()));
		
		getChildren().addAll(icon, new VBox(title,  date, charges));
		
		setOnMouseClicked(event -> listener.accept(voucher.getId()));
	}

}
