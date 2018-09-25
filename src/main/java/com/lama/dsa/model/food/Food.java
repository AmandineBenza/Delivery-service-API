package com.lama.dsa.model.food;

public class Food {

	private long id;
	private long restaurantId;
	private float price;
	private String name;
	private String description;
	
	public Food(long id, long restaurantId, float price, String name, String description) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.price = price;
		this.name = name;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
