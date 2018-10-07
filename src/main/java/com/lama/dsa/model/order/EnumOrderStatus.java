package com.lama.dsa.model.order;

/**
 * Uberoo Order status
 * TODELIVER means that the food is ready to be delivered to the client by a coursier.
 * ONGOING means that the food is not ready and still at the restaurant.
 * DELIVERED means that the client got his food.
 */
public enum EnumOrderStatus {
	TODELIVER, ONGOING, DELIVERED
}
