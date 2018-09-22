package com.lama.dsa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lama.dsa.model.IFood;

public interface IFoodRepository extends MongoRepository<IFood, String>{

	public IFood findByIdAndRestaurantIdAndPrice(int id, int restaurantId, float price);

}
