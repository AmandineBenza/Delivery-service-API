package com.lama.dsa.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TBETOFComputer implements ITBETOFComputer{

	private final static ITBETOFComputer INSTANCE = new TBETOFComputer();
	
	@Value("dsa.tbetof.hardcoded_value")
	private int hardcodedTBETOF;
	
	private TBETOFComputer() {
	}
	
	public static ITBETOFComputer getInstance(){
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
		return hardcodedTBETOF;
	}

}
