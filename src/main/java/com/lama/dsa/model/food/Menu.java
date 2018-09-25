package com.lama.dsa.model.food;

import java.util.List;

public class Menu {
		
	private long id;
	private long restaurantId;
	private float price; 
	private String name; 
	private List<Long> foodIds;
	
	public Menu(long id, long restaurantId, float price, String name, List<Long> foodIds) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.price = price;
		this.name = name;
		this.foodIds = foodIds;
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

	public List<Long> getFoodIds() {
		return foodIds;
	}

	public void setFoodIds(List<Long> foodIds) {
		this.foodIds = foodIds;
	}
	
	
}
