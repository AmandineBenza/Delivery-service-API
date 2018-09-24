
package com.lama.dsa.service.order;

import java.util.List;

import com.lama.dsa.model.order.Order;

public interface IOrderService {

	public List<Order> getOrdersById(long id);
	public List<Order> getOrdersBycoursierId(String coursierId);
	public List<Order> getOrdersBycoursierId(int coursierId);
	public List<Order> getOrdersByRestaurantId(int restaurantId);
	public List<Order> getOrdersByCoursierId(int coursierId);
	public List<Order> getOrdersByRestaurantIds(List<Integer> restaurantsIds);
	public List<Order> getOrdersByCoursierIds(List<Integer> coursiersIds);
	public long getNewOId();
	public void insertOrder(Order order);
	public void updateOrder(Order order);
}

