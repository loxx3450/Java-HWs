package com.loxx3450.hw_09_02_25.task4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LengthConverterTest {
	public static Stream<Arguments> lengthUnitsCombinations() {
		List<LengthUnit> lengthUnits = Arrays.asList(LengthUnit.values());

		return lengthUnits.stream()
			.flatMap(source -> lengthUnits.stream()
				.filter(target -> !source.equals(target))  // Exclude conversion with itself
				.map(target -> Arguments.of(source, target))
			);
	}

	@ParameterizedTest
	@MethodSource("lengthUnitsCombinations")
	void givenCorrectInput_whenConvert_thenCorrectLength(LengthUnit from, LengthUnit to) {
		// Arrange
		double baseLength = 100;
		double expectedNewLength = baseLength * from.getWeightInMeters() / to.getWeightInMeters();

		// Act
		double actualLength = LengthConverter.convert(from, to, baseLength);

		// Assert
		assertEquals(expectedNewLength, actualLength, () -> {
			return String.format("The conversion from %s to %s should work!", from, to);
		});
	}

	@Test
	void givenSameUnit_whenConvert_thenSameLength() {
		// Arrange
		LengthUnit unit = LengthUnit.Meter;
		double baseLength = 100;
		double expectedNewLength = baseLength;

		// Act
		double actualLength = LengthConverter.convert(unit, unit, baseLength);

		// Assert
		assertEquals(expectedNewLength, actualLength, () -> "The conversion to the same unit should return the same length!");
	}

	@Test
	void givenZeroLength_whenConvert_thenZeroResult() {
		// Arrange
		double baseLength = 0;

		// Act
		double actualLength = LengthConverter.convert(LengthUnit.Kilometer, LengthUnit.Millimeter, baseLength);

		// Assert
		assertEquals(0, actualLength, () -> "Converting zero length should return zero!");
	}

	@Test
	void givenNegativeLength_whenConvert_thenCorrectNegativeResult() {
		// Arrange
		LengthUnit from = LengthUnit.Meter;
		LengthUnit to = LengthUnit.Centimeter;
		double baseLength = -50;
		double expectedNewLength = baseLength * 100;

		// Act
		double actualLength = LengthConverter.convert(from, to, baseLength);

		// Assert
		assertEquals(expectedNewLength, actualLength, () -> "Negative lengths should convert correctly!");
	}

	@Test
	void givenLargeLengthAndOverflowIsExpected_whenConvert_thenReturnInfinity() {
		// Arrange
		LengthUnit from = LengthUnit.Decimeter;
		LengthUnit to = LengthUnit.Centimeter;
		double baseLength = Double.MAX_VALUE;
		double expectedNewLength = Double.POSITIVE_INFINITY;

		// Act
		double actualLength = LengthConverter.convert(from, to, baseLength);

		// Assert
		assertEquals(expectedNewLength, actualLength, () -> "Very large numbers should return infinity when overflow!");
	}
}