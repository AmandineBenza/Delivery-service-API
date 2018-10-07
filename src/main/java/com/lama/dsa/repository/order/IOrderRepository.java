package com.lama.dsa.repository.order;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lama.dsa.model.order.Order;

/**
 * Database orders storing. 
 */
public interface IOrderRepository extends MongoRepository<Order, Integer> {

	/**
	 * Find orders given a restaurant identifier.
	 * 
	 * @param restaurantId the restaurant identifier
	 * @return a list of orders
	 */
	public List<Order> findByRestaurantId(long restaurantId);
	
	/**
	 * Find coursiers given a coursier identifier.
	 * 
	 * @param coursierId a coursier identifier
	 * @return a list of orders
	 */
	public List<Order> findByCoursierId(long coursierId);

	/**
	 * Find orders given an order id.
	 */
    public List<Order> findById(long id);
}
