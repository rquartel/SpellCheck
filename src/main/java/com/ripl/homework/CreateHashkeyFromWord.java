package com.ripl.homework;

import java.util.TreeMap;

public class CreateHashkeyFromWord {
	
	public String createHashKeyFromWord(String inWord) {
		StringBuilder hashKey = new StringBuilder();
		char lastChar = ' ';
		for (char c : inWord.toLowerCase().toCharArray()) {
			if (c != lastChar) {
				hashKey.append(c);
				lastChar = c;
			}
		}
		return hashKey.toString();
	}

	
}