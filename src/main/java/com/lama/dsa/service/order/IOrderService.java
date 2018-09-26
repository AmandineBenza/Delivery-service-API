
package com.lama.dsa.service.order;

import java.util.List;

import com.lama.dsa.model.order.Order;

public interface IOrderService {

	public List<Order> getOrdersById(long id);
	public List<Order> getOrdersByCoursierId(long coursierId);
	public List<Order> getOrdersByRestaurantId(long restaurantId);
	public List<Order> getOrdersByRestaurantIds(List<Long> restaurantsIds);
	public List<Order> getOrdersByCoursierIds(List<Long> coursiersIds);
	public long getNewOId();
	public void insertOrder(Order order);
	public void updateOrder(Order order);
	
}

