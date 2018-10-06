package com.lama.dsa.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ETAComputer implements IETAComputer{

	private static final IETAComputer INSTANCE = new ETAComputer();

	public int hardcodedETA;

	private ETAComputer() {}

	public static IETAComputer getInstance(){
		return INSTANCE;
	}

	/**
	 * Computes the "Time Before Eating The Ordered Food".
	 *
	 * @param from start location
	 * @param to end location
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compute(String from, String to) {
		return 20 + new Random().nextInt(40);
	}
}
