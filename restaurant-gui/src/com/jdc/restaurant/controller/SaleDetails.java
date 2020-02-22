package com.jdc.restaurant.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SaleDetails {

	public static void show(long saleId) {
		
		try {
			FXMLLoader loader = new FXMLLoader(SaleDetails.class.getResource("SaleDetails.fxml"));
			Parent view = loader.load();
			
			SaleDetails controller = loader.getController();
			controller.init(saleId);
			
			MainFrame.getPageLoader().loadView(view);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void init(long saleId) {
		// TODO Auto-generated method stub
		
	}
}
