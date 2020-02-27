package com.jdc.restaurant.controller;

import java.util.List;

import com.jdc.restaurant.RestaurantAppException;
import com.jdc.restaurant.client.dto.Category;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.client.dto.Order;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.client.utils.RestaurantApiException;
import com.jdc.restaurant.controller.card.SaleMenu;
import com.jdc.restaurant.model.CategoryModel;
import com.jdc.restaurant.model.MenuModel;
import com.jdc.restaurant.model.OrderModel;
import com.jdc.restaurant.model.SaleModel;
import com.jdc.restaurant.model.SalesManager;
import com.jdc.restaurant.utils.AutoCompleteUtils;
import com.jdc.restaurant.utils.CardWidthUtils;
import com.jdc.restaurant.utils.DecimalFormatCellValueFactory;
import com.jdc.restaurant.utils.Page;
import com.jdc.restaurant.utils.StringUtils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class SaleDetails {
	
	@FXML
	private ComboBox<String> schType;
	
    @FXML
    private TextField schCategory;

    @FXML
    private TextField schMenu;

    @FXML
    private TilePane menus;

    @FXML
    private Label tableNumber;

    @FXML
    private Label totalTitle;

    @FXML
    private TableView<Order> orders;
    
    @FXML
    private TableColumn<Order, String> colPrice;
    
    @FXML
    private TableColumn<Order, String> colTotal;

    @FXML
    private Label subTotal;

    @FXML
    private Label tax;

    @FXML
    private Label total;
    
    private SalesManager saleManager;
    
    private ObjectProperty<Category> selectedCategory;
    
    @FXML
    private void initialize() {
    	
    	selectedCategory = new SimpleObjectProperty<>();
    	
    	schType.getItems().addAll(CategoryModel.getModel().types());
    	schType.getSelectionModel().select(0);
    	
    	schType.valueProperty().addListener((a,b,c) -> {
    		schCategory.clear();
    		selectedCategory.set(null);
    		searchMenu();
    	});
    	
    	schCategory.textProperty().addListener((a,b,c) -> {
    		if(StringUtils.isEmpty(c)) {
    			searchMenu();
    		}
    	});
    	
    	selectedCategory.addListener((a,b,c) -> searchMenu());
    	schMenu.textProperty().addListener((a,b,c) -> searchMenu());
    	
    	AutoCompleteUtils.attach(schCategory, this::searchCategory, selectedCategory::set);
    	
    	saleManager = new SalesManager(orders.getItems());
    	    	
    	subTotal.textProperty().bind(saleManager.subTotalProperty());
    	tax.textProperty().bind(saleManager.taxProperty());
    	total.textProperty().bind(saleManager.totalProperty());
    	totalTitle.textProperty().bind(saleManager.totalProperty());
    	
    	colPrice.setCellValueFactory(new DecimalFormatCellValueFactory<>(a -> a.getPrice()));
    	colTotal.setCellValueFactory(new DecimalFormatCellValueFactory<>(a -> a.getTotal()));
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

    	loadMenus(MenuModel.getModel().search(schType.getValue(), null, null));
	}
	
    private void searchMenu() {
    	List<Menu> list  = MenuModel.getModel().search(schType.getValue(), selectedCategory.get(), schMenu.getText());
    	loadMenus(list);
    }
    
	private List<Category> searchCategory(String name) {
		return CategoryModel.getModel().search(schType.getValue(), name);
	}
}
