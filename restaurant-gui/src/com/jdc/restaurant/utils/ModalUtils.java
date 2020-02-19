package com.jdc.restaurant.utils;

import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalUtils {

	public static<T extends ModalController<E>, E> void show(Class<T> type, E data, Consumer<E> listener) {
		
		try {
			Stage stage = new Stage();
			FXMLLoader loader  = new FXMLLoader(type.getResource(String.format("%s.fxml", type.getSimpleName())));
			stage.setScene(new Scene(loader.load()));
			stage.initModality(Modality.APPLICATION_MODAL);
			
			T controller = loader.getController();
			controller.init(data, listener);
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static interface ModalController<E> {
		void init(E data, Consumer<E> listener);
	}
}
