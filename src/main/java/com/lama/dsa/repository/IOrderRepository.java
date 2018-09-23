package com.lama.dsa.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.order.Order;

public interface IOrderRepository extends MongoRepository<Order, String>{

	public List<Order> findByRestaurantName(String restaurantName);
	public List<Order> findByCoursierName(String coursierName);
	
}
