package com.lama.dsa.model.order;

import java.util.Date;
import java.util.List;

public class Order{

	private long id;
	private int restaurantId;
	private int coursierId;
	
	private long clientId;
	
	private Date creationTime;
	private Date deliveryTime;
	
	private EnumOrderStatus status;
	private List<Integer> foodIds;
	
	public Order(long id, int restaurantId, int coursierId, String address, long clientId, Date creationTime,
			Date deliveryTime, EnumOrderStatus status, List<Integer> foodIds) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.coursierId = coursierId;
		this.clientId = clientId;
		this.creationTime = creationTime;
		this.deliveryTime = deliveryTime;
		this.status = status;
		this.foodIds = foodIds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getCoursierId() {
		return coursierId;
	}

	public void setCoursierId(int coursierId) {
		this.coursierId = coursierId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public EnumOrderStatus getStatus() {
		return status;
	}

	public void setStatus(EnumOrderStatus status) {
		this.status = status;
	}

	public List<Integer> getFoodIds() {
		return foodIds;
	}

	public void setFoodIds(List<Integer> foodIds) {
		this.foodIds = foodIds;
	}
	
}
