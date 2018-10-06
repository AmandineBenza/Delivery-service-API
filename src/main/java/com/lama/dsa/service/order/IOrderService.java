
package com.lama.dsa.service.order;

import java.util.List;

import com.lama.dsa.model.order.Order;

public interface IOrderService {

	 /**
     * Get a order given a name.
     */
	public List<Order> getOrdersById(long id);
	
	 /**
     * Get a order given a coursier id.
     */
	public List<Order> getOrdersByCoursierId(long coursierId);
	
	 /**
     * Get a order given a restaurant id.
     */
	public List<Order> getOrdersByRestaurantId(long restaurantId);
	
	 /**
     * Get a order given a list of restaurants ids.
     */
	public List<Order> getOrdersByRestaurantIds(List<Long> restaurantsIds);
	
	 /**
     * Get a order given a list of coursiers ids.
     */
	public List<Order> getOrdersByCoursierIds(List<Long> coursiersIds);
	
	 /**
     * Get an idea that didn't exist in the database of the new order
     */
	public long getNewOId();
	
	 /**
     * Insert order in the database.
     */
	public void insertOrder(Order order);
	
	 /**
     * Update an order in the database.
     */
	public void updateOrder(Order order);
	
}

