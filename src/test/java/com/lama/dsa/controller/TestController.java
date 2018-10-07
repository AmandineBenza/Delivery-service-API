package com.lama.dsa.controller;

import com.lama.dsa.app.Application;
import com.lama.dsa.model.ETAResponse;
import com.lama.dsa.model.IResponseComponent;
import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.Menu;
import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;
import com.lama.dsa.model.order.EnumOrderStatus;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.model.restaurant.Restaurant;
import com.lama.dsa.service.food.FoodService;
import com.lama.dsa.service.menu.MenuService;
import com.lama.dsa.service.order.OrderService;
import com.lama.dsa.service.restaurant.RestaurantService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
	
    @InjectMocks
    private Controller controller;
    
    @Mock
    private ControllerHelper ch;

    @Mock
    private FoodService fs;
    
    @Mock
    private RestaurantService rs;
    
    @Mock
    private OrderService os;
    
    @Mock
    private MenuService ms;
    
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
	    when(ch.getWholeCatalogue()).thenReturn(etaResponse);
	    
		//assert that when no foods are retrieve a 404 not fond response is sent
		assertEquals(new ResponseEntity<ETAResponse>(etaResponse , HttpStatus.NO_CONTENT), controller.getAllFoods());
		
		Food food = new Food(0, 0, 2.0f, "TestFood", "Food to test foods");
		List<Food> foods = singletonList(food);
		when(fs.getAll()).thenReturn(foods);
		
		Menu menu = new Menu(0L, 2L, 14.5f, "Fabulous menu for a fabulous being", singletonList(1L));
		List<Menu> menus = singletonList(menu);
		when(ms.getAll()).thenReturn(menus);
		
		ETAResponse eta = new ETAResponse();
		eta.add(food);
		eta.add(menu);
		
		ResponseEntity<ETAResponse> response = new ResponseEntity<ETAResponse>(eta, HttpStatus.OK);
		when(ch.getWholeCatalogue()).thenReturn(eta);
		//assert that the good Foods are returned 
		assertEquals(response, controller.getAllFoods());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testFoodInformation() {
		Food food = new Food(0, 0, 2.0f, "TestFood", "Food to test foods");
		List<Food> foods = singletonList(food);
		ETAResponse eta = new ETAResponse();
		eta.add(foods);
		
		when(ch.getFoodService()).thenReturn(fs);
		when(fs.getFoodByName("plat 1")).thenReturn(foods);
		when(ch.getRestaurantService()).thenReturn(rs);
		when(rs.getById(0)).thenReturn( new Restaurant(0L, 25, "Fabulous Restaurant", "Fabulous Adress for a fabulous restaurant", "Fabulous adress for a fabulous restaurant"));
		
		ResponseEntity<ETAResponse> response = new ResponseEntity<ETAResponse>(eta, HttpStatus.OK);

		when(ch.getEtaFoodByName("plat 1","Address X")).thenReturn(eta);
		when(ch.getEtaFoodByName("plat 2","Address X")).thenReturn(new ETAResponse());
		
		assertEquals(response, controller.getFoodByName("plat 1","Address X"));
		assertEquals(new ResponseEntity(new ArrayList<IResponseComponent>(), HttpStatus.NO_CONTENT),
				controller.getFoodByName("plat 2","Address X"));
	}
	
	@Test
	public void testMenuInformation() {
		Menu menu = new Menu(0L, 2L, 14.5f, "Fabulous menu for a fabulous being", singletonList(1L));
		List<Menu> menus = singletonList(menu);
		ETAResponse eta = new ETAResponse();
		eta.add(menus);
		
		when(ch.getMenuService()).thenReturn(ms);
		when(ms.getMenuByName("Fabulous menu for a fabulous being")).thenReturn(menus);
	}
	
	@Test
	public void testRestaurantOrders() {
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
	
}
