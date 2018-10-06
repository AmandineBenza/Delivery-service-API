package com.lama.dsa.controller;

import com.lama.dsa.app.Application;
import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.order.Order;
import com.lama.dsa.service.food.FoodService;
import com.lama.dsa.service.food.IFoodService;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
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

import java.io.Console;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FoodService.class})
@ContextConfiguration(classes = {Application.class})
@AutoConfigureMockMvc
public class TestControllerNotWorkingWorking {
	
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
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testGetAllFoodService() {
		when(ch.getFoodService()).thenReturn(fs);
		when(fs.getAll()).thenReturn(new ArrayList<Food>());
		
		//Check this out before deleting it show how InjectMocks Behave but TODO delete
		System.out.println(controller);
		System.out.println(ch);		
		System.out.println(ch.getFoodService());
		System.out.println(fs.getAll());
		System.out.println(controller.getAllFoods());
		//
		
		//assert that when no foods are retrieve a 404 not fond response is sent
		assertEquals(new ResponseEntity<List<Food>>(new ArrayList<Food>(),HttpStatus.NOT_FOUND), controller.getAllFoods());;
	
		Food food = new Food(0, 0, 2.0f, "TestFood", "Food to test foods");
		List<Food> foods = singletonList(food);
		ResponseEntity<List<Food>> response = new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
		when(fs.getAll()).thenReturn(foods);
		//assert that the good Foods are returned 
		assertEquals(response, controller.getAllFoods());;
		
	}
	
	@Test
	public void testFoodInformation() {
		}
}
