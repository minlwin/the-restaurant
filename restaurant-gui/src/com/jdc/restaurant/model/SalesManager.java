package com.jdc.restaurant.model;

import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.client.dto.Order;
import com.jdc.restaurant.client.dto.Sale;
import com.jdc.restaurant.utils.MMKBinding;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class SalesManager {

	private Sale sale;
	
	private IntegerProperty subTotal = new SimpleIntegerProperty(0);
	private StringProperty subTotalMMK = new SimpleStringProperty("");
	
	private IntegerProperty tax = new SimpleIntegerProperty(0);
	private StringProperty taxMMK = new SimpleStringProperty("");

	private IntegerProperty total = new SimpleIntegerProperty(0);
	private StringProperty totalMMK = new SimpleStringProperty("");
	
	private ObservableList<Order> orders;

	public SalesManager(ObservableList<Order> orders) {
		
		this.orders = orders;
		
		subTotalMMK.bind(new MMKBinding(subTotal));
		taxMMK.bind(new MMKBinding(tax));
		totalMMK.bind(new MMKBinding(total));
		
		total.bind(subTotal.add(tax));
	}
	
	public Sale getSale() {
		return sale;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
		orders.addAll(sale.getValidOrders());
		subTotal.set(sale.getSubTotal());
		tax.set(sale.getTax());
	}
	
	public void addOrder(Menu menu, int count) {
		sale.addOrder(menu, count);
		orders.clear();
		orders.addAll(sale.getValidOrders());
		
		subTotal.set(sale.getSubTotal());
		tax.set(sale.getTax());
	}
	
	public ObservableList<Order> ordersProperty() {
		return orders;
	}
	
	public StringProperty totalProperty() {
		return totalMMK;
	}
	
	public StringProperty taxProperty() {
		return taxMMK;
	}
	
	public StringProperty subTotalProperty() {
		return subTotalMMK;
	}
}
