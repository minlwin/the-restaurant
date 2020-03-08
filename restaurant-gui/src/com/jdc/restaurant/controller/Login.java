package com.jdc.restaurant.controller;

import com.jdc.restaurant.client.LoginClient;
import com.jdc.restaurant.client.dto.Employee;
import com.jdc.restaurant.client.utils.RestaurantApiException;
import com.jdc.restaurant.utils.ApplicationContext;

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
    
    private LoginClient client;
    
    @FXML
    private void initialize() {
    	client = new LoginClient();
    }

    @FXML
    private void close() {
    	loginId.getScene().getWindow().hide();
    }

    @FXML
    private void login() {
    	
    	try {
    		
    		Employee loginUser = client.login(loginId.getText(), password.getText());
    		
    		ApplicationContext.setLoginUser(loginUser);
    		
    		MainFrame.show();
    		
    		close();
			
		} catch (RestaurantApiException e) {
    		message.setText(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
