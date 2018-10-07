package com.lama.dsa.controller;

import java.util.List;

import com.lama.dsa.model.ETAResponse;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.model.order.OrderContainer;
import com.lama.dsa.service.client.IClientService;
import com.lama.dsa.service.coursier.ICoursierService;
import com.lama.dsa.service.food.IFoodService;
import com.lama.dsa.service.menu.IMenuService;
import com.lama.dsa.service.order.IOrderService;
import com.lama.dsa.service.restaurant.IRestaurantService;


public interface IControllerHelper {
	
	public List<Long> getCoursierIdsFromName(String coursierName);
	
	public List<Long> getRestaurantIdsFromName(String restaurantName);
	
	public ETAResponse getWholeCatalogue();
	
	public ETAResponse getEtaAllMenus();
	
	public ETAResponse getEtaFoodByName(String foodName, String deliveryAddress);
	
	public ETAResponse getEtaMenuByName(String menuName, String deliveryAddress);
	
	public ETAResponse computeFoodOrder(OrderContainer inputOrderContainer, String address, String clientName);
	
	public Order setOrderReadyForDelivery(long orderId);
	
	public Order validateOrderDelivery(String coursierName, long orderId);
	
	public boolean checkRestaurantIdIsUnique(OrderContainer inputOrderContainer); 
	
	public IFoodService getFoodService();
	
	public IOrderService getOrderService();
	
	public IMenuService getMenuService();
	
	public IRestaurantService getRestaurantService();
	
	public ICoursierService getCoursierService();
	
	public IClientService getClientService();
	
}
