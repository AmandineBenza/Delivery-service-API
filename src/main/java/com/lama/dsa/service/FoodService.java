package com.lama.dsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lama.dsa.model.IFood;
import com.lama.dsa.repository.IFoodRepository;

@Transactional
@Service("FoodService")
public class FoodService implements IFoodService{
	
	@Autowired
	private IFoodRepository foodRepository;
	
	public FoodService(){
		
	}
	
	@Override
	public List<IFood> getAll(){
		return foodRepository.findAll();
	}
	
}
