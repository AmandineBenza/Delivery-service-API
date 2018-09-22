package com.lama.dsa.model.food;

public class Food implements IFood {

	private int id;
	private int restaurantId;
	private float price;
	private String name;
	private String description;
	
	public Food(int id, int restaurantId, float price, String name, String description) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.price = price;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
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
