package com.lama.dsa.controller;

import java.util.List;
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
import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.Menu;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.model.order.OrderContainer;
import com.lama.dsa.utils.DataBaseFiller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Our Web Service REST Controller.
 * Proposes Uberoo base services entry points.
 */
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
	 * Get all the foods and menus available in the database.
	 */
	@RequestMapping(value = "/CATALOG", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View the whole food catalog.", response = ETAResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food catalogue"),
			@ApiResponse(code = 404, message = "No food was found.") })
	public ResponseEntity getWholeCatalogue() {
		ETAResponse response = helper.getWholeCatalogue();
		return new ResponseEntity(response, (response.hasNoContent()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	/**
	 * Get all the foods (without menus) available in the database. 
	 */
	@RequestMapping(value = "/FOOD", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View the whole food catalog.", response = Food.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food catalogue"),
			@ApiResponse(code = 404, message = "No food was found.") })
	public ResponseEntity getAllFoods() {
		List<Food> foods = helper.getFoodService().getAll();
		return new ResponseEntity(foods, (foods == null || foods.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	/**
	 * Get all the menus available in the database. 
	 */
	@RequestMapping(value = "/MENU", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View the whole menu catalog.", response = Menu.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved menu catalogue"),
			@ApiResponse(code = 404, message = "No menu was found.") })
	public ResponseEntity getAllMenus() {
		List<Menu> menus = helper.getMenuService().getAll();
		return new ResponseEntity(menus, (menus == null || menus.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	/**
	 * Get food given a food name and the delivery address in order to compute the ETA.
	 */
	@RequestMapping(value = "FOOD/{name}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View food given a food name.", response = Food.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found.") })
	public ResponseEntity getFoodByName(@PathVariable String name, @RequestParam("address") String deliveryAddress) {
		ETAResponse response = helper.getEtaFoodByName(name, deliveryAddress);
		
		if(response.hasNoContent()){
			return new ResponseEntity(response.getContent(), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity(response, HttpStatus.OK);
	}

	/**
	 * Get food menu given a menu name and the delivery address in order to compute the ETA.
	 */
	@RequestMapping(value = "MENU/{name}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View food menu given a menu name.", response = Menu.class, responseContainer = "List")	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No menu was found.") })
	public ResponseEntity getMenuByName(@PathVariable String name, @RequestParam("address") String deliveryAddress) {
		ETAResponse response = helper.getEtaMenuByName(name, deliveryAddress);
		
		if(response.hasNoContent()){
			return new ResponseEntity(response.getContent(), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity(response, HttpStatus.OK);
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
		ETAResponse response = helper.computeFoodOrder(inputOrderContainer, address, clientName);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	
	/**
	 * A restaurant sets an order ready to be delivered.
	 */
	@RequestMapping(value = "RESTAURANT/ORDERS/{order}", method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully prepared food."),
			@ApiResponse(code = 404, message = "Order preparation failed.") })
	public ResponseEntity sendToDeliver(@PathVariable("order") long orderId){
		Order order = helper.setOrderReadyForDelivery(orderId);
		return new ResponseEntity(order, HttpStatus.OK);
	}

	/**
	 * A coursier validates a food delivery.
	 */
	@RequestMapping(value = "COURSIER/{coursierName}/ORDERS/{orderId}", method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity deliverFood(@PathVariable("coursierName") String coursierName,
			@PathVariable("orderId") long orderId){
		Order order = helper.validateOrderDelivery(coursierName, orderId);
		return new ResponseEntity(order, HttpStatus.OK);
	}

	/**
	 * Calls the DataBaseFiller, a script to fill the database.
	 */
	@RequestMapping(value = "/UPDATE", method = RequestMethod.POST)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity updateDataBase() {
		DataBaseFiller.fillDataBase(helper);
		return new ResponseEntity("Database successfully updated.\n", HttpStatus.OK);
	}
}
