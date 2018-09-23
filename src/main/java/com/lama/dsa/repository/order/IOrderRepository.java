package com.lama.dsa.repository.order;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lama.dsa.model.order.Order;

public interface IOrderRepository extends MongoRepository<Order, Integer> {

	public List<Order> findByRestaurantId(int restaurantId);
	public List<Order> findByCoursierId(int coursierId);

}