package com.loxx3450.hw_09_02_25.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringAnalyzerCountWordOccurrencesTest {
	@Test
	void givenTextWithMultipleOccurrences_whenCountWordOccurrences_thenCorrectCount() {
		// Arrange
		String text = "The cat chased the other cat because the cat was playful.";
		String word = "cat";
		int expectedCount = 3;

		// Act
		int actualCount = StringAnalyzer.countWordOccurrences(text, word);

		// Assert
		assertEquals(expectedCount, actualCount, "The word 'cat' should occur 3 times.");
	}

	@Test
	void givenTextWithoutWord_whenCountWordOccurrences_thenZero() {
		// Arrange
		String text = "The dog ran across the yard.";
		String word = "cat";
		int expectedCount = 0;

		// Act
		int actualCount = StringAnalyzer.countWordOccurrences(text, word);

		// Assert
		assertEquals(expectedCount, actualCount, "The word 'cat' should occur 0 times.");
	}

	@Test
	void givenTextWithMixedCaseOccurrences_whenCountWordOccurrences_thenCorrectCount() {
		// Arrange
		String text = "Cat cAt CAT caT";
		String word = "cat";
		int expectedCount = 4;

		// Act
		int actualCount = StringAnalyzer.countWordOccurrences(text, word);

		// Assert
		assertEquals(expectedCount, actualCount, "The word 'cat' should occur 4 times regardless of case.");
	}

	@Test
	void givenTextWithWordPartiallyInsideOtherWords_whenCountWordOccurrences_thenCountExactMatchesOnly() {
		// Arrange
		String text = "The catalog contains several categories related to cat.";
		String word = "cat";
		int expectedCount = 1; // only full word matches count

		// Act
		int actualCount = StringAnalyzer.countWordOccurrences(text, word);

		// Assert
		assertEquals(expectedCount, actualCount, "The word 'cat' should occur 1 time as an exact match.");
	}

	@Test
	void givenEmptyText_whenCountWordOccurrences_thenZero() {
		// Arrange
		String text = "";
		String word = "cat";
		int expectedCount = 0;

		// Act
		int actualCount = StringAnalyzer.countWordOccurrences(text, word);

		// Assert
		assertEquals(expectedCount, actualCount, "The word 'cat' should occur 0 times in an empty text.");
	}

	@Test
	void givenEmptyWord_whenCountWordOccurrences_thenZero() {
		// Arrange
		String text = "This is some random text.";
		String word = "";
		int expectedCount = 0;

		// Act
		int actualCount = StringAnalyzer.countWordOccurrences(text, word);

		// Assert
		assertEquals(expectedCount, actualCount, "An empty word should not be counted.");
	}
}
