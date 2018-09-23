package com.lama.dsa.model.order;

import java.util.Date;
import java.util.List;

public class Order{

	private int id;
	private int restaurantId;
	private int coursierId;
	
	private String address;
	private String clientName;
	
	private Date creationTime;
	private Date deliveryTime;
	
	private EnumOrderStatus status;
	private List<Integer> foodIds;
	
	public Order(int id, int restaurantId, int coursierId, String address, String clientName, Date creationTime,
			Date deliveryTime, EnumOrderStatus status, List<Integer> foodIds) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.coursierId = coursierId;
		this.address = address;
		this.clientName = clientName;
		this.creationTime = creationTime;
		this.deliveryTime = deliveryTime;
		this.status = status;
		this.foodIds = foodIds;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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
