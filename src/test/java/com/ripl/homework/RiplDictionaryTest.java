package com.ripl.homework;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import com.ripl.homework.RiplDictionary;

public class RiplDictionaryTest {

	@Test
	public void testLoadFileIntoTreeGetsAllWordsAndCreatesKeys() throws Exception {

		int expectedNumWords = 0;
		
		InputStream input = ClassLoader.getSystemClassLoader().getResource("words").openStream();
		Scanner scanner = new Scanner(input);
		while (scanner.hasNextLine()) {
			expectedNumWords++;
			scanner.nextLine();
		}
		scanner.close();

		RiplDictionary unit = new RiplDictionary();
		assertEquals("precondition check", 0, unit.numberOfWords());

		unit.loadFileIntoTree();
		assertEquals(expectedNumWords, unit.numberOfWords());

		assertTrue("hashKeys (patterns) shouldn't outnumber words",
				unit.numberOfWords() >= unit.numberOfDistinctPatterns());
		// I could do a gold standard test for patterns also - this makes the tests
		// match the test resource file exactly
		// which can be good but also makes the tests rigid. Tradeoff.
	}

	@Test
	public void createHashKeyFromWordWillLowercase() throws Exception {
		RiplDictionary unit = new RiplDictionary();
		String hashKey = unit.createHashKeyFromWord("Plop");
		assertEquals("plop", hashKey);
	}

	@Test
	public void createHashKeyFromWordWillRemoveDupes() throws Exception {
		RiplDictionary unit = new RiplDictionary();
		String hashKey = unit.createHashKeyFromWord("ppllopp");
		assertEquals("plop", hashKey);
	}

	@Test
	public void resolve() {
		RiplDictionary unit = new RiplDictionary();
		unit.loadFileIntoTree(); //gold standard test - relies on the word offbeat to be in resource file
		assertEquals("offbeat", unit.checkWord("offbeat"));
	}
	
	@Test
	public void resolveNoCorrectionFound() {
		RiplDictionary unit = new RiplDictionary();
		unit.loadFileIntoTree();
		assertEquals("No Correction Found", unit.checkWord("sdljsdlkjfdshhfhf"));
	}
}
