package com.lama.dsa.model.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.Menu;

/**
 * Object use to concatenate the food and the menus chose by the client who want to eat.
 * Cart representation. 
 */
public class OrderContainer {

	private List<Food> foods;
	private List<Menu> menus;
	
	public OrderContainer(Food[] foods, Menu[] menus) {
		this.foods = new ArrayList<Food>(Arrays.asList(foods));
		this.menus = new ArrayList<Menu>(Arrays.asList(menus));
	}
	
	public OrderContainer(){
		
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foodIds) {
		this.foods = foodIds;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}
