package com.lama.dsa.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.food.IFood;

public interface IFoodRepository extends MongoRepository<IFood, String>{

	public List<IFood> findByName(String name);
	
}
