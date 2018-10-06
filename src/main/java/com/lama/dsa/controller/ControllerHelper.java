package com.lama.dsa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.Menu;
import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumOrderStatus;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.model.order.OrderContainer;
import com.lama.dsa.model.restaurant.Restaurant;
import com.lama.dsa.service.coursier.ICoursierService;
import com.lama.dsa.service.food.IFoodService;
import com.lama.dsa.service.menu.IMenuService;
import com.lama.dsa.service.order.IOrderService;
import com.lama.dsa.service.restaurant.IRestaurantService;
import com.lama.dsa.utils.ETAComputer;


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

	// TODO >> coursierService.getFirstAvailable()
	/**
	 * Assumes there is only one restaurant for all menus and foods.
	 */
	@Override
	public Order computeFoodOrder(OrderContainer inputOrderContainer, String address, long clientId) {
		long restaurantId = inputOrderContainer.getFoods().get(0).getRestaurantId();
		String restaurantAddress = restaurantService.getById(restaurantId).getAddress();
		long eta = ETAComputer.getInstance().compute(restaurantAddress, address);
		
		Order order = new Order(orderService.getNewOId(),
				restaurantId, -1, address, clientId, new Date(), null, EnumOrderStatus.ONGOING,
				inputOrderContainer.getFoods().stream().map(Food::getId).collect(Collectors.toList()),
				inputOrderContainer.getMenus().stream().map(Menu::getId).collect(Collectors.toList()),
				eta);
		return order;
	}
	
	@Override
	public boolean checkRestaurantIdIsUnique(OrderContainer inputOrderContainer) {
		List<Long> restaurantIds = new ArrayList<>(inputOrderContainer.getFoods().size()
													+ inputOrderContainer.getMenus().size());
		inputOrderContainer.getFoods().forEach(food -> restaurantIds.add(food.getRestaurantId()));
		inputOrderContainer.getMenus().forEach(menu -> restaurantIds.add(menu.getRestaurantId()));
		
		long rid = restaurantIds.get(0);
		
		for(int i = 1; i < restaurantIds.size(); ++i){
			if(rid != restaurantIds.get(i))
				return false;
		}
		
		return true;
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
