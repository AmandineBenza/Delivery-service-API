package com.lama.dsa.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ETAComputer implements IETAComputer{

	private final static IETAComputer INSTANCE = new ETAComputer();
	
	@Value("dsa.eta.hardcoded_value")
	private String hardcodedTBETOF;
	
	private ETAComputer() {
	}
	
	public static IETAComputer getInstance(){
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
		return Integer.parseInt(hardcodedTBETOF);
	}

}
