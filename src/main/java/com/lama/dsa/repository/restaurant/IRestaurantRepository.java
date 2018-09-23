package com.lama.dsa.repository.restaurant;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lama.dsa.model.restaurant.Restaurant;

public interface IRestaurantRepository extends MongoRepository<Restaurant, String> {

	public List<Restaurant> findByName(String restaurantName);
	
}
