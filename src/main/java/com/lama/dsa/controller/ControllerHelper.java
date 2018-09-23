package com.lama.dsa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.restaurant.Restaurant;
import com.lama.dsa.service.ICoursierService;
import com.lama.dsa.service.IFoodService;
import com.lama.dsa.service.IOrderService;
import com.lama.dsa.service.IRestaurantService;


@Component
@SuppressWarnings("unused")
public class ControllerHelper implements IControllerHelper{

	private ICoursierService coursierService;
	private IFoodService foodService;
	private IOrderService orderService;
	private IRestaurantService restaurantService;
	
	public ControllerHelper(ICoursierService coursierService, IFoodService foodService,
			IOrderService orderService, IRestaurantService restaurantService){
		this.coursierService = coursierService;
		this.foodService = foodService;
		this.orderService = orderService;
		this.restaurantService = restaurantService;
	}
	
	@Override
	public List<Integer> getCoursierIdsFromName(String coursierName){
		List<Coursier> coursiers = coursierService.getByName(coursierName);
		List<Integer> coursiersIds = new ArrayList<>();
		coursiers.forEach(c -> coursiersIds.add(c.getId()));
		return coursiersIds;
	}
	
	@Override
	public List<Integer> getRestaurantIdsFromName(String restaurantName){
		List<Restaurant> restaurants = restaurantService.getByName(restaurantName);
		List<Integer> restaurantsIds = new ArrayList<>();
		restaurants.forEach(r -> restaurantsIds.add(r.getId()));
		return restaurantsIds;
	}
	
}
