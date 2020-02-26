package com.jdc.restaurant.client.dto;

public class CategoryDto {

	private long id;
	private String name;
	private int menus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMenus() {
		return menus;
	}

	public void setMenus(int menus) {
		this.menus = menus;
	}

}
