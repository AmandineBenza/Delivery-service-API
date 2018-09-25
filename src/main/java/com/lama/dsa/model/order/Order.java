package com.lama.dsa.model.order;

import java.util.Date;
import java.util.List;

public class Order{

	private long id;
	private long restaurantId;
	private long coursierId;
	
	private long clientId;
	
	private Date creationTime;
	private Date deliveryTime;
	
	private EnumOrderStatus status;
	private List<Long> foodIds;
	
	public Order(long id, long restaurantId, long coursierId, String address, long clientId, Date creationTime,
			Date deliveryTime, EnumOrderStatus status, List<Long> foodIds) {
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

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public long getCoursierId() {
		return coursierId;
	}

	public void setCoursierId(long coursierId) {
		this.coursierId = coursierId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
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

	public List<Long> getFoodIds() {
		return foodIds;
	}

	public void setFoodIds(List<Long> foodIds) {
		this.foodIds = foodIds;
	}
	
}
