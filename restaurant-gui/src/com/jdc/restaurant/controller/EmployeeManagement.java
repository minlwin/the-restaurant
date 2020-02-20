package com.jdc.restaurant.controller;

import java.util.List;

import com.jdc.restaurant.client.dto.Employee;
import com.jdc.restaurant.model.EmployeeModel;
import com.jdc.restaurant.utils.ModalUtils;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeeManagement {

	@FXML
	private TextField schName;

	@FXML
	private TextField schPhone;

	@FXML
	private TableView<Employee> table;

	@FXML
	private void initialize() {
		
		MenuItem edit = new MenuItem("Edit Employee");
		edit.setOnAction(
				event -> ModalUtils.show(EmployeeEdit.class, table.getSelectionModel().getSelectedItem(), this::save));
		
		table.setContextMenu(new ContextMenu(edit));
		
		search();
	}

	@FXML
	private void addNew() {
		ModalUtils.show(EmployeeEdit.class, null, this::save);
	}

	@FXML
	private void search() {

		table.getItems().clear();
		List<Employee> list = EmployeeModel.getModel().search(schName.getText(), schPhone.getText());
		table.getItems().addAll(list);
	}

	private void save(Employee emp) {
		EmployeeModel.getModel().save(emp);
		search();
	}

}
