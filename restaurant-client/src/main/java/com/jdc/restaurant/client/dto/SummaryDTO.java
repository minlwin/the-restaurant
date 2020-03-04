package com.jdc.restaurant.client.dto;

public class SummaryDTO {

	private int availableSeats;
	private int activeVouchers;
	private int saleToday;
	private int pendingOrders;

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getActiveVouchers() {
		return activeVouchers;
	}

	public void setActiveVouchers(int activeVouchers) {
		this.activeVouchers = activeVouchers;
	}

	public int getSaleToday() {
		return saleToday;
	}

	public void setSaleToday(int saleToday) {
		this.saleToday = saleToday;
	}

	public int getPendingOrders() {
		return pendingOrders;
	}

	public void setPendingOrders(int pendingOrders) {
		this.pendingOrders = pendingOrders;
	}

}
