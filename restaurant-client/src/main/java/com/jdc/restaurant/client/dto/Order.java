package com.jdc.restaurant.client.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jdc.restaurant.client.utils.DateDeSerializer;
import com.jdc.restaurant.client.utils.DateSerializer;
import com.jdc.restaurant.client.utils.DateUtiles;

public class Order {

	private long id;
	@JsonIgnore
	private Sale sale;
	@JsonProperty("product")
	private Menu menu;
	@JsonProperty("unitPrice")
	private int price;
	private int quantity;

	private String status;
	private int remind;
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using =  DateDeSerializer.class)
	private Date orderTime;
	
	public Order() {
		status = "Ordered";
	}
	
	public Order(OrderDto dto) {
		this.id = dto.getId();
		this.sale = dto.getSale();
		this.menu = dto.getMenu();
		this.price = dto.getPrice();
		this.quantity = dto.getQuantity();
		this.status = dto.getStatus();
		this.remind = dto.getRemind();
		this.orderTime = dto.getOrderTime();
	}

	@JsonIgnore
	public String getOrderTimeStr() {
		return null == orderTime ? "" : DateUtiles.getViewDateTime(orderTime);
	}
	
	@JsonIgnore
	public String getTableNumber() {
		return sale.getTable().getTableNumber();
	}
	
	@JsonIgnore
	public String getProductName() {
		return menu.getName();
	}
	
	public int getTotal() {
		return price * quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRemind() {
		return remind;
	}

	public void setRemind(int remind) {
		this.remind = remind;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

}
