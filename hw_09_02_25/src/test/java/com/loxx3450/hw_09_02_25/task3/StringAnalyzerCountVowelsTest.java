package com.loxx3450.hw_09_02_25.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringAnalyzerCountVowelsTest {
	@Test
	void givenStringWithVowels_whenCountVowels_thenCorrectCount() {
		// Arrange
		String input = "education";
		int expectedCount = 5;

		// Act
		int actualCount = StringAnalyzer.countVowels(input);

		// Assert
		assertEquals(expectedCount, actualCount, "The number of vowels should be 5.");
	}

	@Test
	void givenStringWithoutVowels_whenCountVowels_thenZero() {
		// Arrange
		String input = "rhythm";
		int expectedCount = 0;

		// Act
		int actualCount = StringAnalyzer.countVowels(input);

		// Assert
		assertEquals(expectedCount, actualCount, "The number of vowels should be 0.");
	}

	@Test
	void givenStringWithUpperCaseVowels_whenCountVowels_thenCorrectCount() {
		// Arrange
		String input = "AEIOU";
		int expectedCount = 5;

		// Act
		int actualCount = StringAnalyzer.countVowels(input);

		// Assert
		assertEquals(expectedCount, actualCount, "The number of upper case vowels should be 5.");
	}

	@Test
	void givenEmptyString_whenCountVowels_thenZero() {
		// Arrange
		String input = "";
		int expectedCount = 0;

		// Act
		int actualCount = StringAnalyzer.countVowels(input);

		// Assert
		assertEquals(expectedCount, actualCount, "The number of vowels should be 0 for an empty string.");
	}
}
