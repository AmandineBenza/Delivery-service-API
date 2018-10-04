package com.lama.dsa.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lama.dsa.app.Application;
import com.lama.dsa.model.food.Food;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
//@WebMvcTest(Controller.class)
@SpringBootTest(classes = Application.class)
public class TestController {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private Controller controller;
	
//    Arrival arrival = new Arrival();
//    arrival.setCity("Yerevan");
//
//    List<Arrival> allArrivals = singletonList(arrival);
//
//    given(arrivalController.getAllArrivals()).willReturn(allArrivals);
//
//    mvc.perform(get(VERSION + ARRIVAL + "all")
//            .with(user("blaze").password("Q1w2e3r4"))
//            .contentType(APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$", hasSize(1)))
//            .andExpect(jsonPath("$[0].city", is(arrival.getCity())));
	
	@Test
	public void testDatabaseNotEmpty() {
		Food food = new Food(0, 0, 2.0f, "TestFood", "Food to test foods");
		List<Food> foods = singletonList(food);
		ResponseEntity<List<Food>> response = new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
		given(controller.getAllFoods().getBody()).willReturn(response.getBody());
		
		try {
			mvc.perform(get("localhost:8080/DSA/FOOD")
					.contentType(APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1)))
					.andExpect(jsonPath("$[0].id", is(food.getId())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
