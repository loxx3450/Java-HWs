package com.loxx3450.hw_09_02_25.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringAnalyzerCountConsonantsTest {
	@Test
	void givenStringWithConsonants_whenCountConsonants_thenCorrectCount() {
		// Arrange
		String input = "education";
		int expectedCount = 4;

		// Act
		int actualCount = StringAnalyzer.countConsonants(input);

		// Assert
		assertEquals(expectedCount, actualCount, "The number of consonants in word 'education' should be 4.");
	}

	@Test
	void givenStringWithoutConsonants_whenCountConsonants_thenZero() {
		// Arrange
		String input = "aeiou";
		int expectedCount = 0;

		// Act
		int actualCount = StringAnalyzer.countConsonants(input);

		// Assert
		assertEquals(expectedCount, actualCount, "The number of consonants in word 'aeiou' should be 0.");
	}

	@Test
	void givenStringWithUpperCaseConsonants_whenCountConsonants_thenCorrectCount() {
		// Arrange
		String input = "BcDFg";
		int expectedCount = 5;

		// Act
		int actualCount = StringAnalyzer.countConsonants(input);

		// Assert
		assertEquals(expectedCount, actualCount, "The number of consonants in word 'BcDFg' should be 5.");
	}

	@Test
	void givenEmptyString_whenCountConsonants_thenZero() {
		// Arrange
		String input = "";
		int expectedCount = 0;

		// Act
		int actualCount = StringAnalyzer.countConsonants(input);

		// Assert
		assertEquals(expectedCount, actualCount, "The number of consonants should be 0 for an empty string.");
	}
}
