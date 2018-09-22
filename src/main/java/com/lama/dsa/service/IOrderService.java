package com.lama.dsa.service;

import java.util.List;

import com.lama.dsa.model.order.IOrder;

public interface IOrderService {

	public List<IOrder> getOrdersByRestaurantName(String restaurantName);
	public List<IOrder> getOrdersByCoursierName(String coursierName);
	
}
