package com.jaymansmann.gameserver.gameserver.utility;

import java.security.SecureRandom;

public class UtilityClass {
	private static final int LENGTH = 16;
	private static final SecureRandom RANDOM  = new SecureRandom();
	private static final char[] CHARACTERS = {'B', 'C', 'D', 'F', 'G', 'H', 'J',
			'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z',
			'2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static String getRandomCodestring()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < LENGTH; i++)
			stringBuilder.append(CHARACTERS[RANDOM.nextInt(CHARACTERS.length)]);
		return stringBuilder.toString();
	}
	
	//QR code generation
	
	//hashing and salting
}
