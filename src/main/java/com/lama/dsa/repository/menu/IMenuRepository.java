package com.lama.dsa.repository.menu;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lama.dsa.model.food.Menu;

/**
 * Database menu storing. 
 */
public interface IMenuRepository extends MongoRepository<Menu, String> {
	
	/**
	 * Find a menu given a menu name.
	 */
	public List<Menu> findByName(String name);

}
