package com.lama.dsa.controller;

import java.util.List;

import com.lama.dsa.model.order.Order;
import com.lama.dsa.model.order.OrderContainer;
import com.lama.dsa.service.coursier.ICoursierService;
import com.lama.dsa.service.food.IFoodService;
import com.lama.dsa.service.menu.IMenuService;
import com.lama.dsa.service.order.IOrderService;
import com.lama.dsa.service.restaurant.IRestaurantService;


public interface IControllerHelper {
	
	public List<Long> getCoursierIdsFromName(String coursierName);
	
	public List<Long> getRestaurantIdsFromName(String restaurantName);
	
	public Order computeFoodOrder(OrderContainer inputOrderContainer, String address, long clientId);
	
	public boolean checkRestaurantIdIsUnique(OrderContainer inputOrderContainer); 
	
	public IFoodService getFoodService();
	
	public IOrderService getOrderService();
	
	public IMenuService getMenuService();
	
	public IRestaurantService getRestaurantService();
	
	public ICoursierService getCoursierService();
	
}
