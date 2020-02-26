package com.ripl.homework;
import java.util.HashSet;
import java.util.Set;

public class WordMatcher {
	
	private Set<String> words = new HashSet<String>();

	public WordMatcher(String word) {
		words.add(word);
	}

	public void addAdditionalWord(String word) {
		words.add(word);
	}	

	public String match(String word) {
		// assumption is that inWord hashKey is the same. Could check if pedantic. Going for simplest first.
		if (words.contains(word)) return word; //exact match
		if (words.size() == 1) return words.iterator().next(); //fixed caps and/or multiple chars when only one possible match
		return toString(); //return all options concatenated
	}
	
	@Override
	public String toString() {
		return "[" + String.join(", ", words) + "]";
	}

}
