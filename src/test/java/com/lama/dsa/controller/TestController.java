package com.lama.dsa.controller;

import com.lama.dsa.app.Application;
import com.lama.dsa.model.ETAResponse;
import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.Menu;
import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;
import com.lama.dsa.model.order.EnumOrderStatus;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.model.order.OrderContainer;
import com.lama.dsa.model.restaurant.Restaurant;
import com.lama.dsa.service.coursier.CoursierService;
import com.lama.dsa.service.coursier.ICoursierService;
import com.lama.dsa.service.food.FoodService;
import com.lama.dsa.service.food.IFoodService;
import com.lama.dsa.service.menu.MenuService;
import com.lama.dsa.service.order.IOrderService;
import com.lama.dsa.service.order.OrderService;
import com.lama.dsa.service.restaurant.RestaurantService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;
import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FoodService.class})
@ContextConfiguration(classes = {Application.class})
@AutoConfigureMockMvc
public class TestController {

	@Autowired
	private Controller testC;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	Controller controller;

	@Mock
	ControllerHelper ch;

	@Mock
	FoodService fs;

	@Mock
	RestaurantService rs;

	@Mock
	OrderService os;

	@Mock
	MenuService ms;

	@Mock
	CoursierService cs;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void contexLoads() throws Exception {
		assertThat(testC).isNotNull();
	}

	@Test
	public void testGetAllFoodService() {
		when(ch.getFoodService()).thenReturn(fs);
		when(fs.getAll()).thenReturn(new ArrayList<Food>());
		when(ch.getMenuService()).thenReturn(ms);
		when(ms.getAll()).thenReturn(new ArrayList<Menu>());
		ETAResponse etaResponse = new ETAResponse();
		//assert that when no foods are retrieve a 404 not fond response is sent
		assertEquals(new ResponseEntity<ETAResponse>(etaResponse , HttpStatus.NO_CONTENT), controller.getAllFoods());

		Food food = new Food(0, 0, 2.0f, "TestFood", "Food to test foods");
		List<Food> foods = singletonList(food);
		when(fs.getAll()).thenReturn(foods);
		Menu menu = new Menu(0L, 2L, 14.5f, "Fabulous menu for a fabulous being", singletonList(1L));
		List<Menu> menus = singletonList(menu);
		when(ms.getAll()).thenReturn(menus);
		ETAResponse eta = new ETAResponse();
		eta.list.add(food);
		eta.list.add(menu);
		ResponseEntity<ETAResponse> response = new ResponseEntity<ETAResponse>(eta, HttpStatus.OK);
		//assert that the good Foods are returned 
		assertEquals(response, controller.getAllFoods());

	}

	@Test
	public void testFoodInformation() {
		Food food = new Food(0, 0, 2.0f, "TestFood", "Food to test foods");
		List<Food> foods = singletonList(food);
		ETAResponse eta = new ETAResponse();
		eta.list.addAll(foods);

		when(ch.getFoodService()).thenReturn(fs);
		when(fs.getFoodByName("plat 1")).thenReturn(foods);
		when(ch.getRestaurantService()).thenReturn(rs);
		when(rs.getById(0)).thenReturn( new Restaurant(0L, 25, "Fabulous Restaurant", "Fabulous Adress for a fabulous restaurant", "Fabulous adress for a fabulous restaurant"));

		ResponseEntity<ETAResponse> response = new ResponseEntity<ETAResponse>(eta, HttpStatus.OK);

		assertEquals(response, controller.getFoodByName("plat 1","Address X"));
		assertEquals(new ResponseEntity<List<Food>>(new ArrayList<Food>(),HttpStatus.NO_CONTENT), controller.getFoodByName("plat 2","Address X"));
	}

	@Test
	public void testMenuInformation() {

		Menu menu = new Menu(0L, 2L, 14.5f, "Fabulous menu for a fabulous being", singletonList(1L));
		List<Menu> menus = singletonList(menu);
		ETAResponse eta = new ETAResponse();
		eta.list.addAll(menus);

		ResponseEntity<ETAResponse> response = new ResponseEntity<ETAResponse>(eta, HttpStatus.OK);

		when(ch.getMenuService()).thenReturn(ms);
		when(ms.getMenuByName("Fabulous menu for a fabulous being")).thenReturn(menus);
	}

