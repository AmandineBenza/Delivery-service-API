
package com.lama.dsa.service.food;

import java.util.List;

import com.lama.dsa.model.food.Food;

public interface IFoodService {
	
	 /**
     * Get all foods availables in the database.
     */
	public List<Food> getAll();
	
	 /**
     * Get food given a name.
     */
	public List<Food> getFoodByName(String name);

	 /**
     * Insert a food in the database.
     */	
	public Food insertFood(Food food);
	
}

