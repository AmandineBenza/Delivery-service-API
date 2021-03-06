package com.lama.dsa.service.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lama.dsa.model.restaurant.Restaurant;
import com.lama.dsa.repository.restaurant.IRestaurantRepository;

@Transactional
@Service("RestaurantService")
public class RestaurantService implements IRestaurantService {

	@Autowired
	private IRestaurantRepository restaurantRepository;
	
	public RestaurantService() {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Restaurant> getByName(String restaurantName) {
		return restaurantRepository.findByName(restaurantName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertRestaurant(Restaurant restaurant) {
		restaurantRepository.insert(restaurant);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Restaurant getById(long id) {
		return restaurantRepository.findById(id).get(0);
	}
}
