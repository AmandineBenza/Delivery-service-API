package com.lama.dsa.service;

import java.util.List;

import com.lama.dsa.model.order.Order;

public interface IOrderService {

	public List<Order> getOrdersByRestaurantName(String restaurantName);
	public List<Order> getOrdersByCoursierName(String coursierName);
	
}
