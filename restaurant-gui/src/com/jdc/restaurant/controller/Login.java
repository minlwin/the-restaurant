package com.jdc.restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private TextField loginId;

    @FXML
    private PasswordField password;
    
    @FXML
    private Label message;

    @FXML
    private void close() {
    	loginId.getScene().getWindow().hide();
    }

    @FXML
    private void login() {
    	
    	try {
    		
    		MainFrame.show();
    		
    		close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
