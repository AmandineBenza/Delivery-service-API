package com.lama.dsa.service.restaurant;

import java.util.List;

import com.lama.dsa.model.restaurant.Restaurant;

public interface IRestaurantService {

	public List<Restaurant> getByName(String restaurantName);
	
	public Restaurant getById(long id);

	void insertRestaurant(Restaurant order);

}