package com.lama.dsa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumOrderStatus;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.model.restaurant.Restaurant;
import com.lama.dsa.service.coursier.ICoursierService;
import com.lama.dsa.service.food.IFoodService;
import com.lama.dsa.service.menu.IMenuService;
import com.lama.dsa.service.order.IOrderService;
import com.lama.dsa.service.restaurant.IRestaurantService;


@Component
public class ControllerHelper implements IControllerHelper{

	@Autowired
	private ICoursierService coursierService;
	
	@Autowired
	private IFoodService foodService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IRestaurantService restaurantService;
	
	@Autowired
	private IMenuService menuService;
	
	@Override
	public List<Long> getCoursierIdsFromName(String coursierName){
		List<Coursier> coursiers = coursierService.getByName(coursierName);
		List<Long> coursiersIds = new ArrayList<>();
		coursiers.forEach(c -> coursiersIds.add(c.getId()));
		return coursiersIds;
	}
	
	@Override
	public List<Long> getRestaurantIdsFromName(String restaurantName){
		List<Restaurant> restaurants = restaurantService.getByName(restaurantName);
		List<Long> restaurantsIds = new ArrayList<>();
		restaurants.forEach(r -> restaurantsIds.add(r.getId()));
		return restaurantsIds;
	}

	// >> TODO <<
	@Override
	public Order computeFoodOrder(String foodName, String address, long clientId) {
		Food orderedFood = foodService.getFoodByName(foodName).get(0);
		List<Long> orderedFoods = new ArrayList<>();
		orderedFoods.add(orderedFood.getId());
		
		Order order = new Order(orderService.getNewOId(), orderedFood.getRestaurantId(), -1,
				address, clientId, new Date(), null, EnumOrderStatus.ONGOING, orderedFoods);
		orderService.insertOrder(order);
		
		return order;
	}

	@Override
	public IFoodService getFoodService() {
		return foodService;
	}

	@Override
	public IOrderService getOrderService() {
		return orderService;
	}

	@Override
	public IMenuService getMenuService() {
		return menuService;
	}

	@Override
	public IRestaurantService getRestaurantService() {
		return restaurantService;
	}

	@Override
	public ICoursierService getCoursierService() {
		return coursierService;
	}
	
}
