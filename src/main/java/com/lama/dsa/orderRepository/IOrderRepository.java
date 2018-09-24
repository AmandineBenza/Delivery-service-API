package com.lama.dsa.orderRepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.order.Order;

public interface IOrderRepository extends MongoRepository<Order, Integer>
{

  public List<Order> findByRestaurantId(int restaurantId);
  
    public List<Order> findByCoursierId(int coursierId);

	public List<Order> findByCoursierId(String coursierId);

	public List<Order> getOrdersById(long id);
	
}
