package com.jdc.restaurant.controller;

import java.io.IOException;

import com.jdc.restaurant.utils.Page;
import com.jdc.restaurant.utils.PageLoader;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame implements PageLoader{
	
	@FXML
	private StackPane content;
	@FXML
	private Label title;
	@FXML
	private Label loginUserName;
	@FXML
	private VBox sideBar;
	
	private static PageLoader pageLoader;
	
	@FXML
	private void initialize() {
		loadView(Page.Home);
		pageLoader = this;
	}
	

	public static void show() throws IOException {

		Stage stage = new Stage();
		stage.setScene(new Scene(FXMLLoader.load(MainFrame.class.getResource("MainFrame.fxml"))));
		stage.show();
	}
	
	@FXML
	private void loadView(MouseEvent event) {
		Node node = (Node) event.getSource();
		loadView(Page.valueOf(node.getId()));
		sideBar.getChildren().stream().filter(a -> a.getStyleClass().contains("active")).findAny().ifPresent(a -> a.getStyleClass().remove("active"));
		node.getStyleClass().add("active");
	}
	
	public void loadView(Page page) {
		title.setText(page.getTitle());
		try {
			
			Parent view = FXMLLoader.load(getClass().getResource(page.getViewFile()));
			loadView(view);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadView(Parent view) {
		content.getChildren().clear();
		content.getChildren().add(view);
	}

	public static PageLoader getPageLoader() {
		return pageLoader;
	}
}
