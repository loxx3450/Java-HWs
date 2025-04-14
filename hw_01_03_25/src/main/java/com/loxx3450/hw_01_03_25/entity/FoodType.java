package com.loxx3450.hw_01_03_25.entity;

import jakarta.persistence.Entity;

public enum FoodType {
	MEAT, FISH, VEGETARIAN, VEGAN, DESSERT, SOUP, SALAD, FAST_FOOD, OTHER;

	public static FoodType fromString(String text) {
		if (text == null) {
			return OTHER;
		}

		for (FoodType value : FoodType.values()) {
			if (value.name().equalsIgnoreCase(text)) {
				return value;
			}
		}
		return OTHER;
	}
}

