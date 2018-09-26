package com.lama.dsa.repository.menu;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.food.Menu;

public interface IMenuRepository extends MongoRepository<Menu, String> {
	
	public List<Menu> findByName(String name);

}
