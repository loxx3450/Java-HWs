package com.loxx3450.hw_09_02_25;

import com.loxx3450.hw_09_02_25.task2.Currency;
import com.loxx3450.hw_09_02_25.task2.CurrencyConverter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

	public static void main(String[] args) {
		System.out.println(CurrencyConverter.convert(Currency.Euro, Currency.Euro, 100));
	}
}