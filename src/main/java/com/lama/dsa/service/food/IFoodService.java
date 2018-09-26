
package com.lama.dsa.service.food;

import java.util.List;

import com.lama.dsa.model.food.Food;

public interface IFoodService {

	public List<Food> getAll();
	
	public List<Food> getFoodByName(String name);

	public Food insertFood(Food food);
	
}

