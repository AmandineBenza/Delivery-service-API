package com.lama.dsa.repository.restaurant;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lama.dsa.model.restaurant.Restaurant;

/**
 * Database restaurants storing. 
 */
public interface IRestaurantRepository extends MongoRepository<Restaurant, String> {

	/**
	 * Find restaurant given an identifier.
	 */
	public List<Restaurant> findById(long id);
	
	/**
	 * Find restaurant given a restaurant name. 
	 */
	public List<Restaurant> findByName(String restaurantName);
	
}