	@Test
	public void testRestaurantOrders() {

		/*(long id, long restaurantId, long coursierId, String address, long clientId, Date creationTime,
		Date deliveryTime, EnumOrderStatus status, List<Long> foodIds, List<Long> menuIds, long eta)*/	

		Order order1 = new Order(0L,1L,2L,"Adress of a fantastic customer", 0, new Date(), new Date(), 
				EnumOrderStatus.ONGOING, singletonList(1L), singletonList(1L), 42L);
		Order order2 = new Order(1L,1L,3L,"Adress of another fantastic customer", 1, new Date(), new Date(), 
				EnumOrderStatus.TODELIVER, singletonList(2L), singletonList(2L), 24L);
		Restaurant restaurant = new Restaurant(1L, 25, "Fabulous Restaurant", "Fabulous Adress for a fabulous restaurant", "Fabulous adress for a fabulous restaurant");

		List<Restaurant> restaurants = singletonList(restaurant);
		List<Long> restaurantsIds = singletonList(restaurants.get(0).getId());
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);

		ResponseEntity<List<Order>> response = new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

		when(ch.getOrderService()).thenReturn(os);
		when(ch.getRestaurantIdsFromName("Fabulous Restaurant")).thenReturn(restaurantsIds);
		when(os.getOrdersByRestaurantIds(restaurantsIds)).thenReturn(orders);

