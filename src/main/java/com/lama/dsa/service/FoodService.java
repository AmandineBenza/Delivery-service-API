package com.lama.dsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lama.dsa.model.food.Food;
import com.lama.dsa.model.food.IFood;
import com.lama.dsa.repository.IFoodRepository;

@Transactional
@Service("FoodService")
public class FoodService implements IFoodService{
	
	@Autowired
	private IFoodRepository foodRepository;
	
	public FoodService(){
	}
	
	@Override
	public List<Food> getAll(){
		return foodRepository.findAll();
	}

	@Override
	public List<Food> getFoodByName(String name) {
		return foodRepository.findByName(name);
	}
	
	@Override
	public Food insertFood(Food food) {
		return  foodRepository.insert(food);
	}
}
