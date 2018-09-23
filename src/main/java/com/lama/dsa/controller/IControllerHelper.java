package com.lama.dsa.controller;

import java.util.List;


public interface IControllerHelper {
	
	public List<Integer> getCoursierIdsFromName(String coursierName);
	
	public List<Integer> getRestaurantIdsFromName(String restaurantName);
	
}
