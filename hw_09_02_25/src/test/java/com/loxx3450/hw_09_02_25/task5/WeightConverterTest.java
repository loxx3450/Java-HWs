package com.loxx3450.hw_09_02_25.task5;

import static org.junit.jupiter.api.Assertions.*;

import com.loxx3450.hw_09_02_25.task4.LengthUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WeightConverterTest {
	public static Stream<Arguments> weightUnitsCombinations() {
		List<WeightUnit> weightUnits = Arrays.asList(WeightUnit.values());

		return weightUnits.stream()
			.flatMap(source -> weightUnits.stream()
				.filter(target -> !source.equals(target))  // Exclude conversions with itself
				.map(target -> Arguments.of(source, target))
			);
	}

	@ParameterizedTest
	@MethodSource("weightUnitsCombinations")
	void givenCorrectInput_whenConvert_thenCorrectWeight(WeightUnit from, WeightUnit to) {
		// Arrange
		double baseWeight = 100;
		double expectedNewWeight = baseWeight * from.getWeightInKilograms() / to.getWeightInKilograms();

		// Act
		double actualWeight = WeightConverter.convert(from, to, baseWeight);

		// Assert
		assertEquals(expectedNewWeight, actualWeight, () -> {
			return String.format("The conversion from %s to %s should work!", from, to);
		});
	}

	@Test
	void givenSameUnit_whenConvert_thenSameWeight() {
		// Arrange
		WeightUnit unit = WeightUnit.Kilogram;
		double baseWeight = 100;
		double expectedNewWeight = baseWeight;

		// Act
		double actualWeight = WeightConverter.convert(unit, unit, baseWeight);

		// Assert
		assertEquals(expectedNewWeight, actualWeight, () -> "The conversion to the same unit should return the same weight!");
	}

	@Test
	void givenZeroWeight_whenConvert_thenZeroResult() {
		// Arrange
		double baseWeight = 0;

		// Act
		double actualWeight = WeightConverter.convert(WeightUnit.Tonne, WeightUnit.Gram, baseWeight);

		// Assert
		assertEquals(0, actualWeight, () -> "Converting zero weight should return zero!");
	}

	@Test
	void givenNegativeWeight_whenConvert_thenCorrectNegativeResult() {
		// Arrange
		WeightUnit from = WeightUnit.Kilogram;
		WeightUnit to = WeightUnit.Gram;
		double baseWeight = -50;
		double expectedNewWeight = baseWeight * 1000;

		// Act
		double actualWeight = WeightConverter.convert(from, to, baseWeight);

		// Assert
		assertEquals(expectedNewWeight, actualWeight, () -> "Negative weights should convert correctly!");
	}

	@Test
	void givenLargeWeightAndOverflowIsExpected_whenConvert_thenReturnInfinity() {
		// Arrange
		WeightUnit from = WeightUnit.Tonne;
		WeightUnit to = WeightUnit.Centner;
		double baseWeight = Double.MAX_VALUE;
		double expectedNewWeight = Double.POSITIVE_INFINITY;

		// Act
		double actualWeight = WeightConverter.convert(from, to, baseWeight);

		// Assert
		assertEquals(expectedNewWeight, actualWeight, () ->"Very large numbers should return infinity when overflow!");
	}
}