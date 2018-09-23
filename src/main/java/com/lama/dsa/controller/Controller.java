package com.lama.dsa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.IFood;
import com.lama.dsa.model.order.IOrder;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.service.IFoodService;
import com.lama.dsa.service.IOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/DSA/")
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
@Api(value = "dsa", description = "Resources pertaining to Fresh Food Delivery Information")
public class Controller {

	@Autowired
	private IFoodService foodService;

	@Autowired
	private IOrderService orderService;

	/**
	 * Get all the foods available in the catalogue.
	 */
	@RequestMapping(value = "/FOOD", method = RequestMethod.GET, produces = "application/xml")
	@ApiOperation(value = "View the whole food catalogue", response = IFood.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food catalogue"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity getAllFoods() {
		List<Food> foods = foodService.getAll();
		return new ResponseEntity(foods,
				/*
				 * (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND :
				 */ HttpStatus.OK);
	}

	/**
	 * By name.
	 */
	@RequestMapping(value = "FOOD/{name}", method = RequestMethod.GET, produces = "application/xml")
	@ApiOperation(value = "View the whole food catalogue", response = IFood.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity getFoodByName(@PathVariable String name) {
		List<Food> foods = foodService.getFoodByName(name);
		return new ResponseEntity(foods,
				/*
				 * (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND :
				 */ HttpStatus.OK);
	}

	@RequestMapping(value = "FOOD/{name}/{price}/{id},{restaurantId}/{description}", method = RequestMethod.POST, produces = "application/xml")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity addFood(@PathVariable String name,@PathVariable String description,@PathVariable int id,@PathVariable int restaurantId,@PathVariable float price) {
		Food food = new Food(id , restaurantId, price, name,  description);
		foodService.insertFood(food);
		return new ResponseEntity(food,
				 HttpStatus.OK);
	}

	// @RequestMapping(value = "RESTAURANT/{restaurantName}/COMMANDS", method =
	// RequestMethod.GET, produces = "application/xml")
	// @ApiOperation(value = "View a restaurant orders", response =
	// IOrder.class, responseContainer = "List")
	// @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully
	// retrieved orders"),
	// @ApiResponse(code = 404, message = "No order was found") })
	// public ResponseEntity getOrdersByRestaurantName(@PathVariable String
	// restaurantName) {
	// List<Order> orders =
	// orderService.getOrdersByRestaurantName(restaurantName);
	// return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ?
	// HttpStatus.NOT_FOUND : HttpStatus.OK);
	// }

	@RequestMapping(value = "COURSIER/{coursierName}/COMMANDS", method = RequestMethod.GET, produces = "application/xml")
	@ApiOperation(value = "View a coursier orders", response = IOrder.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved orders"),
			@ApiResponse(code = 404, message = "No order was found") })
	public ResponseEntity getOrdersByCoursierName(@PathVariable String coursierName) {

		List<Order> orders = orderService.getOrdersBycoursierId(coursierName);
		return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
}
