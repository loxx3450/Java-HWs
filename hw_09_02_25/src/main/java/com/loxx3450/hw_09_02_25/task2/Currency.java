package com.loxx3450.hw_09_02_25.task2;

public enum Currency {
	Euro("EUR"),
	Dollar("USD"),
	PoundSterling("GBP"),
	Yen("JPY");

	private final String isoCode;

	Currency(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getIsoCode() {
		return isoCode;
	}
}
