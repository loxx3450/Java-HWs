package com.loxx3450.hw_01_03_25.entity;

public enum Country {
	USA, CANADA, UK, FRANCE, GERMANY, ITALY, SPAIN,
	AUSTRALIA, JAPAN, CHINA, INDIA, BRAZIL, MEXICO, OTHER;

	public static Country fromString(String text) {
		if (text == null) {
			return OTHER;
		}

		for (Country value : Country.values()) {
			if (value.name().equalsIgnoreCase(text)) {
				return value;
			}
		}
		return OTHER;
	}
}
