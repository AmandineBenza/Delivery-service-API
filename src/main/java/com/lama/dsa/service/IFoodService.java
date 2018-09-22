package com.lama.dsa.service;

import java.util.List;

import com.lama.dsa.model.food.IFood;

public interface IFoodService {

	public List<IFood> getAll();
	
	public List<IFood> getFoodByName(String name);
	
}
