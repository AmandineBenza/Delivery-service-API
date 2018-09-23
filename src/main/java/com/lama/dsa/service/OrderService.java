
package com.lama.dsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lama.dsa.model.order.Order;
import com.lama.dsa.repository.order.IOrderRepository;

@Transactional
@Service("OrderService")
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	
	public OrderService(){
		
	}

	@Override
	public List<Order> getOrdersByRestaurantId(int restaurantId) {
		return orderRepository.findByRestaurantId(restaurantId);
	}

	@Override
	public List<Order> getOrdersByCoursierId(int coursierId) {
		return orderRepository.findByCoursierId(coursierId);
	}

	// TODO
	@Override
	public List<Order> getOrdersByRestaurantIds(List<Integer> restaurantsIds) {
		return null;
	}

	// TODO
	@Override
	public List<Order> getOrdersByCoursierIds(List<Integer> coursiersIds) {
		// TODO Auto-generated method stub
		return null;
	}

}

