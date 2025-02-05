package com.loxx3450.hw_09_02_25.task2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CurrencyConverterTest {
	private static final double DELTA = 0.0001;

	public static Stream<Arguments> currencyConversionCombinations() {
		List<Currency> currencies = Arrays.asList(Currency.values());

		return currencies.stream()
			.flatMap(source -> currencies.stream()
				.filter(target -> !source.equals(target))  // Exclude conversion with itself
				.map(target -> Arguments.of(source, target))
			);
	}

	@Test
	void givenSameCurrency_whenConvert_thenReturnSameAmount() {
		// Arrange
		double amount = 100.0;
		double expectedAmount = amount;

		// Act
		double actualAmount = CurrencyConverter.convert(Currency.Euro, Currency.Euro, amount);

		// Assert
		assertEquals(expectedAmount, actualAmount, DELTA, "Conversion between the same currency should return the same amount.");
	}

	@ParameterizedTest
	@MethodSource("currencyConversionCombinations")
	void givenTwoCurrencies_whenConvert_thenReturnConvertedAmount(Currency source, Currency target) {
		// Arrange
		double amount = 100.0;

		MonetaryAmount amountInSourceCurrency = Money.of(amount, source.getIsoCode());
		CurrencyConversion conversionToTargetCurrency = MonetaryConversions.getConversion(target.getIsoCode());

		double expectedAmount = amountInSourceCurrency.with(conversionToTargetCurrency).getNumber().doubleValue();

		// Act
		double actualAmount = CurrencyConverter.convert(source, target, amount);

		// Assert
		assertEquals(expectedAmount, actualAmount, DELTA,
			String.format("Converted amount from %s to %s should match the actual exchange rate.", source, target));
	}

	@Test
	void givenZeroAmount_whenConvert_thenReturnZero() {
		// Arrange
		double amount = 0.0;
		double expectedAmount = 0.0;

		// Act
		double actualAmount = CurrencyConverter.convert(Currency.Dollar, Currency.PoundSterling, amount);

		// Assert
		assertEquals(expectedAmount, actualAmount, DELTA, "Converting zero should return zero.");
	}

	@Test
	void givenNegativeAmount_whenConvert_thenReturnNegativeConvertedAmount() {
		// Arrange
		double amount = -100.0;

		// Act
		double actualAmount = CurrencyConverter.convert(Currency.PoundSterling, Currency.Dollar, amount);

		// Assert
		assertTrue(actualAmount < 0, "Converting a negative amount should return a negative result.");
	}
}