package com.loxx3450.hw_09_02_25.task3;

import java.util.Set;

public class StringAnalyzer {
	private static final Set<Character> VOWELS = Set.of(
		'a', 'e', 'i', 'o', 'u'
	);
	private static final Set<Character> CONSONANTS = Set.of(
		'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'
	);

	public static boolean isPalindrome(String input) {
		String cleaned = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
		String reversed = new StringBuilder(cleaned).reverse().toString();
		return cleaned.equals(reversed);
	}

	public static int countVowels(String input) {
		return (int) input.toLowerCase().chars()
			.filter(c -> VOWELS.contains((char) c))
			.count();
	}

	public static int countConsonants(String input) {
		return (int) input.toLowerCase().chars()
			.filter(c -> CONSONANTS.contains((char) c))
			.count();
	}

	public static int countWordOccurrences(String text, String word) {
		if (word.isEmpty())
			return 0;

		String pattern = "\\b" + word.toLowerCase() + "\\b";
		return (int) text.toLowerCase().split(pattern, -1).length - 1;
	}
}
