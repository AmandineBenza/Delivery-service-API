package com.lama.dsa.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ETAComputer implements IETAComputer{

	static private final IETAComputer INSTANCE = new ETAComputer();
	
	@Value("dsa.eta.hardcoded_value")
	public String hardcodedETA;
	
	private ETAComputer() {
	}
	
	static public IETAComputer getInstance(){
		return INSTANCE;
	}
	
	/**
	 * Computes the "Time Before Eating The Ordered Food".
	 * 
	 * @param from start location
	 * @param to end location
	 */
	@Override
	public int compute(String from, String to) {
		return Integer.parseInt(hardcodedETA);
	}

}
