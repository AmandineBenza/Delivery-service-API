
package com.lama.dsa.service.order;

import java.util.ArrayList;
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

	public OrderService() {

	}

	@Override
	public long getNewOId() {
		return orderRepository.count() + 1;
	}

	@Override
	public void insertOrder(Order order) {
		orderRepository.insert(order);
	}

	@Override
	public List<Order> getOrdersById(long id) {
		return orderRepository.findById(id);
	}

	@Override
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public List<Order> getOrdersByRestaurantId(long restaurantId) {
		return orderRepository.findByRestaurantId(restaurantId);
	}

	@Override
	public List<Order> getOrdersByCoursierId(long coursierId) {
		return orderRepository.findByCoursierId(coursierId);
	}

	/**
	 * TODO ?
	 * Get orders of restaurants inquired.
	 */
	@Override
	public List<Order> getOrdersByRestaurantIds(List<Long> restaurantsIds) {
		List<Order> orders = new ArrayList<>();
		
		for(long restaurantId : restaurantsIds){
			for(Order order : orderRepository.findAll()){
				if(order.getRestaurantId() == restaurantId){
					orders.add(order);
				}
			}
		}
		
		return orders;
	}

	/**
	 * TODO ?
	 * Get orders of coursiers inquired.
	 */
	@Override
	public List<Order> getOrdersByCoursierIds(List<Long> coursiersIds) {
		List<Order> orders = new ArrayList<>();
		
		for(long coursierId : coursiersIds){
			for(Order order : orderRepository.findAll()){
				if(order.getCoursierId() == coursierId){
					orders.add(order);
				}
			}
		}
		
		return orders;
	}
	
}
