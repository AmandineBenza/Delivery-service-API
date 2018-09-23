
package com.lama.dsa.service;

import java.util.List;

import com.lama.dsa.model.order.Order;

public interface IOrderService {

	public List<Order> getOrdersByRestaurantId(int restaurantId);
	public List<Order> getOrdersByCoursierId(int coursierId);
	public List<Order> getOrdersByRestaurantIds(List<Integer> restaurantsIds);
	public List<Order> getOrdersByCoursierIds(List<Integer> coursiersIds);
	
}

