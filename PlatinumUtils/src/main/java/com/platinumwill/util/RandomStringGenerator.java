package com.platinumwill.util;

import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Random;

public class RandomStringGenerator {
	
	public static enum CharacterType {
		LOWER_CASE
		, UPPER_CASE
		, NUMBER
		, SPECIAL
	}
	
	public static String generateRandomString(int minLength, int maxLength, int minLCaseCount, int minUCaseCount,
			int minNumCount, int minSpecialCount) {
		char[] randomString;
		
		String lowerCaseChars = "abcdefgijkmnopqrstwxyz";
		String upperCaseChars = "ABCDEFGHJKLMNPQRSTWXYZ";
		String numericChars = "123456789";
		String specialChars = "*$-+?_&=!%{}/";
		
		Hashtable<CharacterType, Integer> charGroupsUsed = new Hashtable<CharacterType, Integer>();
		charGroupsUsed.put(CharacterType.LOWER_CASE, minLCaseCount);
		charGroupsUsed.put(CharacterType.UPPER_CASE, minUCaseCount);
		charGroupsUsed.put(CharacterType.NUMBER, minNumCount);
		charGroupsUsed.put(CharacterType.SPECIAL, minSpecialCount);
		
		// Because we cannot use the default randomizer, which is based on the
		// current time (it will produce the same "random" number within a
		// second), we will use a random number generator to seed the
		// randomizer.
		
		// Use a 4-byte array to fill it with random bytes and convert it then
		// to an integer value.
		byte[] randomBytes = new byte[4];
		
		// Generate 4 random bytes.
		// RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider();
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(randomBytes);
		
		// Convert 4 bytes into a 32-bit integer value.
		int seed = (randomBytes[0] & 0x7f) << 24 | randomBytes[1] << 16 | randomBytes[2] << 8 | randomBytes[3];
		
		// Create a randomizer from the seed.
		Random random = new Random(seed);
		
		// Allocate appropriate memory for the password.
		if (minLength < maxLength) {
			boolean longEnough = false;
			int randomLength = 0;
			while (!longEnough) {
				randomLength = random.nextInt(maxLength + 1);
				if (randomLength >= minLength) {
					longEnough = true;
				}
			}
			randomString = new char[randomLength];
		} else {
			randomString = new char[minLength];
		}
		
		int requiredCharactersLeft = minLCaseCount + minUCaseCount + minNumCount + minSpecialCount;
		
		// Build the password.
		for (int i = 0; i < randomString.length; i++) {
			String selectableChars = "";
			
			// if we still have plenty of characters left to acheive our minimum
			// requirements.
			if (requiredCharactersLeft < randomString.length - i) {
				// choose from any group at random
				selectableChars = lowerCaseChars + upperCaseChars + numericChars + specialChars;
			} else // we are out of wiggle room, choose from a random group that
					// still needs to have a minimum required.
			{
				// choose only from a group that we need to satisfy a minimum
				// for.
				
				for (CharacterType charGroup : charGroupsUsed.keySet()) {
					if (charGroupsUsed.get(charGroup) > 0) {
						switch (charGroup) {
						case LOWER_CASE:
							selectableChars += lowerCaseChars;
							break;
						case UPPER_CASE:
							selectableChars += upperCaseChars;
							break;
						case NUMBER:
							selectableChars += numericChars;
							break;
						case SPECIAL:
							selectableChars += specialChars;
							break;
						}
					}
				}
			}
			
			// Now that the string is built, get the next random character.
			char nextChar = selectableChars.charAt(random.nextInt(selectableChars.length() - 1));
			
			// Tac it onto our password.
			randomString[i] = nextChar;
			
			// Now figure out where it came from, and decrement the appropriate
			// minimum value.
			if (lowerCaseChars.indexOf(nextChar) >= 0) {
				charGroupsUsed.put(CharacterType.LOWER_CASE, charGroupsUsed.get(CharacterType.LOWER_CASE) - 1);
				if ((int) charGroupsUsed.get(CharacterType.LOWER_CASE) >= 0) {
					requiredCharactersLeft--;
				}
			} else if (upperCaseChars.indexOf(nextChar) > 1) {
				charGroupsUsed.put(CharacterType.UPPER_CASE, (int) charGroupsUsed.get(CharacterType.UPPER_CASE) - 1);
				if (charGroupsUsed.get(CharacterType.UPPER_CASE) >= 0) {
					requiredCharactersLeft--;
				}
			} else if (upperCaseChars.indexOf(nextChar) > 1) {
				charGroupsUsed.put(CharacterType.NUMBER, charGroupsUsed.get(CharacterType.NUMBER) - 1);
				if (charGroupsUsed.get(CharacterType.NUMBER) >= 0) {
					requiredCharactersLeft--;
				}
			} else if (upperCaseChars.indexOf(nextChar) > 1) {
				charGroupsUsed.put(CharacterType.SPECIAL, charGroupsUsed.get(CharacterType.SPECIAL) - 1);
				if (charGroupsUsed.get(CharacterType.SPECIAL) >= 0) {
					requiredCharactersLeft--;
				}
			}
		}
		return new String(randomString);
	}
}
