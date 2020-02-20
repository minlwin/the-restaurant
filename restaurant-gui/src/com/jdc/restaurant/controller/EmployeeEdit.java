package com.jdc.restaurant.controller;

import java.util.function.Consumer;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Employee;
import com.jdc.restaurant.model.EmployeeModel.Role;
import com.jdc.restaurant.utils.ModalUtils.ModalController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EmployeeEdit implements ModalController<Employee>{

    @FXML
    private Label title;

    @FXML
    private Label message;

    @FXML
    private TextField name;

    @FXML
    private ComboBox<Role> role;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;
    
    private Employee data;
    private Consumer<Employee> listener;
    
    @FXML
    private void initialize() {
    	role.getItems().addAll(Role.values());
    }

    @FXML
    void close() {
    	title.getScene().getWindow().hide();
    }

    @FXML
    void save() {
    	
    	try {
    		
    		data.setName(name.getText());

    		if(null != role.getValue()) {
        		data.setRole(role.getValue().name());
    		}
    		
    		data.setPassword(email.getText());
    		data.setPhone(phone.getText());
    		data.setEmail(email.getText());
    		
    		listener.accept(data);
    		
    		close();
			
		} catch (RestaurantAppException e) {
			message.setText(e.getMessage());
		}
    }

	@Override
	public void init(Employee data, Consumer<Employee> listener) {
		this.data = data;
		this.listener = listener;
		
		if(null == data) {
			this.data = new Employee();
			title.setText("Add New Employee");
		} else {
			title.setText("Edit Employee");
			name.setText(data.getName());
			role.setValue(Role.valueOf(data.getRole()));
			phone.setText(data.getPhone());
			email.setText(data.getEmail());
		}
	}

}
