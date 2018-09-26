package com.lama.dsa.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.order.EnumOrderStatus;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.service.order.IOrderService;

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
	 * Get all the foods available in the database.
	 */
	@RequestMapping(value = "/FOOD", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View the whole food catalog.", response = Food.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food catalogu"),
			@ApiResponse(code = 404, message = "No food was found.") })
	public ResponseEntity getAllFoods() {
		List<Food> foods = helper.getFoodService().getAll();
		return new ResponseEntity(foods, (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	/**
	 * Get food by given food name.
	 */
	@RequestMapping(value = "FOOD/{name}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View food given a food name.", response = Food.class, responseContainer = "List")	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found.") })
	public ResponseEntity getFoodByName(@PathVariable String name) {
		List<Food> foods = helper.getFoodService().getFoodByName(name);
		helper.computeFoodOrder(name, "", 0L);
		return new ResponseEntity(foods, (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	/**
	 * Get commands of a restaurant (get by name).
	 */
	@RequestMapping(value = "RESTAURANT/{restaurantName}/ORDERS", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View a restaurant orders.", response = Order.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved orders."),
			@ApiResponse(code = 404, message = "No order was found") })
	public ResponseEntity getOrdersByRestaurantName(@PathVariable String restaurantName) {
		List<Order> orders = helper.getOrderService().getOrdersByRestaurantIds(helper.getRestaurantIdsFromName(restaurantName));
		return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	/**
	 * Get commands of a coursier (get by name).
	 */
	@RequestMapping(value = "COURSIER/{coursierName}/ORDERS", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View a coursier orders.", response = Order.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved orders."),
			@ApiResponse(code = 404, message = "No order was found") })
	public ResponseEntity getOrdersByCoursierName(@PathVariable String coursierName) {
		List<Order> orders = helper.getOrderService().getOrdersByCoursierIds(helper.getCoursierIdsFromName(coursierName));
		return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	
	/*
	 * ------------- POST -------------
	 */

	/**
	 * TODO
	 * 		(Client workflow)
	 * 		Order a food given a food name, the client id, the delivery address.
	 */
	@RequestMapping(value = "FOOD/{name}", method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully ordered food."),
			@ApiResponse(code = 404, message = "Order failed.") })
	public ResponseEntity orderFood(@PathVariable("name") String foodName,
			@RequestParam("clientid") long clientId, 
			@RequestParam("address") String address) {
		Order order = helper.computeFoodOrder(foodName, address, clientId);
		return new ResponseEntity(order, HttpStatus.OK);
	}

	/**
	 *  TODO
	 *  	(Restaurant workflow)
	 *  	Set an order ready to be delivered.
	 */
	@RequestMapping(value = "RESTAURANT/{restaurant}/ORDERS/{order}", method = RequestMethod.POST)
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
		
		return new ResponseEntity(order = orderService.getOrdersById(orderId).get(0), HttpStatus.OK);
	}

	/**
	 *  	(Coursier worflow)
	 *  	A coursier validates a food delivery.
	 */
	@RequestMapping(value = "COURSIER/{coursierName}/ORDERS/{orderId}", method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity deliverFood(@PathVariable("coursierName") String foodName,
			@PathVariable("orderId") long orderId){
		IOrderService orderService = helper.getOrderService();
		Order order = orderService.getOrdersById(orderId).get(0);
		order.setDeliveryTime(new Date());
		order.setStatus(EnumOrderStatus.DELIVERED);
		orderService.updateOrder(order);
		return new ResponseEntity(order = orderService.getOrdersById(orderId).get(0), HttpStatus.OK);
	}

}
