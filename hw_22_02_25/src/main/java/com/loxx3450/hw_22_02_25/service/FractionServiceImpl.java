package com.loxx3450.hw_22_02_25.service;

import com.loxx3450.hw_22_02_25.dto.FractionPair;
import com.loxx3450.hw_22_02_25.entity.Fraction;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

@Service
public class FractionServiceImpl implements FractionService {

	@Override
	public Fraction reduceFraction(Fraction fraction) {
		int numerator = fraction.getNumerator();
		int denominator = fraction.getDenominator();

		int gcd = gcd(numerator, denominator);

		return new Fraction(numerator / gcd, denominator / gcd);
	}

	@Override
	public Fraction addFraction(FractionPair fractionPair) {
		return operateFractions(fractionPair, Integer::sum);
	}

	@Override
	public Fraction subtractFraction(FractionPair fractionPair) {
		return operateFractions(fractionPair, (a, b) -> a - b);
	}

	private Fraction operateFractions(FractionPair fractionPair, BiFunction<Integer, Integer, Integer> operation) {
		var f1 = fractionPair.getFraction1();
		var f2 = fractionPair.getFraction2();

		// Least Common Denominator
		int commonDenominator = findCommonDenominator(f1.getDenominator(), f2.getDenominator());

		// new numerators according to updated denominator
		int newNumerator1 = f1.getNumerator() * (commonDenominator / f1.getDenominator());
		int newNumerator2 = f2.getNumerator() * (commonDenominator / f2.getDenominator());

		return new Fraction(operation.apply(newNumerator1, newNumerator2), commonDenominator);
	}

	@Override
	public Fraction multiplyFraction(FractionPair fractionPair) {
		var f1 = fractionPair.getFraction1();
		var f2 = fractionPair.getFraction2();

		int resultNumerator = f1.getNumerator() * f2.getNumerator();
		int resultDenominator = f1.getDenominator() * f2.getDenominator();

		return new Fraction(resultNumerator, resultDenominator);
	}

	@Override
	public Fraction divideFraction(FractionPair fractionPair) {
		var f1 = fractionPair.getFraction1();
		var f2 = fractionPair.getFraction2();

		Fraction reversedFraction2 = new Fraction(f2.getDenominator(), f2.getNumerator());

		return multiplyFraction(new FractionPair(f1, reversedFraction2));
	}

	private int findCommonDenominator(int d1, int d2) {
		return Math.abs(d1 * d2) / gcd(d1, d2);
	}

	// finds greatest common factor
	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
