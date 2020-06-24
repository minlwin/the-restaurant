package com.jdc.restaurant.client.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jdc.restaurant.client.utils.DateDeSerializer;
import com.jdc.restaurant.client.utils.DateSerializer;
import com.jdc.restaurant.client.utils.DateUtiles;

public class OrderDto {

	private long id;
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
	
	public String getOrderTimeStr() {
		return null == orderTime ? "" : DateUtiles.getViewDateTime(orderTime);
	}
	
	public String getTableNumber() {
		return sale.getTable().getTableNumber();
	}
	
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
