
package com.lama.dsa.foodRepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.food.Food;

public interface IFoodRepository extends MongoRepository<Food, String>{

	public List<Food> findByName(String name);
	
}
