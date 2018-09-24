
package com.lama.dsa.service;

import java.util.List;

import com.lama.dsa.model.order.Order;

public interface IOrderService {

	public List<Order> getOrdersById(long id);
	public List<Order> getOrdersBycoursierId(String coursierId);
	public long getNewOId();
	public void insertOrder(Order order);
	public void updateOrder(Order order);
}

