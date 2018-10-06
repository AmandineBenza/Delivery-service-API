package com.lama.dsa.service.menu;

import java.util.List;

import com.lama.dsa.model.food.Menu;

public interface IMenuService {

	public List<Menu> getAll();
	
	public List<Menu> getMenuByName(String name);

	public Menu insertMenu(Menu menu);
	
}
