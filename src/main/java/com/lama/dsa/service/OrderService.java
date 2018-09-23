
package com.lama.dsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lama.dsa.model.order.Order;
import com.lama.dsa.orderRepository.IOrderRepository;

@Transactional
@Service("OrderService")
public class OrderService implements IOrderService
{

	@Autowired
	private IOrderRepository orderRepository;
	
	public OrderService(){
		
	}

	

	@Override
	public List<Order> getOrdersBycoursierId(String coursierId) {
		// TODO Auto-generated method stub
		return orderRepository.findByCoursierId(coursierId);
	}

//	@Override
//	public List<Order> getOrdersByCoursierName(String coursierName) {
//		return orderRepository.findByCoursierName(coursierName);
//	}
}

