package com.lama.dsa.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.order.IOrder;

public interface IOrderRepository extends MongoRepository<IOrder, String>{

	public List<IOrder> findByRestaurantName(String restaurantName);
	public List<IOrder> findByCoursierName(String coursierName);
	
}
