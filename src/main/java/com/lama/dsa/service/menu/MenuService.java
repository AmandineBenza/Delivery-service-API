package com.lama.dsa.service.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lama.dsa.model.food.Menu;
import com.lama.dsa.repository.menu.IMenuRepository;

@Transactional
@Service("MenuService")
public class MenuService implements IMenuService {

	@Autowired
	private IMenuRepository menuRepository;

	public MenuService() {
	}

	@Override
	public List<Menu> getAll() {
		return menuRepository.findAll();
	}

	@Override
	public List<Menu> getMenuByName(String name) {
		return menuRepository.findByName(name);
	}

	@Override
	public Menu insertMenu(Menu menu) {
		return menuRepository.insert(menu);
	}
}