		assertEquals(response, controller.getOrdersByRestaurantName("Fabulous Restaurant"));
		assertEquals(new ResponseEntity<List<Order>>(new ArrayList<Order>(),HttpStatus.NO_CONTENT), controller.getOrdersByRestaurantName("Not so fabulous restaurant"));

	}

	@Test
	public void testCoursierOrders() {

		Order order1 = new Order(0L,1L,2L,"Adress of a fantastic customer", 0, new Date(), new Date(), 
				EnumOrderStatus.ONGOING, singletonList(1L), singletonList(1L), 42L);
		Order order2 = new Order(1L,1L,3L,"Adress of another fantastic customer", 1, new Date(), new Date(), 
				EnumOrderStatus.TODELIVER, singletonList(2L), singletonList(2L), 24L);		
		Coursier coursier = new Coursier(0L, "Jean-Eudes", EnumCoursierStatus.AVAILABLE);

		List<Coursier> coursiers = singletonList(coursier);
		List<Long> coursiersIds = singletonList(coursiers.get(0).getId());	
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);

		ResponseEntity<List<Order>> response = new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

		when(ch.getOrderService()).thenReturn(os);
		when(ch.getCoursierIdsFromName("Jean-Eudes")).thenReturn(coursiersIds);
		when(os.getOrdersByCoursierIds(coursiersIds)).thenReturn(orders);

		assertEquals(response, controller.getOrdersByCoursierName("Jean-Eudes"));
		assertEquals(new ResponseEntity<List<Order>>(new ArrayList<Order>(),HttpStatus.NO_CONTENT), controller.getOrdersByCoursierName("Someone who's not Jean-Eudes"));

	}


	@Test
	public void testOrderFood() {


		Food food = new Food(0, 0L, 2.0f, "TestFood", "Food to test foods");
		List<Food> foods = singletonList(food);

		Menu menu = new Menu(0L, 0L, 14.5f, "Fabulous menu for a fabulous being", singletonList(1L));
		List<Menu> menus = singletonList(menu);

		OrderContainer orderContainer = new OrderContainer();
		when(ch.checkRestaurantIdIsUnique(orderContainer)).thenReturn(true);
		when(ch.getOrderService()).thenReturn(os);

		Order order1 = new Order(0L,1L,2L,"Adress of a fantastic customer", 0, new Date(), new Date(), 
				EnumOrderStatus.ONGOING, singletonList(0L), singletonList(0L), 42L);

		when(ch.computeFoodOrder(orderContainer,"Client address" , "Client name")).thenReturn(order1);

		ETAResponse eta = new ETAResponse();
		eta.list.add(order1);
		ResponseEntity<ETAResponse> response = new ResponseEntity<ETAResponse>(eta, HttpStatus.OK);
		assertEquals(response, controller.orderFood( "Client name",
				"Client address",
				orderContainer));
		verify(os).insertOrder(order1);
	}

	@Test
	public void testFoodToDeliver() {

		Order order1 = new Order(0L,1L,2L,"Adress of a fantastic customer", 0, new Date(), new Date(), 
				EnumOrderStatus.ONGOING, singletonList(0L), singletonList(0L), 42L);
		Order order2 = new Order(1L,1L,2L,"Adress of a fantastic customer2", 0, new Date(), new Date(), 
				EnumOrderStatus.TODELIVER, singletonList(0L), singletonList(0L), 42L);
		Coursier coursier = new Coursier(0,"A man",EnumCoursierStatus.AVAILABLE);
		when(ch.getOrderService()).thenReturn(os);
		when(os.getOrdersById(0)).thenReturn(singletonList(order1));
		when(os.getOrdersById(1)).thenReturn(singletonList(order2));
		when(ch.getCoursierService()).thenReturn(cs);
		when(cs.getByStatus(EnumCoursierStatus.AVAILABLE)).thenReturn(singletonList(coursier));
		
		ResponseEntity<Order> response = new ResponseEntity<Order>(order1, HttpStatus.OK);
		ResponseEntity<Order> response2 = new ResponseEntity<Order>(order2, HttpStatus.BAD_REQUEST);
		assertEquals(response,controller.sendToDeliver(0l));
		assertEquals(EnumCoursierStatus.DELIVERING,coursier.getStatus());
		assertEquals(response2,controller.sendToDeliver(1l));
		verify(cs).update(coursier);
	}



	@Test
	public void testDeliverFood() {

		//	/**
		//	 *  	(Coursier worflow)
		//	 *  	A coursier validates a food delivery.
		//	 */
		//	@RequestMapping(value = "COURSIER/{coursierName}/ORDERS/{orderId}", method = RequestMethod.POST)
		//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved food"),
		//			@ApiResponse(code = 404, message = "No food was found") })
		//	public ResponseEntity deliverFood(@PathVariable("coursierName") String coursierName,
		//			@PathVariable("orderId") long orderId){
		//		IOrderService orderService = helper.getOrderService();
		//		ICoursierService coursierService = helper.getCoursierService();
		//		Order order = orderService.getOrdersById(orderId).get(0);
		//		order.setDeliveryTime(new Date());
		//		order.setStatus(EnumOrderStatus.DELIVERED);
		//		orderService.updateOrder(order);
		//
		//		//We assume that name is also an id
		//		Coursier coursier = coursierService.getByName(coursierName).get(0);
		//		coursier.setStatus(EnumCoursierStatus.AVAILABLE);
		//		return new ResponseEntity(order = orderService.getOrdersById(orderId).get(0), HttpStatus.OK);
		//	}
		Order order1 = new Order(1L,1L,2L,"Adress of a fantastic customer2", 0, new Date(), new Date(), 
				EnumOrderStatus.TODELIVER, singletonList(0L), singletonList(0L), 42L);
		Coursier coursier = new Coursier(0,"A man",EnumCoursierStatus.DELIVERING);
		when(ch.getOrderService()).thenReturn(os);
		when(os.getOrdersById(0)).thenReturn(singletonList(order1));
		when(ch.getCoursierService()).thenReturn(cs);
		when(cs.getByName("A man")).thenReturn(singletonList(coursier));
		ResponseEntity<Order> response = new ResponseEntity<Order>(order1, HttpStatus.OK);
		assertEquals(response,controller.deliverFood("A man",0l));
		assertEquals(order1.getStatus(),EnumOrderStatus.DELIVERED);
		assertEquals(coursier.getStatus(),EnumCoursierStatus.AVAILABLE);
		
		
	}
}
