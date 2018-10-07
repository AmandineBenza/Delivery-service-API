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
	
	/**
	 * Get coursier identifiers given a coursier name.
	 * 
	 * @param coursierName the coursier name
	 * @return a list of identifiers
	 */
	public List<Long> getCoursierIdsFromName(String coursierName);
	
	/**
	 * Get restaurant identifiers given a restaurant name.
	 * 
	 * @param restaurantName the restaurant name
	 * @return a list of identifiers
	 */
	public List<Long> getRestaurantIdsFromName(String restaurantName);
	
	/**
	 * Get the whole API catalog.
	 * 
	 * @return a composition of "unique" foods and menus
	 */
	public ETAResponse getWholeCatalogue();
	
	/**
	 * Get a food given its name and the delivery address wanted.
	 * 
	 * @param foodName the food name
	 * @param deliveryAddress the user delivery address
	 * @return the food information including the ETA "Estimated Time of Arrival"
	 */
	public ETAResponse getEtaFoodByName(String foodName, String deliveryAddress);
	
	/**
	 * Get a menu given its name and the delivery address wanted.
	 * 
	 * @param menuName the menu name
	 * @param deliveryAddress the user delivery address
	 * @return the menu information including the ETA "Estimated Time of Arrival"
	 */
	public ETAResponse getEtaMenuByName(String menuName, String deliveryAddress);

	/**
	 * Computes a food order.
	 * 
	 * @param inputOrderContainer the object container of the user JSON input
	 * @param deliveryAddress the delivery address
	 * @param clientName the client name
	 * @return the order created including the ETA "Estimated Time of Arrival"
	 */
	public ETAResponse computeFoodOrder(OrderContainer inputOrderContainer, String deliveryAddress, String clientName);
	
	/**
	 * Set an order to be ready to be delivered.
	 * 
	 * @param orderId the order identifier
	 * @return the ready order
	 */
	public Order setOrderReadyForDelivery(long orderId);
	
	/**
	 * Validates the delivery of an order.
	 * 
	 * @param coursierName the coursier name
	 * @param orderId the order identifier
	 * @return the validated order
	 */
	public Order validateOrderDelivery(String coursierName, long orderId);
	
	/**
	 * Checks that a container content only reference to one restaurant.
	 * 
	 * @param inputOrderContainer the object container of the user JSON input
	 */
	public boolean checkRestaurantIdIsUnique(OrderContainer inputOrderContainer); 

	/**
	 * Get reference to the food service.
	 */
	public IFoodService getFoodService();
	
	/**
	 * Get reference to the order service.
	 */
	public IOrderService getOrderService();
	
	/**
	 * Get reference to the menu service.
	 */
	public IMenuService getMenuService();
	
	/**
	 * Get reference to the restaurant service.
	 */
	public IRestaurantService getRestaurantService();
	
	/**
	 * Get reference to the coursier service.
	 */
	public ICoursierService getCoursierService();
	
	/**
	 * Get reference to the client service.
	 */
	public IClientService getClientService();
	
}
