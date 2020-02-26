package com.ripl.homework;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ripl.homework.WordMatcher;

public class WordMatcherTest {

	@Test
	public void findsExactMatch() {
		
		String inWord = "Plop";
		WordMatcher unit = new WordMatcher(inWord);
		
		assertEquals(inWord, unit.match(inWord));
	}
	
	@Test
	public void findsExactMatchWhenMultipleWordsMatchHashKey() {
		
		String firstWord = "ploop";
		String secondWord = "Plop";
		
		WordMatcher unit = new WordMatcher(firstWord);
		unit.addAdditionalWord(secondWord);
		
		assertEquals(secondWord, unit.match(secondWord));
	}

	@Test
	public void matchesOnDoubleCharacterError() {
		String correctWord = "plop";
		WordMatcher unit = new WordMatcher(correctWord);
		
		assertEquals(correctWord, unit.match("ploop"));
	}
	
	@Test
	public void toStringConcatenatesAllOptions() {
		String firstWord = "ploop";
		//you can add more words in here if you wanted
		String lastWord = "Plop";
		
		WordMatcher unit = new WordMatcher(firstWord);
		unit.addAdditionalWord(lastWord);
		
		StringBuilder expected = new StringBuilder("[");
		expected.append(firstWord); expected.append(", ");
		//expected.append(anotherWord); expected.append(", ");
		expected.append(lastWord);
		expected.append("]");
		
		assertEquals("[" + firstWord + ", " + lastWord + "]", unit.toString());
	}
	
	@Test
	public void returnsAllOptionsWhenMultiple() {
		String firstWord = "ploop";
		String secondWord = "Plop";
		
		WordMatcher unit = new WordMatcher(firstWord);
		unit.addAdditionalWord(secondWord);
		
		assertEquals("[ploop, Plop]", unit.match("ppplllloooPppp"));
	}

}
