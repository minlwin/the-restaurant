package com.jdc.restaurant.controller;

import java.util.List;

import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.client.dto.Order;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.controller.card.CategoryConstructor;
import com.jdc.restaurant.controller.card.MenuCard;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.model.MenuModel;
import com.jdc.restaurant.model.SaleModel;
import com.jdc.restaurant.utils.CardWidthUtils;
import com.jdc.restaurant.utils.Icons;

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
    	
    	menus.getChildren().clear();
    	List<Menu> list = MenuModel.getModel().search(category, null);
    	
    	DoubleProperty cardWidth = CardWidthUtils.getWidth(menus.widthProperty(), 240.0, 10.0);
    	
    	list.stream().map(m -> new MenuCard(m, null, this::addOrder, cardWidth))
    		.forEach(menus.getChildren()::add);
   	
    }
    
    private void addOrder(Menu menu) {
    	
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
