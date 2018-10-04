package com.lama.dsa.model.restaurant;

public class Restaurant {

	private long id;
	private float averagePrice;
	private String name;
	private String address;
	private String description;
	
	public Restaurant(long id, float averagePrice, String name, String address, String description) {
		this.id = id;
		this.averagePrice = averagePrice;
		this.name = name;
		this.address = address;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(float averagePrice) {
		this.averagePrice = averagePrice;
	}
	
}
