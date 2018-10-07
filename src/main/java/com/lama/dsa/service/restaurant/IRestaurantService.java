package com.lama.dsa.service.restaurant;

import java.util.List;

import com.lama.dsa.model.restaurant.Restaurant;

public interface IRestaurantService {
	
	 /**
     * Get a list of restaurants given a restaurant name.
     */
	public List<Restaurant> getByName(String restaurantName);
	
	 /**
     * Get a restaurant given an idea
     */
	public Restaurant getById(long id);
	
	 /**
     * Insert a restaurant in the database
     */
	void insertRestaurant(Restaurant order);

}