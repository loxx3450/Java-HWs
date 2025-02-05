package com.loxx3450.hw_09_02_25.task3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StringAnalyzerIsPalindromeTest {
	@Test
	void givenPalindromeWithSpaces_whenIsPalindrome_thenReturnTrue() {
		// Arrange
		String input = "A man a plan a canal Panama";

		// Act
		boolean result = StringAnalyzer.isPalindrome(input);

		// Assert
		assertTrue(result, "The phrase with spaces should be identified as a palindrome.");
	}

	@Test
	void givenPalindromeWithMixedCase_whenIsPalindrome_thenReturnTrue() {
		// Arrange
		String input = "RaceCar";

		// Act
		boolean result = StringAnalyzer.isPalindrome(input);

		// Assert
		assertTrue(result, "The mixed-case word should be identified as a palindrome.");
	}

	@Test
	void givenNonPalindrome_whenIsPalindrome_thenReturnFalse() {
		// Arrange
		String input = "Hello World";

		// Act
		boolean result = StringAnalyzer.isPalindrome(input);

		// Assert
		assertFalse(result, "A non-palindrome string should return false.");
	}

	@Test
	void givenPalindromeWithSpecialCharacters_whenIsPalindrome_thenReturnTrue() {
		// Arrange
		String input = "Madam, I'm Adam";

		// Act
		boolean result = StringAnalyzer.isPalindrome(input);

		// Assert
		assertTrue(result, "A phrase with special characters should be identified as a palindrome.");
	}

	@Test
	void givenEmptyString_whenIsPalindrome_thenReturnTrue() {
		// Arrange
		String input = "";

		// Act
		boolean result = StringAnalyzer.isPalindrome(input);

		// Assert
		assertTrue(result, "An empty string should be considered a palindrome.");
	}

	@Test
	void givenSingleCharacter_whenIsPalindrome_thenReturnTrue() {
		// Arrange
		String input = "a";

		// Act
		boolean result = StringAnalyzer.isPalindrome(input);

		// Assert
		assertTrue(result, "A single character should be considered a palindrome.");
	}
}
