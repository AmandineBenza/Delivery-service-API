package com.lama.dsa.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.IOrder;

public interface IOrderRepository extends MongoRepository<IOrder, String>{

	public List<IOrder> findByRestaurantId(String restaurantId);
	public List<IOrder> findByCoursierId(String id);
	
}
