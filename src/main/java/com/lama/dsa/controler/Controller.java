package com.lama.dsa.controler;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lama.dsa.model.IMenu;
import com.lama.dsa.model.Menu;
import com.lama.dsa.service.IDeliveryService;

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
	private IDeliveryService deliveryService;
	
	@RequestMapping(value = "GET/{restaurant}/MEALS/MENU", method = RequestMethod.GET, produces = "application/xml")
	@ApiOperation(value = "View a restaurant list of menus", response = IMenu.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved restaurant menus"),
			@ApiResponse(code = 404, message = "No menu were found") })
	public ResponseEntity getMenus(@PathVariable() String restaurant) {
		ImmutableList<IMenu> menus = Lists.immutable.of(new Menu());
		// ramenService...
		return new ResponseEntity(menus, (menus == null || menus.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
}
