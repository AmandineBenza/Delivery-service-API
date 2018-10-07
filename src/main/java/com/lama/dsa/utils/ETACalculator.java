package com.lama.dsa.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * Computes the "Estimated time of arrival".
 */
@Component
public class ETACalculator implements IETACalculator{

	private static final IETACalculator INSTANCE = new ETACalculator();

	public int hardcodedETA;

	private ETACalculator() {}

	public static IETACalculator getInstance(){
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compute(String from, String to) {
		return 20 + new Random().nextInt(40);
	}
}
