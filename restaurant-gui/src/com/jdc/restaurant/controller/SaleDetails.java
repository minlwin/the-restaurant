package com.jdc.restaurant.controller;

import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.client.dto.Order;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.client.utils.RestaurantApiException;
import com.jdc.restaurant.controller.card.CategoryConstructor;
import com.jdc.restaurant.controller.card.SaleMenu;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.model.MenuModel;
import com.jdc.restaurant.model.OrderModel;
import com.jdc.restaurant.model.SaleModel;
import com.jdc.restaurant.model.SalesManager;
import com.jdc.restaurant.utils.CardWidthUtils;
import com.jdc.restaurant.utils.Page;

import javafx.beans.property.DoubleProperty;
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
    
    private SalesManager saleManager;
    
    @FXML
    private void initialize() {
    	
    	saleManager = new SalesManager(orders.getItems());
    	    	
    	subTotal.textProperty().bind(saleManager.subTotalProperty());
    	tax.textProperty().bind(saleManager.taxProperty());
    	total.textProperty().bind(saleManager.totalProperty());
    	totalTitle.textProperty().bind(saleManager.totalProperty());
    }
    

    @FXML
    void paid() {
    	try {
    		
    		SaleModel.getModel().paid(saleManager.getSale());
    		
			SaleDetails.show(saleManager.getSale().getId());
    		
		} catch (RestaurantAppException | RestaurantApiException e) {
			e.printStackTrace();
			MessageDialog.show(e.getMessage());
		}

    }

    @FXML
    void print() {

    }

    @FXML
    void sendOrder() {
    	
    	try {
    		
    		OrderModel.getModel().takeOrder(saleManager.getSale());
    		
			MainFrame.getPageLoader().loadView(Page.SaleManagement);
    		
		} catch (RestaurantAppException | RestaurantApiException e) {
			e.printStackTrace();
			MessageDialog.show(e.getMessage());
		}
    	
    }
    
    private void searchMenu(Category category) {
    	loadMenus(MenuModel.getModel().search(category, null));
    }
    
    private void loadMenus(List<Menu> list) {
    	
    	DoubleProperty cardWidth = CardWidthUtils.getWidth(menus.widthProperty(), 186.0, 10.0);
    	
    	menus.getChildren().clear();

    	list.stream().map(m -> new SaleMenu(m, saleManager::addOrder, cardWidth))
    		.forEach(menus.getChildren()::add);
   	
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
		saleManager.setSale(sale);

		CategoryConstructor.construct(categories, 
    			schCategory.textProperty(), 
    			CategoryModel.getModel()::search, 
    			this::searchMenu);
    	
    	loadMenus(MenuModel.getModel().search(null, null));
	}
}
