package com.lama.dsa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lama.dsa.model.ETAResponse;
import com.lama.dsa.model.IResponseComponent;
import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.Menu;
import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;
import com.lama.dsa.model.order.EnumOrderStatus;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.service.coursier.ICoursierService;
import com.lama.dsa.model.order.OrderContainer;
import com.lama.dsa.service.order.IOrderService;
import com.lama.dsa.utils.DataBaseFiller;
import com.lama.dsa.utils.ETAComputer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/DSA/")
@SuppressWarnings({ "rawtypes", "unchecked"})
@Api(value = "dsa", description = "Resources pertaining to Fresh Food Delivery Information")
public class Controller {

	@Autowired
	private IControllerHelper helper;
	
	/*
	 * ------------- GET-------------
	 */
	
	/** 
	 * TODO REGLER 
	 * Get all the foods available in the database.
	 */
	@RequestMapping(value = "/FOOD", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View the whole food catalog.", response = Food.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food catalogue"),
			@ApiResponse(code = 404, message = "No food was found.") })
	public ResponseEntity getAllFoods() {
		
		List<Food> foods = helper.getFoodService().getAll();
		List<Menu> menus = helper.getMenuService().getAll();
		ETAResponse response = new ETAResponse();
		response.list.addAll(foods);
		response.list.addAll(menus);
		
		return new ResponseEntity(response, (foods == null || foods.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	/**
	 * Get food given a food name.
	 */
	@RequestMapping(value = "FOOD/{name}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View food given a food name.", response = Food.class, responseContainer = "List")	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found.") })
	public ResponseEntity getFoodByName(@PathVariable String name, @RequestParam("address") String address) {
		
		List<Food> foods = helper.getFoodService().getFoodByName(name);
		long restaurantId = foods.get(0).getRestaurantId();
		String restaurantAddress = helper.getRestaurantService().getById(restaurantId).getAddress();
		long eta = ETAComputer.getInstance().compute(restaurantAddress, address);
		ETAResponse response = new ETAResponse(eta);
		response.list.addAll(foods);

		return new ResponseEntity(response, (foods == null || foods.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	/**
	 * Get menu given a menu name.
	 */
	@RequestMapping(value = "MENU/{name}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View menu given a menu name.", response = Menu.class, responseContainer = "List")	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No menu was found.") })
	public ResponseEntity getMenuByName(@PathVariable String name, @RequestParam("address") String address) {
		List<Menu> menus = helper.getMenuService().getMenuByName(name);
		long restaurantId = menus.get(0).getRestaurantId();
		String restaurantAddress = helper.getRestaurantService().getById(restaurantId).getAddress();
		long eta = ETAComputer.getInstance().compute(restaurantAddress, address);
		ETAResponse response = new ETAResponse(eta);
		response.list.addAll(menus);
		return new ResponseEntity(response, (menus == null || menus.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	/**
	 * Get orders of a restaurant (get by name).
	 */
	@RequestMapping(value = "RESTAURANT/{restaurantName}/ORDERS", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View a restaurant orders.", response = Order.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved orders."),
			@ApiResponse(code = 404, message = "No order was found.") })
	public ResponseEntity getOrdersByRestaurantName(@PathVariable String restaurantName) {
		List<Order> orders = helper.getOrderService().getOrdersByRestaurantIds(helper.getRestaurantIdsFromName(restaurantName));
		return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	/**
	 * Get orders of a coursier (get by name).
	 */
	@RequestMapping(value = "COURSIER/ORDERS/{coursierName}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View a coursier orders.", response = Order.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved orders."),
			@ApiResponse(code = 404, message = "No order was found.") })
	public ResponseEntity getOrdersByCoursierName(@PathVariable String coursierName) {
		List<Order> orders = helper.getOrderService().getOrdersByCoursierIds(helper.getCoursierIdsFromName(coursierName));
		return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	
	/*
	 * ------------- POST -------------
	 */
	/**
	 * Make an order given the client name.
	 */
	@RequestMapping(value = "FOOD/{clientName}", method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Order successfully created."),
			@ApiResponse(code = 404, message = "Order creation failed.") })
	public ResponseEntity orderFood(@PathVariable("clientName") String clientName,
			@RequestParam("address") String address,
			@RequestBody(required = false) OrderContainer inputOrderContainer) {
		
		if(!helper.checkRestaurantIdIsUnique(inputOrderContainer)){
			return new ResponseEntity(null, HttpStatus.FORBIDDEN);
		}
		
		Order order = helper.computeFoodOrder(inputOrderContainer, address, clientName);
		ETAResponse response = new ETAResponse(order.getEta());
		response.list.add(order);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	/**
	 *  	(Restaurant workflow)
	 *  	Set an order ready to be delivered.
	 */
	@RequestMapping(value = "RESTAURANT/ORDERS/{order}", method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully prepared food."),
			@ApiResponse(code = 404, message = "Order preparation failed.") })
	public ResponseEntity sendToDeliver(@PathVariable("restaurant") String foodName,
			@PathVariable("order") long orderId){
		IOrderService orderService = helper.getOrderService();
		Order order = orderService.getOrdersById(orderId).get(0);
		
		if(order.getStatus() == EnumOrderStatus.ONGOING){
			order.setStatus(EnumOrderStatus.TODELIVER);
			orderService.updateOrder(order);
		}
		
		ICoursierService coursierService = helper.getCoursierService();
		
		//We assume that there will always be a coursier available for the MVP.
		Coursier firstAvailableCoursier  = coursierService.getByStatus(EnumCoursierStatus.AVAILABLE).get(0);
		firstAvailableCoursier.setStatus(EnumCoursierStatus.DELIVERING);
		coursierService.update(firstAvailableCoursier);
		
		return new ResponseEntity(order = orderService.getOrdersById(orderId).get(0), HttpStatus.OK);
	}

	/**
	 *  	(Coursier worflow)
	 *  	A coursier validates a food delivery.
	 */
	@RequestMapping(value = "COURSIER/{coursierName}/ORDERS/{orderId}", method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity deliverFood(@PathVariable("coursierName") String coursierName,
			@PathVariable("orderId") long orderId){
		IOrderService orderService = helper.getOrderService();
		ICoursierService coursierService = helper.getCoursierService();
		Order order = orderService.getOrdersById(orderId).get(0);
		order.setDeliveryTime(new Date());
		order.setStatus(EnumOrderStatus.DELIVERED);
		orderService.updateOrder(order);

		//We assume that name is also an id
		Coursier coursier = coursierService.getByName(coursierName).get(0);
		coursier.setStatus(EnumCoursierStatus.AVAILABLE);
		return new ResponseEntity(order = orderService.getOrdersById(orderId).get(0), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/UPDATE", method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity updateDataBase() {
		DataBaseFiller.setHelper(helper);
		DataBaseFiller.fillDataBase();
		return new ResponseEntity("Database successfully updated.", HttpStatus.OK);
	}
}
