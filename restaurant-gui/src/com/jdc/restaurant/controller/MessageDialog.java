package com.jdc.restaurant.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MessageDialog {

	@FXML
	private Label message;
	
	@FXML
	private void close() {
		message.getScene().getWindow().hide();
	}

	public static void show(String message) {
		try {
			FXMLLoader loader = new FXMLLoader(MessageDialog.class.getResource("MessageDialog.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));
			MessageDialog dialog = loader.getController();
			
			dialog.message.setText(message);
			
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
