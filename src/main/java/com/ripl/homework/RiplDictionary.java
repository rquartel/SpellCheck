package com.ripl.homework;
import java.net.URL;
import java.util.Scanner;
import java.util.TreeMap;

public class RiplDictionary {

	private int numWords = 0;
	private TreeMap<String, WordMatcher> dictionary = new TreeMap<String, WordMatcher>();

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		RiplDictionary riplDictionary = new RiplDictionary();
		riplDictionary.loadFileIntoTree();
		System.out.println("Processed words: " + riplDictionary.numWords);
		System.out.println("Hashkeys: " + riplDictionary.numberOfDistinctPatterns());
		System.out.println();
		System.out.println("Ready for testing. Enter a word and press enter/return key.");

		Scanner scanner = new Scanner(System.in);
		while (true) {
			String word = scanner.next();
			System.out.println("Looking up - " + word);
			System.out.println(riplDictionary.checkWord(word));
			System.out.println();
		}
	}

	public String checkWord( String wordToCheck) {
		WordMatcher matcher = dictionary.get(createHashKeyFromWord(wordToCheck));
		if (matcher == null) return "No Correction Found";
		return matcher.match(wordToCheck);
	}

	public int numberOfWords() {
		return numWords;
	}

	public int numberOfDistinctPatterns() {
		return dictionary.size();
	}

	public void loadFileIntoTree() {
		numWords = 0;

		try {
			URL wordsUrl = ClassLoader.getSystemClassLoader().getResource("words");
			Scanner scanner = new Scanner(wordsUrl.openStream());

			while (scanner.hasNextLine()) {
				numWords++;
				String inWord = scanner.nextLine();
				String hashKey = createHashKeyFromWord(inWord);
				WordMatcher matcher = dictionary.get(hashKey);

				if (matcher != null) { // yuck. I don't like null check as a rule for logic
					// the hashKey is already present so we have multiple words
					matcher.addAdditionalWord(inWord);
				} else {
					// it's a new pattern - add it
					matcher = new WordMatcher(inWord);
					dictionary.put(hashKey, matcher);
				}
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// I wish java had a private except for testing
	String createHashKeyFromWord(String inWord) {
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
