package com.loxx3450.hw_09_02_25.task2;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import org.javamoney.moneta.Money;

public class CurrencyConverter {
	public static double convert(Currency source, Currency target, double amount) {
		CurrencyUnit sourceUnit = Monetary.getCurrency(source.getIsoCode());
		CurrencyUnit targetUnit = Monetary.getCurrency(target.getIsoCode());

		MonetaryAmount amountInSourceCurrency = Money.of(amount, sourceUnit);

		CurrencyConversion conversion = MonetaryConversions.getConversion(targetUnit);

		MonetaryAmount amountInTargetCurrency = amountInSourceCurrency.with(conversion);

		return amountInTargetCurrency.getNumber().doubleValue();
	}
}
