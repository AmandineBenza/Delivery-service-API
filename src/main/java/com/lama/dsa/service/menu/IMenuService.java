package com.lama.dsa.service.menu;

import java.util.List;

import com.lama.dsa.model.food.Menu;

public interface IMenuService {

	 /**
     * Get all menus availables in the database.
     */
	public List<Menu> getAll();
	
	 /**
     * Get a menu given a name.
     */
	public List<Menu> getMenuByName(String name);
	
	 /**
     * Insert a food in the database.
     */	
	public Menu insertMenu(Menu menu);
	
}
