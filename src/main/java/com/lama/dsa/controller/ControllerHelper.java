package com.lama.dsa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lama.dsa.model.ETAResponse;
import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.Menu;
import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;
import com.lama.dsa.model.order.EnumOrderStatus;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.model.order.OrderContainer;
import com.lama.dsa.model.restaurant.Restaurant;
import com.lama.dsa.service.client.IClientService;
import com.lama.dsa.service.coursier.ICoursierService;
import com.lama.dsa.service.food.IFoodService;
import com.lama.dsa.service.menu.IMenuService;
import com.lama.dsa.service.order.IOrderService;
import com.lama.dsa.service.restaurant.IRestaurantService;
import com.lama.dsa.utils.ETACalculator;


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

	@Autowired
	private IClientService clientService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ETAResponse getWholeCatalogue() {
		List<Food> foods = foodService.getAll();
		List<Menu> menus = menuService.getAll();
		ETAResponse response = new ETAResponse();
		response.add(foods);
		response.add(menus);
		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ETAResponse getEtaFoodByName(String foodName, String deliveryAddress) {
		List<Food> foods = foodService.getFoodByName(foodName);
		ETAResponse response = new ETAResponse();

		if(!foods.isEmpty() && foods != null){
			long restaurantId = foods.get(0).getRestaurantId();
			String restaurantAddress = restaurantService.getById(restaurantId).getAddress();
			long eta = ETACalculator.getInstance().compute(restaurantAddress, deliveryAddress);

			response.setETA(eta);
			response.add(foods);
		}

		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ETAResponse getEtaMenuByName(String menuName, String deliveryAddress) {
		List<Menu> menus = menuService.getMenuByName(menuName);
		ETAResponse response = new ETAResponse();

		if(!menus.isEmpty() && menus != null){
			long restaurantId = menus.get(0).getRestaurantId();
			String restaurantAddress = restaurantService.getById(restaurantId).getAddress();
			long eta = ETACalculator.getInstance().compute(restaurantAddress, deliveryAddress);

			response.setETA(eta);
			response.add(menus);
		}

		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> getCoursierIdsFromName(String coursierName){
		List<Coursier> coursiers = coursierService.getByName(coursierName);
		List<Long> coursiersIds = new ArrayList<>();
		coursiers.forEach(c -> coursiersIds.add(c.getId()));
		return coursiersIds;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> getRestaurantIdsFromName(String restaurantName){
		List<Restaurant> restaurants = restaurantService.getByName(restaurantName);
		List<Long> restaurantsIds = new ArrayList<>();
		restaurants.forEach(r -> restaurantsIds.add(r.getId()));
		return restaurantsIds;
	}

	/**
	 * {@inheritDoc}
	 * Assumes there is only one restaurant for all menus and foods.
	 */
	@Override
	public ETAResponse computeFoodOrder(OrderContainer inputOrderContainer, String address, String clientName) {
		// We need to get the departure address in order to compute the ET	
		// The departure address is the restaurant address
		long restaurantId = inputOrderContainer.getFoods().get(0).getRestaurantId();
		String restaurantAddress = restaurantService.getById(restaurantId).getAddress();

		// Calculation of the Estimated Time of Arrival
		long eta = ETACalculator.getInstance().compute(restaurantAddress, address);
		// Get the client identifier to put in the order producted
		long clientId = clientService.getClientByName(clientName).get(0).getId();

		// Get an available coursier.
		// We assume that there will always be a coursier available for the MVP.
		Coursier firstAvailableCoursier  = coursierService.getByStatus(EnumCoursierStatus.AVAILABLE).get(0);
		firstAvailableCoursier.setStatus(EnumCoursierStatus.DELIVERING);
		coursierService.update(firstAvailableCoursier);

		Order order = new Order(orderService.getNewOId(),
				restaurantId, firstAvailableCoursier.getId(), address, clientId,
				new Date(), null, EnumOrderStatus.ONGOING,
				inputOrderContainer.getFoods().stream().map(Food::getId).collect(Collectors.toList()),
				inputOrderContainer.getMenus().stream().map(Menu::getId).collect(Collectors.toList()),
				eta);

		ETAResponse response = new ETAResponse(order.getEta());
		response.add(order);

		this.orderService.insertOrder(order);
		return response;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Order setOrderReadyForDelivery(long orderId) {
		Order order = orderService.getOrdersById(orderId).get(0);

		if(order.getStatus() == EnumOrderStatus.ONGOING){
			order.setStatus(EnumOrderStatus.TODELIVER);
			orderService.updateOrder(order);

			//We assume that there will always be a coursier available for the MVP.
			Coursier firstAvailableCoursier  = coursierService.getByStatus(EnumCoursierStatus.AVAILABLE).get(0);
			firstAvailableCoursier.setStatus(EnumCoursierStatus.DELIVERING);
			coursierService.update(firstAvailableCoursier);
			// order = orderService.getOrdersById(orderId).get(0);
		}
		
		return order;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Order validateOrderDelivery(String coursierName, long orderId) {
		Order order = orderService.getOrdersById(orderId).get(0);
		order.setDeliveryTime(new Date());
		order.setStatus(EnumOrderStatus.DELIVERED);
		orderService.updateOrder(order);

		// We assume that name is also an id
		Coursier coursier = coursierService.getByName(coursierName).get(0);
		coursier.setStatus(EnumCoursierStatus.AVAILABLE);
		// order = orderService.getOrdersById(orderId).get(0);
		coursierService.update(coursier);
		return order;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFoodService getFoodService() {
		return foodService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IOrderService getOrderService() {
		return orderService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMenuService getMenuService() {
		return menuService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IRestaurantService getRestaurantService() {
		return restaurantService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ICoursierService getCoursierService() {
		return coursierService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IClientService getClientService() {
		return clientService;
	}

}