package com.lama.dsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lama.dsa.repository.IOrderRepository;

@Transactional
@Service("CommandService")
public class OrderService implements IOrderService{

	@Autowired
	private IOrderRepository orderRepository;
	
	public OrderService(){
		
	}
}
