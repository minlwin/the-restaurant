package com.jdc.restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RestaurantApplication extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("controller/Login.fxml"))));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
