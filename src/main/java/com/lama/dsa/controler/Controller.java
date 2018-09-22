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

import com.lama.dsa.model.IFood;
import com.lama.dsa.model.IMenu;
import com.lama.dsa.model.Menu;
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
	
	@RequestMapping(value = "FOOD/", method = RequestMethod.GET, produces = "application/xml")
	@ApiOperation(value = "View the whole food catalogue", response = IFood.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food catalogue"),
			@ApiResponse(code = 404, message = "No food was found") })
	public ResponseEntity getAllFoods() {
		List<IFood> foods = foodService.getAll();
		return new ResponseEntity(foods, (foods == null || foods.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
}
