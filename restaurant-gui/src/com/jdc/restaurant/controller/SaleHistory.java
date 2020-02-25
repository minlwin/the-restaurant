package com.jdc.restaurant.controller;

import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.model.SaleModel.Status;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SaleHistory {

    @FXML
    private TextField tableNumber;

    @FXML
    private ComboBox<Status> status;

    @FXML
    private TableView<Sale> table;

    @FXML
    void search() {

    }

}
