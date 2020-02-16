package com.jdc.restaurant.client.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jdc.restaurant.client.utils.DateDeSerializer;
import com.jdc.restaurant.client.utils.DateSerializer;

public class Sale {

	private long id;
	@JsonProperty("tables")
	private Table table;
	@JsonProperty("saleDate")
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using =  DateDeSerializer.class)
	private Date date;
	private int subTotal;
	private int tax;
	@JsonProperty("details")
	private List<Order> orders;
	private boolean paid;
	
	public Sale() {
		orders = new ArrayList<>();
	}
	
	public void addOrder(Menu menu) {
		Order od = new Order();
		od.setMenu(menu);
		od.setPrice(menu.getPrice());
		od.setQuantity(1);
		orders.add(od);
		calculate();
	}
		
	public void addOrder(Menu menu, int count) {
		Order od = new Order();
		od.setMenu(menu);
		od.setPrice(menu.getPrice());
		od.setQuantity(count);
		orders.add(od);
		calculate();
	}
	
	private void calculate() {
		this.subTotal = orders.stream().mapToInt(a -> a.getPrice() * a.getQuantity()).sum();
		this.tax = subTotal / 100 * 5;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
