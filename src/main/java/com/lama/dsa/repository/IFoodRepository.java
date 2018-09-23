package com.lama.dsa.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.food.Food;

public interface IFoodRepository extends MongoRepository<Food, Integer>{

	public List<Food> findByName(String name);
	
}
