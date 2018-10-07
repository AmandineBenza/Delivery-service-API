package com.lama.dsa.utils;

public interface IETACalculator {

	/**
	 * Computes the "Estimated time of arrival".
	 *
	 * @param from start location
	 * @param to end location
	 */
	public int compute(String from, String to);
	
}
