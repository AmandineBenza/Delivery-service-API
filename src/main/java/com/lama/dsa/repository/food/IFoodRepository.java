package com.lama.dsa.repository.food;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.food.Food;

/**
 * Database food storing. 
 */
public interface IFoodRepository extends MongoRepository<Food, String> {

	/**
	 * Find food given a name.
	 */
	public List<Food> findByName(String name);
	
}
