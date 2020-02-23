package com.jdc.restaurant.controller;

import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Order;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.controller.card.CategoryConstructor;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.model.SaleModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class SaleDetails {
	
    @FXML
    private TextField schCategory;

    @FXML
    private HBox categories;

    @FXML
    private TilePane menus;

    @FXML
    private Label tableNumber;

    @FXML
    private Label totalTitle;

    @FXML
    private TableView<Order> orders;

    @FXML
    private Label subTotal;

    @FXML
    private Label tax;

    @FXML
    private Label total;
    
    @FXML
    private void initialize() {
    	
    	CategoryConstructor.construct(categories, 
    			schCategory.textProperty(), 
    			CategoryModel.getModel()::search, 
    			this::searchMenu);
    }
    

    @FXML
    void paid() {

    }

    @FXML
    void print() {

    }

    @FXML
    void save() {

    }
    
    private void searchMenu(Category category) {
    	
    }

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

		Sale sale = SaleModel.getModel().findById(saleId);
		tableNumber.setText(sale.getTable().getTableNumber());
	}
}
