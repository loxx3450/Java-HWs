package com.loxx3450.hw_01_03_25.entity;

import com.loxx3450.hw_01_03_25.dto.RecipeFullDto;
import com.loxx3450.hw_01_03_25.dto.RecipeSummaryDto;
import com.loxx3450.hw_01_03_25.enums.RecipeDetailLevel;

public enum MealTime {
	BREAKFAST, LUNCH, DINNER, SUPPER, SNACK, ANYTIME;

	public static MealTime fromString(String text) {
		if (text == null) {
			return ANYTIME;
		}

		for (MealTime value : MealTime.values()) {
			if (value.name().equalsIgnoreCase(text)) {
				return value;
			}
		}
		return ANYTIME;
	}
}

