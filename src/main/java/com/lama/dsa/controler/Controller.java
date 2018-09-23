package com.lama.dsa.controler;

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
import com.lama.dsa.model.order.EnumOrderStatus;
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
	@RequestMapping(value = "FOOD/", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View the whole food catalogue", response = Food.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food catalogue"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity getAllFoods() {
		List<Food> foods = foodService.getAll();
		return new ResponseEntity(foods, (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	/**
	 * By name.
	 */
	@RequestMapping(value = "FOOD/{name}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View a certain food", response = Food.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity getFoodByName(@PathVariable String name) {
		List<Food> foods = foodService.getFoodByName(name);
		return new ResponseEntity(foods, (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
//	@RequestMapping(value = "FOOD/{name}", method = RequestMethod.POST, produces = "application/json")
//	@ApiOperation(value = "Order a certain food", response = IFood.class, responseContainer = "")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully ordered food"),
//			@ApiResponse(code = 404, message = "Food order failed") })
//	public ResponseEntity orderFoodByName(@PathVariable String name) {
//		EnumOrderStatus status = orderService.orderFoodByName(name);
//		return new ResponseEntity(foods, (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
//	}
	
	@RequestMapping(value = "RESTAURANT/{restaurantName}/COMMANDS", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View a restaurant orders", response = Order.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved orders"),
			@ApiResponse(code = 404, message = "No order was found") })
	public ResponseEntity getOrdersByRestaurantName(@PathVariable String restaurantName) {
		List<Order> orders = orderService.getOrdersByRestaurantName(restaurantName);
		return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@RequestMapping(value = "COURSIER/{coursierName}/COMMANDS", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View a coursier orders", response = Order.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved orders"),
			@ApiResponse(code = 404, message = "No order was found") })
	public ResponseEntity getOrdersByCoursierName(@PathVariable String coursierName) {
		List<Order> orders = orderService.getOrdersByCoursierName(coursierName);
		return new ResponseEntity(orders, (orders == null || orders.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
}
