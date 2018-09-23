
package com.lama.dsa.service;

import java.util.List;

import com.lama.dsa.model.food.Food;

public interface IFoodService {

	public List<Food> getAll();
	
	public List<Food> getFoodByName(String name);

	Food insertFood(Food food);
	
}

