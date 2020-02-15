package com.jdc.restaurant.client.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jdc.restaurant.client.utils.DateDeSerializer;
import com.jdc.restaurant.client.utils.DateSerializer;

public class Employee {

	private long id;
	private String name;
	private String role;
	private String email;
	private String phone;
	private String password;
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using =  DateDeSerializer.class)
	private Date creation;
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using =  DateDeSerializer.class)
	private Date modification;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getModification() {
		return modification;
	}

	public void setModification(Date modification) {
		this.modification = modification;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
