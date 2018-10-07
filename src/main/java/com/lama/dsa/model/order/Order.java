package com.lama.dsa.model.order;

import java.util.Date;
import java.util.List;

import com.lama.dsa.model.IResponseComponent;

/**
 * Uberoo Order representation.
 */
public class Order implements IResponseComponent {

	private long id;
	private long restaurantId;
	private long coursierId;
	
	private long clientId;
	private long eta; // estimated time of arrival
	
	private Date creationTime;
	private Date deliveryTime;
	
	private EnumOrderStatus status;
	
	private List<Long> foodIds;
	private List<Long> menuIds;
	
	public Order(long id, long restaurantId, long coursierId, String address, long clientId, Date creationTime,
			Date deliveryTime, EnumOrderStatus status, List<Long> foodIds, List<Long> menuIds, long eta) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.coursierId = coursierId;
		this.clientId = clientId;
		this.creationTime = creationTime;
		this.deliveryTime = deliveryTime;
		this.status = status;
		this.foodIds = foodIds;
		this.menuIds = menuIds;
		this.eta = eta;
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

	public long getEta() {
		return eta;
	}

	public void setEta(long eta) {
		this.eta = eta;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public List<Long> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Long> menuIds) {
		this.menuIds = menuIds;
	}
	
}
